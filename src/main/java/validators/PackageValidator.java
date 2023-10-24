package validators;

import descriptions.WitsDescriptor;
import exceptions.WitsPackageException;
import parsers.PackageSplitter;
import parsers.Splitter;
import parsers.WitsPackageParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

public abstract class PackageValidator implements Validator {
    private RecordValidator recordValidator;
    private Splitter<String, String> packageSplitter;

    PackageValidator(PackageSplitter packageSplitter, RecordValidator recordValidator) {
        this.recordValidator = recordValidator;
        this.packageSplitter = packageSplitter;
    }

    @Override
    public boolean isValid(String witsPackage) {
        if (!isWitsPackage(witsPackage))
            return false;
        boolean result = false;
        String[] records = packageSplitter.split(witsPackage);
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
