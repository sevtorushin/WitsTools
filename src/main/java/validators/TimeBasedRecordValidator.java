package validators;

import descriptions.TimeBasedDescription;
import parsers.RecordSplitter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class TimeBasedRecordValidator extends RecordValidator {

    public TimeBasedRecordValidator() {
        super(TimeBasedDescription.class, new RecordSplitter(2, 2));
    }

    @Override
    boolean isValidSpecificValue(String item, String value) {
        boolean isValidValue;
        int parseItem;
        try {
            parseItem = Integer.parseInt(item);
            if (parseItem > 45)
                throw new IllegalStateException("Unexpected item from record: " + item);
            switch (item) {
//                case "08":
//                    isValidValue = ...
                default:
                    isValidValue = isNumber(value);

            }
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unexpected item from record: " + item);
        }
        return isValidValue;
    }
}
