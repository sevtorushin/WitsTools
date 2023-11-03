package descriptions;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum MwdEvaluationDescription implements WitsDescriptor {
    WELL_IDENTIFIER("01", "WID"),
    HOLE_SECT_NO("02", "SKNO"),
    RECORD_IDENTIFIER("03", "RID"),
    SEQUENCE_IDENTIFIER("04", "SQID"),
    DATE("05", "DATE"),
    TIME("06", "TIME"),
    ACTIVITY_CODE("07", "ACTC"),
    DEPTH_HOLE_MEAS("08", "DMEA"),
    DEPTH_HOLE_VERT("09", "DVER"),
    DEPTH_BIT_MEAS("10", "DBTM"),
    DEPTH_BIT_VERT("11", "DBTV"),
    PASS_NUMBER("12", "PASS"),
    DEPTH_RESIS_1_SENSOR_MEAS("13", "DR1M"),
    DEPTH_RESIS_1_SENSOR_VERT("14", "DR1V"),
    RESIS_1_READING("15", "MR1"),
    RESIS_1_BOREHOLE_CORR("16", "MR1C"),
    DEPTH_RESIS_2_SENSOR_MEAS("17", "DR2M"),
    DEPTH_RESIS_2_SENSOR_VERT("18", "DR2V"),
    RESIS_2_READING("19", "MR2"),
    RESIS_2_BOREHOLE_CORR("20", "MR2C"),
    DEPTH_GRAY_1_SENSOR_MEAS("21", "DG1M"),
    DEPTH_GRAY_1_SENSOR_VERT("22", "DG1V"),
    GAMMA_RAY_1_READING("23", "MG1"),
    GAMMA_RAY_1_BOREHOLE_CORR("24", "MG1C"),
    DEPTH_GRAY_2_SENSOR_MEAS("25", "DG2M"),
    DEPTH_GRAY_2_SENSOR_VERT("26", "DG2V"),
    GAMMA_RAY_2_READING("27", "MG2"),
    GAMMA_RAY_2_BOREHOLE_CORR("28", "MG2C"),
    DEPTH_POR_1_SENSOR_MEAS("29", "DP1M"),
    DEPTH_POR_1_SENSOR_VERT("30", "DP1V"),
    POROSITY_TOOL_1_READING("31", "MPO1"),
    DEPTH_POR_2_SENSOR_MEAS("32", "DP2M"),
    DEPTH_POR_2_SENSOR_VERT("33", "DP2V"),
    POROSITY_TOOL_2_READING("34", "MPO2"),
    DOWNHOLE_FLUID_TEMP_ANN("35", "MFTA"),
    DOWNHOLE_FLUID_TEMP_PIPE("36", "MFTP"),
    DOWNHOLE_FLUID_RESIS_ANN("37", "MFRA"),
    DOWNHOLE_FLUID_RESIS_PIPE("38", "MFRP"),
    DEPTH_FORM_DENSITY_MEAS("39", "DFDM"),
    DEPTH_FORM_DENSITY_VERT("40", "DFDV"),
    FORMATION_DENSITY("41", "MFD"),
    DEPTH_CALIPER_MEAS("42", "DCLM"),
    DEPTH_CALIPER_VERT("43", "DCLV"),
    CALIPER("44", "MCLP"),
    PORE_PRESSURE_GRAD_MWD("45", "MFPP"),
    FRAC_PRESSURE_GRAD_MWD("46", "MFFP"),
    SPARE1("47", "SPR1"),
    SPARE2("48", "SPR2"),
    SPARE3("49", "SPR3"),
    SPARE4("50", "SPR4"),
    SPARE5("51", "SPR5"),
    SPARE6("52", "SPR6"),
    SPARE7("53", "SPR7"),
    SPARE8("54", "SPR8"),
    SPARE9("55", "SPR9")
    ;

    String packageNumber = "08";
    String item;
    String mnemonic;

    MwdEvaluationDescription(String item, String mnemonic) {
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
        return Arrays.stream(MwdEvaluationDescription.values()).map(MwdEvaluationDescription::getItem).collect(Collectors.toSet());
    }
}
