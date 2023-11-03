package validators;

import parsers.splitters.PackageSplitter;

public class MwdEvaluationPackageValidator extends PackageValidator{

    public MwdEvaluationPackageValidator() {
        super(new PackageSplitter("\\r?\\n|\\n"), new MwdEvaluationRecordValidator());
    }
}