package validators;

import descriptions.TimeBasedDescription;
import parsers.TimeBasedParser;

public class TimeBasedPackageValidator extends PackageValidator{

    public TimeBasedPackageValidator() {
        super(new TimeBasedParser(), TimeBasedDescription.class);
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