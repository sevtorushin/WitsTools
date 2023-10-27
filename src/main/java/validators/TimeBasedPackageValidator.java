package validators;

import parsers.splitters.PackageSplitter;

public class TimeBasedPackageValidator extends PackageValidator{

    public TimeBasedPackageValidator() {
        super(new PackageSplitter("\\r?\\n|\\n"), new TimeBasedRecordValidator());
    }
}