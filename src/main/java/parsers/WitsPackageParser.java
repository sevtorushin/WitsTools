package parsers;

import annotation.Item;
import exceptions.WitsPackageException;
import parsers.containers.ParseWitsDataContainer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class WitsPackageParser {
    private Splitter<String, String> packageSplitter;
    private ParseWitsDataContainer container;

    public WitsPackageParser(RecordSplitter recordSplitter, PackageSplitter packageSplitter) {
        this.packageSplitter = packageSplitter;
        this.container = new ParseWitsDataContainer(recordSplitter);
    }

    public WitsPackageParser parse(String witsPackage) {
        container.clear();
        String[] records = packageSplitter.split(witsPackage);
        for (int i = 1; i < records.length - 1; i++) {
            container.put(records[i]);
        }
        return this;
    }

    public ParseWitsDataContainer getContainer() {
        return new ParseWitsDataContainer(container);
    }

    public String getValue(String item) {
        return container.getValue(item);
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
        return container.getValue("07");
    }

    public Splitter<String, String> getPackageSplitter() {
        return packageSplitter;
    }

    public void setPackageSplitter(Splitter<String, String> packageSplitter) {
        this.packageSplitter = packageSplitter;
    }
}
