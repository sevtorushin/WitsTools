package validators;

import exceptions.WitsPackageException;

public abstract class WitsValidator implements Validator {
    @Override
    public boolean isValid(String data) {
        return false;
    }

    abstract boolean isValidPackageNumber(String record);

    abstract boolean isValidItem(String record);

    public boolean isValidNumberValue(String witsPackage){
        String[] tokens = witsPackage.split("\r?\n|\r");
        String value;
        int item;
        boolean isValid;
        for (int i = 1; i < tokens.length - 1; i++) {
            item = Integer.parseInt(tokens[i].substring(2, 4));
            value = tokens[i].substring(4);
            if (item > 7) {
                isValid = value.matches("-?\\d+\\.?\\d*?");
                if (!isValid)
                    return false;
            }
        }
        return true;
    }

    private boolean isNumber(String value){
        return false;
    }

    private boolean isDate(){
        return false;
    }

    private boolean isTime(){
        return false;
    }
}
