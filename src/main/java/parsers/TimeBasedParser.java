package parsers;

import exceptions.WitsPackageException;
import validators.RecordValidator;

import java.util.Map;

public class TimeBasedParser extends WitsPackageParser {

    public Double getBitDepth(String witsPackage) {
        return getValue(witsPackage, "08") != null ? Double.parseDouble(getValue(witsPackage, "08")) : null;
    }

    public Double getMeasuredDepth(String witsPackage) {
        return getValue(witsPackage, "10") != null ? Double.parseDouble(getValue(witsPackage, "10")) : null;
    }

    public Double getBlockPos(String witsPackage) {
        return getValue(witsPackage, "12") != null ? Double.parseDouble(getValue(witsPackage, "12")) : null;
    }

    public Double getHKLD(String witsPackage) {
        return getValue(witsPackage, "14") != null ? Double.parseDouble(getValue(witsPackage, "14")) : null;
    }

    public Double getPressure(String witsPackage) {
        return getValue(witsPackage, "21") != null ? Double.parseDouble(getValue(witsPackage, "21")) : null;
    }
}
