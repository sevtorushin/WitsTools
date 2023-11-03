package validators;

import exceptions.SplitException;
import exceptions.WitsValidationException;
import parsers.splitters.PackageSplitter;
import parsers.splitters.Splitter;

public abstract class PackageValidator extends WitsValidator {
    private RecordValidator recordValidator;
    private Splitter<String, String> packageSplitter;

    PackageValidator(PackageSplitter packageSplitter, RecordValidator recordValidator) {
        this.recordValidator = recordValidator;
        this.packageSplitter = packageSplitter;
    }

    @Override
    public boolean isValidWits(String witsPackage) throws WitsValidationException {
        if (!isWitsPackage(witsPackage))
            throw new WitsValidationException("Header (&&) or footer (!!) for specified package missing");
        boolean result = false;
        String[] records;
        try {
            records = packageSplitter.split(witsPackage);
        } catch (SplitException e) {
            throw new WitsValidationException(e.getMessage());
        }
        for (int i = 1; i < records.length - 1; i++) {
            if (!recordValidator.isValid(records[i])) {
                return false;
            }
            result = true;
        }
        return result;
    }

    private boolean isWitsPackage(String witsPackage) {
        return witsPackage.startsWith("&&") && witsPackage.endsWith("!!");
    }
}
