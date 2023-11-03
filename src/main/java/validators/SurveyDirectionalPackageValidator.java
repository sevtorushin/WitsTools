package validators;

import parsers.splitters.PackageSplitter;

public class SurveyDirectionalPackageValidator extends PackageValidator{

    public SurveyDirectionalPackageValidator() {
        super(new PackageSplitter("\\r?\\n|\\n"), new SurveyDirectionalRecordValidator());
    }
}