package validators;

import parsers.RecordSplitter;
import parsers.Splitter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

abstract class RecordValidator implements Validator {
    private final String packageNumber;
    private final Set<String> itemSet;
    private Splitter<String, String> recordSplitter;

    public RecordValidator(String packageNumber, Set<String> itemSet, RecordSplitter recordSplitter) {
        this.packageNumber = packageNumber;
        this.itemSet = itemSet;
        this.recordSplitter = recordSplitter;
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
        return value.matches("-?\\d+\\.?\\d*?");
    }

    private boolean isDate(String value) {
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public Set<String> getItemSet() {
        return itemSet;
    }

    public Splitter<String, String> getRecordSplitter() {
        return recordSplitter;
    }

    public void setRecordSplitter(Splitter<String, String> recordSplitter) {
        this.recordSplitter = recordSplitter;
    }
}
