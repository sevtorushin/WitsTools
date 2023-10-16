package validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public abstract class WitsValidator implements Validator {
    @Override
    public boolean isValid(String data) {
        return false;
    }

    abstract boolean isValidPackageNumber(String record);

    abstract boolean isValidItem(String record);

    public boolean isValidNumberValue(String witsPackage) {
        String[] records = witsPackage.split("\r?\n|\r");
        String value;
        int item;
        boolean isValid;
        for (int i = 1; i < records.length - 1; i++) {
            item = Integer.parseInt(records[i].substring(2, 4));
            value = records[i].substring(4);
            if (!isValidPackageNumber(records[i]))
                return false;
            if (item > 7) {
                isValid = value.matches("-?\\d+\\.?\\d*?");
                if (!isValid)
                    return false;
            }
        }
        return true;
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

    public static boolean isChronoUnit(String value) {
        List<String> patternList = List.of("yyMMdd", "HHmmss");
        for (String pattern : patternList) {
            try {
                LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
                return true;
            } catch (DateTimeParseException dpe) {
                try {
                    LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
                    return true;
                } catch (DateTimeParseException ignored) {
                }
            }
        }
        return false;
    }
}
