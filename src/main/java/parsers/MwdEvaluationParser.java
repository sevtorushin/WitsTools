package parsers;

public class MwdEvaluationParser extends WitsPackageParser {

    public Double getGammaMD(String witsPackage) {
        return getValue(witsPackage, "25") != null ? Double.parseDouble(getValue(witsPackage, "25")) : null;
    }

    public Double getGammaRaw(String witsPackage) {
        return getValue(witsPackage, "27") != null ? Double.parseDouble(getValue(witsPackage, "27")) : null;
    }

    public Double getGammaCorr(String witsPackage) {
        return getValue(witsPackage, "28") != null ? Double.parseDouble(getValue(witsPackage, "28")) : null;
    }
}
