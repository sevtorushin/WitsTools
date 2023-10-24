package validators;

import descriptions.WitsDescriptor;
import parsers.RecordSplitter;
import parsers.Splitter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

abstract class RecordValidator implements Validator {
    private String packageNumber;
    private Set<String> itemSet;
    private Splitter<String, String> recordSplitter;

    RecordValidator(Class<? extends WitsDescriptor> descriptorClass, RecordSplitter recordSplitter) {
        this.recordSplitter = recordSplitter;
        try {
            Method getPackageNumber = descriptorClass.getMethod("getPackageNumber");
            this.packageNumber = (String) getPackageNumber.invoke(descriptorClass.getEnumConstants()[0]);
            Method getItemSet = descriptorClass.getDeclaredMethod("getItemSet");
            this.itemSet = (Set<String>) getItemSet.invoke(descriptorClass.getEnumConstants()[0]);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    abstract boolean isValidSpecificValue(String item, String value);

    @Override
    public boolean isValid(String record) {
        String[] tokens = recordSplitter.split(record);
        String packageNumber = tokens[0];
        String item = tokens[1];
        String value = tokens[2];
        boolean isValidPackageNumber = packageNumber.equals(this.packageNumber);
        boolean isValidItem = itemSet.contains(item);
        boolean isValidValue;
        switch (item) {
            case "01":
                isValidValue = true;
                break;
            case "02":
                isValidValue = isNumber(value);
                break;
            case "03":
                isValidValue = isNumber(value);
                break;
            case "04":
                isValidValue = isNumber(value);
                break;
            case "05":
                isValidValue = isDate(value);
                break;
            case "06":
                isValidValue = isTime(value);
                break;
            case "07":
                isValidValue = isNumber(value) && Integer.parseInt(value) > 0 && Integer.parseInt(value) < 34;
                break;
            default:
                isValidValue = isValidSpecificValue(item, value);
                break;
        }
        return isValidPackageNumber && isValidItem && isValidValue;
    }

    boolean isNumber(String value) {
        String numberRegexp = "-?\\d+\\.?\\d*?";
        String eNumberRegexp = "-?\\d+\\.?\\d*?e-\\d+";
        return value.matches(numberRegexp) ||
                value.matches(eNumberRegexp);
    }

    boolean isDate(String value) {
        String pattern = "yyMMdd";
        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
            return true;
        } catch (DateTimeParseException pe) {
            return false;
        }
    }

    boolean isTime(String value) {
        String pattern = "HHmmss";
        try {
            LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
            return true;
        } catch (DateTimeParseException pe) {
            return false;
        }
    }

    String getPackageNumber() {
        return packageNumber;
    }

    Set<String> getItemSet() {
        return itemSet;
    }

    Splitter<String, String> getRecordSplitter() {
        return recordSplitter;
    }

    void setRecordSplitter(Splitter<String, String> recordSplitter) {
        this.recordSplitter = recordSplitter;
    }
}
