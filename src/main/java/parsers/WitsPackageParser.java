package parsers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

abstract class WitsPackageParser {
    private Splitter<String, String> recordSplitter;
    private Splitter<String, String> packageSplitter;

    public WitsPackageParser(Splitter<String, String> recordSplitter, Splitter<String, String> packageSplitter) {
        this.recordSplitter = recordSplitter;
        this.packageSplitter = packageSplitter;
    }

    public String getValue(String witsPackage, String item) {
        String[] records = packageSplitter.split(witsPackage);
        String[] tokens;
        for (int i = 1; i < records.length - 1; i++) {
            tokens = recordSplitter.split(records[i]);
            if (tokens[1].equals(item))
                return tokens[2];
        }
        return null;
    }

    public String getWellId(String witsPackage) {
        return getValue(witsPackage, "01");
    }

    public String getHoleSectNo(String witsPackage) {
        return getValue(witsPackage, "02");
    }

    public String getRecordId(String witsPackage) {
        return getValue(witsPackage, "03");
    }

    public String getSeqId(String witsPackage) {
        return getValue(witsPackage, "04");
    }

    public LocalDate getDate(String witsPackage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String date = getValue(witsPackage, "05");
        if (date != null)
            return LocalDate.parse(date, formatter);
        else return null;
    }

    public LocalTime getTime(String witsPackage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String time = getValue(witsPackage, "06");
        if (time != null)
            return LocalTime.parse(time, formatter);
        else return null;
    }

    public String getActivCode(String witsPackage) {
        return getValue(witsPackage, "07");
    }

    public Splitter<String, String> getRecordSplitter() {
        return recordSplitter;
    }

    public void setRecordSplitter(Splitter<String, String> recordSplitter) {
        this.recordSplitter = recordSplitter;
    }

    public Splitter<String, String> getPackageSplitter() {
        return packageSplitter;
    }

    public void setPackageSplitter(Splitter<String, String> packageSplitter) {
        this.packageSplitter = packageSplitter;
    }
}
