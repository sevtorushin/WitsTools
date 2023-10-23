package parsers;

import annotation.Item;
import exceptions.WitsPackageParseException;
import exceptions.WitsPackageException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public abstract class WitsPackageParser {
    private Splitter<String, String> recordSplitter;
    private Splitter<String, String> packageSplitter;
    private String packageNumber;
    private Map<String, String> map = new HashMap<>();

    public WitsPackageParser(String packageNumber, Splitter<String, String> recordSplitter, Splitter<String, String> packageSplitter) {
        this.recordSplitter = recordSplitter;
        this.packageSplitter = packageSplitter;
        this.packageNumber = packageNumber;
    }

    public WitsPackageParser parse(String witsPackage) throws WitsPackageException {
        String[] records = packageSplitter.split(witsPackage);
        String[] tokens;
        if (!records[0].equals("&&") || !records[records.length - 1].equals("!!"))
            throw new WitsPackageParseException("Invalid package. Can not find WITS markers (&& or !!)");
        for (int i = 1; i < records.length - 1; i++) {
            tokens = recordSplitter.split(records[i]);
            map.put(tokens[1], tokens[2]);
            if (!tokens[0].equals(packageNumber))
                throw new WitsPackageException("Invalid package");
        }
        return this;
    }

    public Map<String, String> getPackageAsMap() {
        return new HashMap<>(map);
    }

    public String getValue(String item) {
        return map.get(item);
    }

    public Double getDoubleValue(String item) throws WitsPackageException {
        if (Integer.parseInt(item) < 7){
            throw new IllegalArgumentException("Incorrect argument: " + item + ". Item must be greater than 7");
        }
        String value = getValue(item);
        if (value != null)
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException nfe){
                throw new WitsPackageException("Wrong value: " + value);
            }
        return null;
    }

    @Item(number = "01")
    public String getWellId() {
        return getValue("01");
    }

    @Item(number = "02")
    public String getHoleSectNo() {
        return getValue("02");
    }

    @Item(number = "03")
    public String getRecordId() {
        return getValue("03");
    }

    @Item(number = "04")
    public String getSeqId() {
        return getValue("04");
    }

    @Item(number = "05")
    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String date = getValue("05");
        if (date != null)
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException pe){
                System.err.println(pe.getMessage());
                return null;
            }
        return null;
    }

    @Item(number = "06")
    public LocalTime getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String time = getValue("06");
        if (time != null)
            try {
                return LocalTime.parse(time, formatter);
            } catch (DateTimeParseException pe){
                System.err.println(pe.getMessage());
                return null;
            }
        return null;
    }

    @Item(number = "07")
    public String getActivCode() {
        return map.get("07");
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }
}
