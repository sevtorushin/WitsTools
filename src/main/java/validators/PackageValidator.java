package validators;

import parsers.PackageSplitter;

abstract class PackageValidator implements Validator {
    private RecordValidator recordValidator;
    private PackageSplitter packageSplitter;

    PackageValidator(RecordValidator recordValidator, PackageSplitter packageSplitter) {
        this.recordValidator = recordValidator;
        this.packageSplitter = packageSplitter;
    }

    @Override
    public boolean isValid(String witsPackage) {
        String[] records = packageSplitter.split(witsPackage);
        if (!records[0].equals("&&") || !records[records.length - 1].equals("!!")) {
            throw new IllegalArgumentException("Header or footer package is missing");
        }
            for (int i = 1; i < records.length - 1; i++) {
                if (!recordValidator.isValid(records[i]))
                    return false;
            }
        return true;
    }

    public RecordValidator getRecordValidator() {
        return recordValidator;
    }

    public void setRecordValidator(RecordValidator recordValidator) {
        this.recordValidator = recordValidator;
    }

    public PackageSplitter getPackageSplitter() {
        return packageSplitter;
    }

    public void setPackageSplitter(PackageSplitter packageSplitter) {
        this.packageSplitter = packageSplitter;
    }
}
