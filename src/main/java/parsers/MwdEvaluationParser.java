package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsPackageException;
import exceptions.WitsParseException;
import parsers.splitters.PackageSplitter;
import parsers.splitters.RecordSplitter;

@Package(number = "08")
public class MwdEvaluationParser extends WitsPackageParser {

    public MwdEvaluationParser() {
        super(null, new PackageSplitter("\\r?\\n|\\r"), null); //todo убрать Null
    }

    @Item(number = "21")
    public Double getGammaMD(String witsPackage) throws WitsParseException {
        return getDoubleValue("21");
    }

    @Item(number = "23")
    public Double getGammaRaw(String witsPackage) throws WitsParseException {
        return getDoubleValue("23");
    }

    @Item(number = "24")
    public Double getGammaCorr(String witsPackage) throws WitsParseException {
        return getDoubleValue("24");
    }
}
