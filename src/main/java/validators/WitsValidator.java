package validators;

import exceptions.WitsValidationException;

public abstract class WitsValidator implements Validator{
    @Override
    public boolean isValid(String data) {
        boolean isValid = false;
        try {
            isValid = isValidWits(data);
        } catch (WitsValidationException wve){
            wve.printStackTrace();
        }
        return isValid;
    }

    abstract boolean isValidWits(String data) throws WitsValidationException;
}
