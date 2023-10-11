package validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RecordValidator implements Validator{
    private String packageNumber;
    private List<String> itemList;

    public RecordValidator() {
    }

    @Override
    public boolean isValid(String data) {
        return false;
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
