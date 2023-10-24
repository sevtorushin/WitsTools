package validators;

import descriptions.TimeBasedDescription;
import parsers.PackageSplitter;
import parsers.TimeBasedParser;

public class TimeBasedPackageValidator extends PackageValidator{

    public TimeBasedPackageValidator() {
        super(new PackageSplitter("\\r?\\n|\\n"), new TimeBasedRecordValidator());
    }
}