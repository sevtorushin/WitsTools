package descriptions;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum SurveyDirectionalDescription implements WitsDescriptor {
    WELL_IDENTIFIER("01", "WID"),
    HOLE_SECT_NO("02", "SKNO"),
    RECORD_IDENTIFIER("03", "RID"),
    SEQUENCE_IDENTIFIER("04", "SQID"),
    DATE("05", "DATE"),
    TIME("06", "TIME"),
    ACTIVITY_CODE("07", "ACTC"),
    DEPTH_SVY_MEAS("08", "DSVM"),
    DEPTH_SVY_VERT("09", "DSVV"),
    PASS_NUMBER("10", "PASS"),
    DEPTH_HOLE_MEAS("11", "DMEA"),
    SVY_TYPE("12", "STYP"),
    SVY_INCLINATION("13", "SINC"),
    SVY_AZIMUTH_UNCORRECTED("14", "SAZU"),
    SVY_AZIMUTH_CORRECTED("15", "SAZC"),
    SVY_MAGNETIC_TOOLFACE("16", "SMTF"),
    SVY_GRAVITY_TOOLFACE("17", "SGTF"),
    SVY_NORTH_SOUTH_POSIRION("18", "SNS"),
    SVY_EAST_WEST_POSIRION("19", "SEW"),
    SVY_DOG_LEG_SEVERITY("20", "SDLS"),
    SVY_RATE_OF_WALK("21", "SWLK"),
    SPARE1("22", "SPR1"),
    SPARE2("23", "SPR2"),
    SPARE3("24", "SPR3"),
    SPARE4("25", "SPR4"),
    SPARE5("26", "SPR5")
    ;

    String packageNumber = "07";
    String item;
    String mnemonic;

    SurveyDirectionalDescription(String item, String mnemonic) {
        this.item = item;
        this.mnemonic = mnemonic;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public String getItem() {
        return item;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public Set<String> getItemSet(){
        return Arrays.stream(SurveyDirectionalDescription.values()).map(SurveyDirectionalDescription::getItem).collect(Collectors.toSet());
    }
}
