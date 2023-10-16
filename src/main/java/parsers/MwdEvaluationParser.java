package parsers;

import exceptions.WitsPackageException;
import validators.RecordValidator;

public class MwdEvaluationParser extends WitsPackageParser {

    public Double getGammaMD(String witsPackage) {
        return getValue(witsPackage, "21") != null ? Double.parseDouble(getValue(witsPackage, "21")) : null;
    }

    public Double getGammaRaw(String witsPackage) {
        return getValue(witsPackage, "23") != null ? Double.parseDouble(getValue(witsPackage, "23")) : null;
    }

    public Double getGammaCorr(String witsPackage) {
        return getValue(witsPackage, "24") != null ? Double.parseDouble(getValue(witsPackage, "24")) : null;
    }
}
