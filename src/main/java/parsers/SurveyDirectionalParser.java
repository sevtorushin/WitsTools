package parsers;

import exceptions.WitsPackageException;
import validators.RecordValidator;

public class SurveyDirectionalParser extends WitsPackageParser {

    public Double getSurveyMD(String witsPackage) {
        return getValue(witsPackage, "08") != null ? Double.parseDouble(getValue(witsPackage, "08")) : null;
    }

    public Double getInclination(String witsPackage) {
        return getValue(witsPackage, "13") != null ? Double.parseDouble(getValue(witsPackage, "13")) : null;
    }

    public Double getAzimuthCorr(String witsPackage) {
        return getValue(witsPackage, "15") != null ? Double.parseDouble(getValue(witsPackage, "15")) : null;
    }

    public Double getMagneticTF(String witsPackage) {
        return getValue(witsPackage, "16") != null ? Double.parseDouble(getValue(witsPackage, "16")) : null;
    }

    public Double getGravityTF(String witsPackage) {
        return getValue(witsPackage, "17") != null ? Double.parseDouble(getValue(witsPackage, "17")) : null;
    }
}
