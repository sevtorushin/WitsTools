package validators;

import parsers.PackageSplitter;

public class TimeBasedPackageValidator extends PackageValidator{

    public TimeBasedPackageValidator() {
        super(new TimeBasedRecordValidator(), new PackageSplitter("\\r?\\n|\\r"));
    }
}