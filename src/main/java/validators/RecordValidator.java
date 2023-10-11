package validators;

import parsers.RecordParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public class RecordValidator implements Validator {
    private final String packageNumber;
    private final Set<String> itemSet;

    public RecordValidator(String packageNumber, Set<String> itemSet) {
        this.packageNumber = packageNumber;
        this.itemSet = itemSet;
    }

    @Override
    public boolean isValid(String data) {
        String packageNumber = RecordParser.getPackageNumber(data);
        String item = RecordParser.getItem(data);
        String value = RecordParser.getValue(data);
        boolean isValidPackageNumber = packageNumber.equals(this.packageNumber);
        boolean isValidItem = itemSet.contains(item);
        boolean isValidValue;
        switch (item) {
            case "01":
                isValidValue = true;
                break;
            case "05":
                isValidValue = isDate(value);
                break;
            case "06":
                isValidValue = isTime(value);
                break;
            default:
                isValidValue = isNumber(value);
                break;
        }
        return isValidPackageNumber && isValidItem && isValidValue;
    }

    private boolean isNumber(String value) {
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

    private boolean isTime(String value) {
        String pattern = "HHmmss";
        try {
            LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
            return true;
        } catch (DateTimeParseException pe) {
            return false;
        }
    }
}
