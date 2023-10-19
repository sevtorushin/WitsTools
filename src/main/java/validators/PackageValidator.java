package validators;

import parsers.PackageSplitter;

abstract class PackageValidator implements Validator{
    private RecordValidator recordValidator;
    private PackageSplitter packageSplitter;

    PackageValidator(RecordValidator recordValidator, PackageSplitter packageSplitter) {
        this.recordValidator = recordValidator;
        this.packageSplitter = packageSplitter;
    }

    @Override
    public boolean isValid(String witsPackage) {
        String[] records = packageSplitter.split(witsPackage);
        for (int i = 1; i < records.length - 1; i++) {
            if (!recordValidator.isValid(records[i]))
                return false;
        }
        return true;
    }
}
