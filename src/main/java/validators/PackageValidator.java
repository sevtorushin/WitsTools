package validators;

import descriptions.WitsDescriptor;
import exceptions.WitsPackageException;
import parsers.WitsPackageParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

abstract class PackageValidator implements Validator {
    private WitsPackageParser parser;
    private Set<String> itemSet;
    private String packageNumber;

    PackageValidator(WitsPackageParser parser, Class<? extends WitsDescriptor> descriptorClass) {
        this.parser = parser;
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
    public boolean isValid(String witsPackage) {
            parser.parse(witsPackage);
            boolean result = false;
        for (Map.Entry<String, String[]> set : parser.getContainer().getMap().entrySet()) {
            if (!isCorrect(set.getValue()[1], set.getKey(), set.getValue()[0])) {
                result = false;
                return result;
            }
            result = true;
        }
        return result;
    }

    private boolean isCorrect(String packageNumber, String item, String value){
        boolean isValidItem = itemSet.contains(item);
        boolean isValidPackageNumber = packageNumber.equals(this.packageNumber);
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

    public WitsPackageParser getParser() {
        return parser;
    }

    public Set<String> getItemSet() {
        return itemSet;
    }

    public String getPackageNumber() {
        return packageNumber;
    }
}
