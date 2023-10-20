package validators;

import descriptions.TimeBasedDescription;
import parsers.RecordSplitter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class TimeBasedRecordValidator extends RecordValidator {

    private static Set<String> items;
    private static String packageNumber;

    static {
        items = Arrays.stream(TimeBasedDescription.values()).map(TimeBasedDescription::getItem).collect(Collectors.toSet());
        packageNumber = TimeBasedDescription.WELL_IDENTIFIER.getPackageNumber();
    }

    TimeBasedRecordValidator() {
        super(packageNumber, items, new RecordSplitter(2, 2));
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
