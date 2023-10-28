package validators;

import descriptions.WitsDescriptor;
import exceptions.WitsValidationException;
import parsers.splitters.RecordSplitter;
import parsers.splitters.Splitter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public abstract class RecordValidator extends WitsValidator {
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

    abstract boolean isValidSpecificValue(String item, String value) throws WitsValidationException;

    @Override
    boolean isValidWits(String record) throws WitsValidationException {
        String[] tokens = recordSplitter.split(record);
        String packageNumber = tokens[0];
        String item = tokens[1];
        String value = tokens[2];
        if (!packageNumber.equals(this.packageNumber))
            throw new WitsValidationException("Invalid package number: " + packageNumber);
        if (!itemSet.contains(item))
            throw new WitsValidationException("Invalid item: " + item);
        boolean isValidValue;
        switch (item) {
            case "01":
                isValidValue = true;
                break;
            case "02":
                if (!(isValidValue = isNumber(value)))
                    throw new WitsValidationException("Invalid value for item 02: " + value);
                break;
            case "03":
                if (!(isValidValue = isNumber(value)))
                    throw new WitsValidationException("Invalid value for item 03: " + value);
                break;
            case "04":
                if (!(isValidValue = isNumber(value)))
                    throw new WitsValidationException("Invalid value for item 04: " + value);
                break;
            case "05":
                if (!(isValidValue = isDate(value)))
                    throw new WitsValidationException("Invalid date: " + value);
                break;
            case "06":
                if (!(isValidValue = isTime(value)))
                    throw new WitsValidationException("Invalid time: " + value);
                break;
            case "07":
                if (!isNumber(value))
                    throw new WitsValidationException("Activity code should be number: " + value);
                if (!(isValidValue = Integer.parseInt(value) > 0 && Integer.parseInt(value) < 34))
                    throw new WitsValidationException("Invalid activity code: " + value);
                break;
            default:
                isValidValue = isValidSpecificValue(item, value);
                break;
        }
        return isValidValue;
    }

    boolean isNumber(String value) {
        String numberRegexp = "-?\\d+[.,]?\\d*?";
        String eNumberRegexp = "-?\\d+[.,]?\\d*?e-\\d+";
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
