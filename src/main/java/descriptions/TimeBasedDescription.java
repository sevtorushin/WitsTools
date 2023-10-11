package descriptions;

public enum TimeBasedDescription implements WitsDescription{
    WELL_IDENTIFIER("01", "WID"),
    HOLE_SECT_NO("02", "SKNO"),
    RECORD_IDENTIFIER("03", "RID"),
    SEQUENCE_IDENTIFIER("04", "SQID"),
    DATE("05", "DATE"),
    TIME("06", "TIME"),
    ACTIVITY_CODE("07", "ACTC"),
    DEPTH_BIT_MEAS("08", "DBTM"),
    DEPTH_BIT_VERT("09", "DBTV"),
    DEPTH_HOLE_MEAS("10", "DMEA"),
    DEPTH_HOLE_VERT("11", "DVER"),
    BLOCK_POSITION("12", "BPOS"),
    RATE_OF_PENETRATION_AVG("13", "ROPA"),
    HOOK_LOAD_AVG("14", "HKLA"),
    HOOK_LOAD_MAX("15", "HKLX"),
    WEIGHT_ON_BIT_AVG("16", "WOBA"),
    WEIGHT_ON_BIT_MAX("17", "WOBX"),
    ROTARY_TORQUE_AVG("18", "TQA"),
    ROTARY_TORQUE_MAX("19", "TQX"),
    ROTARY_SPEED_AVG("20", "RPMA"),
    STAND_PIPE_PRESSURE_AVG("21", "SPPA"),
    CASING_PRESSURE("22", "CHKP"),
    PUMP_STROKE_RATE_1("23", "SPM1"),
    PUMP_STROKE_RATE_2("24", "SPM2"),
    PUMP_STROKE_RATE_3("25", "SPM3"),
    TANK_VOLUME("26", "TVA"),
    TANK_VOLUME_CHANGE("27", "TVCA"),
    MUD_FLOW_OUT("28", "MFOP"),
    MUD_FLOW_OUT_AVG("29", "MFOA"),
    MUD_FLOW_IN_AVG("30", "MFIA"),
    MUD_DENSITY_OUT("31", "MDOA"),
    MUD_DENSITY_IN("32", "MDIA"),
    MUD_TEMPERATURE_OUT_AVG("33", "MTOA"),
    MUD_TEMPERATURE_IN_AVG("34", "MTIA"),
    MUD_CONDUCTIVITY_OUT_AVG("35", "MCOA"),
    MUD_CONDUCTIVITY_IN_AVG("36", "MCIA"),
    PUMP_STROKE_COUNT_CUM("37", "STKC"),
    LAG_STROKES("38", "LSTK"),
    DEPTH_RETURNS_MEAS("39", "DRTM"),
    GAS_AVG("40", "GASA"),
    SPARE1("41", "SPR1"),
    SPARE2("42", "SPR2"),
    SPARE3("43", "SPR3"),
    SPARE4("44", "SPR4"),
    SPARE5("45", "SPR5")
    ;

    String recordNumber = "01";
    String item;
    String mnemonic;

    TimeBasedDescription(String item, String mnemonic) {
        this.item = item;
        this.mnemonic = mnemonic;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public String getItem() {
        return item;
    }

    public String getMnemonic() {
        return mnemonic;
    }
}
