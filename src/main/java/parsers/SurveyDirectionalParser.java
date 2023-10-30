package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsPackageException;
import exceptions.WitsParseException;
import parsers.splitters.PackageSplitter;
import parsers.splitters.RecordSplitter;

@Package(number = "07")
public class SurveyDirectionalParser extends WitsPackageParser {

    public SurveyDirectionalParser() {
        super(null, new PackageSplitter("\\r?\\n|\\r"), null); //todo убрать Null
    }

    @Item(number = "08")
    public Double getSurveyMD(String witsPackage) throws WitsParseException {
        return getDoubleValue("08");
    }

    @Item(number = "13")
    public Double getInclination(String witsPackage) throws WitsParseException {
        return getDoubleValue("13");
    }

    @Item(number = "15")
    public Double getAzimuthCorr(String witsPackage) throws WitsParseException {
        return getDoubleValue("15");
    }

    @Item(number = "16")
    public Double getMagneticTF(String witsPackage) throws WitsParseException {
        return getDoubleValue("16");
    }

    @Item(number = "17")
    public Double getGravityTF(String witsPackage) throws WitsParseException {
        return getDoubleValue("17");
    }
}
