package validators;

import descriptions.TimeBasedDescription;
import exceptions.WitsValidationException;
import parsers.splitters.RecordSplitter;

public class TimeBasedRecordValidator extends RecordValidator {

    public TimeBasedRecordValidator() {
        super(TimeBasedDescription.class, new RecordSplitter(2, 2));
    }

    @Override
    boolean isValidSpecificValue(String item, String value) throws WitsValidationException {
        boolean isValidValue;
        int parseItem;
        try {
            parseItem = Integer.parseInt(item);
            if (parseItem > 45)
                throw new WitsValidationException("Unexpected item from record: " + item);
            switch (item) {
//                case "08":
//                    isValidValue = ...
                default:
                    isValidValue = isNumber(value);

            }
        } catch (NumberFormatException e) {
            throw new WitsValidationException("Unexpected item from record: " + item);
        }
        return isValidValue;
    }
}
