package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsPackageException;

@Package(number = "08")
public class MwdEvaluationParser extends WitsPackageParser {

    public MwdEvaluationParser() {
        super("08", new RecordSplitter(), new PackageSplitter("\\r?\\n|\\r"));
    }

    @Item(number = "21")
    public Double getGammaMD(String witsPackage) throws WitsPackageException {
        return getDoubleValue("21");
    }

    @Item(number = "23")
    public Double getGammaRaw(String witsPackage) throws WitsPackageException {
        return getDoubleValue("23");
    }

    @Item(number = "24")
    public Double getGammaCorr(String witsPackage) throws WitsPackageException {
        return getDoubleValue("24");
    }
}
