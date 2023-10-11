package validators;

import exceptions.WitsPackageException;

public class TimeBasedPackageValidator {

    public boolean validatePackageNumber(String witsPackage) {
        String[] tokens = witsPackage.split("\r?\n|\r");
        for (int i = 1; i < tokens.length - 1; i++) {
            if (!tokens[i].startsWith("01"))
                return false;
        }
        return true;
    }

    public boolean validateNumberValue(String witsPackage) throws WitsPackageException {
        String[] tokens = witsPackage.split("\r?\n|\r");
        String value;
        int item;
        boolean isValid;
        for (int i = 1; i < tokens.length - 1; i++) {
            item = Integer.parseInt(tokens[i].substring(2, 4));
            value = tokens[i].substring(4);
            if (item > 8) {
                isValid = value.matches("-?\\d+\\.?\\d*?");
                if (!isValid)
                    throw new WitsPackageException("Invalid value for item " + item + " value: " + value);
            }
        }
        return true;
    }
}
