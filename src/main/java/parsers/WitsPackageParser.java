package parsers;

import annotation.Item;
import exceptions.WitsPackageException;
import exceptions.WitsPackageParseException;
import exceptions.WitsParseException;
import parsers.containers.ParseWitsPackageDataContainer;
import parsers.splitters.PackageSplitter;
import parsers.splitters.Splitter;
import validators.PackageValidator;
import validators.ValidatorBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public abstract class WitsPackageParser extends WitsParser<WitsPackageParser> {
    private Splitter<String, String> packageSplitter;
    private final ParseWitsPackageDataContainer container;
    private ValidatorBuilder<? extends PackageValidator> validators;

    public WitsPackageParser(WitsRecordParser recordParser, PackageSplitter packageSplitter, ValidatorBuilder<? extends PackageValidator> validators) {
        this.packageSplitter = packageSplitter;
        this.container = new ParseWitsPackageDataContainer(recordParser);
        this.validators = validators;
    }

    public WitsPackageParser parse(String witsPackage) throws WitsParseException {
        if (!validators.isValid(witsPackage))
            throw new WitsPackageParseException("Invalid package");
        container.clear();
        String[] records = packageSplitter.split(witsPackage);
        for (int i = 1; i < records.length - 1; i++) {
            container.put(records[i]);
        }
        return this;
    }

    public String getPackageNumber() throws WitsParseException {
        return container.getStorage().values().stream()
                .findFirst()
                .orElseThrow(() -> new WitsParseException("Parse exception. Data is missing. Inner container is empty"))[1];
    }

    public Set<String> getRecordsSet(){
        return container.getRecordsSet();
    }

    public ParseWitsPackageDataContainer getContainer() {
        return new ParseWitsPackageDataContainer(container);
    }

    public String getValue(String item) throws WitsParseException {
        return container.getValue(item);
    }

    public Double getDoubleValue(String item) throws WitsParseException {
        if (Integer.parseInt(item) < 7) {
            throw new IllegalArgumentException("Incorrect argument: " + item + ". Item must be greater than 7");
        }
        String value = getValue(item);
        if (value != null)
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException nfe) {
                throw new WitsParseException("Wrong value: " + value);
            }
        return null;
    }

    @Item(number = "01")
    public String getWellId() throws WitsParseException {
        return getValue("01");
    }

    @Item(number = "02")
    public String getHoleSectNo() throws WitsParseException {
        return getValue("02");
    }

    @Item(number = "03")
    public String getRecordId() throws WitsParseException {
        return getValue("03");
    }

    @Item(number = "04")
    public String getSeqId() throws WitsParseException {
        return getValue("04");
    }

    @Item(number = "05")
    public LocalDate getDate() throws WitsParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String date = getValue("05");
        if (date != null)
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException pe) {
                System.err.println(pe.getMessage());
                return null;
            }
        return null;
    }

    @Item(number = "06")
    public LocalTime getTime() throws WitsParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String time = getValue("06");
        if (time != null)
            try {
                return LocalTime.parse(time, formatter);
            } catch (DateTimeParseException pe) {
                System.err.println(pe.getMessage());
                return null;
            }
        return null;
    }

    @Item(number = "07")
    public String getActivCode() throws WitsParseException {
        return container.getValue("07");
    }

    public void resetValidation() {
        this.validators.clearAllValidators();
        this.container.resetValidation();
    }

    public Splitter<String, String> getPackageSplitter() {
        return packageSplitter;
    }

    public void setPackageSplitter(Splitter<String, String> packageSplitter) {
        this.packageSplitter = packageSplitter;
    }

    public ValidatorBuilder<? extends PackageValidator> getValidators() {
        return validators;
    }

    public void setValidators(ValidatorBuilder<? extends PackageValidator> validators) {
        this.validators = validators;
    }
}
