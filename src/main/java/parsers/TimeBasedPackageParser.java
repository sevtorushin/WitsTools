package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsParseException;
import parsers.splitters.PackageSplitter;
import validators.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TimeBasedPackageParser} class allows you to parse WITS packets into record components.
 * Provides a variety of methods for obtaining data on individual records of the WITS package.
 * <p>
 * To parse a package, you need to call the {@code parse} method and pass it the string representation
 * of the WITS package as a parameter. You can get the values of individual schedules using get-methods.
 * For example, the {@code getBitDepth} method will return the bit depth value of the current parsed package.
 * To get the value of item, you must use the {@code getValue} method and pass it the string item as a parameter.
 * To get a value in the form of a {@code double} value, there is a {@code getDoubleValue} method,
 * which will return a numeric value based on the passed item.
 * <p>
 * By default, the {@code TimeBasedPackageParser} object validates the parsed data based on the requirements
 * of the WITS protocol. You can also disable package validation, or add several additional ones.
 *
 * @apiNote
 * {@code TimeBasedPackageParser} extends the {@code WITSPackageParser} class,
 * which includes general functionality for working with parsing WITS packages.
 *
 * @author  Sergey Vorushin
 * @see     WitsPackageParser
 */

@Package(number = "01")
public class TimeBasedPackageParser extends WitsPackageParser {

    /**
     * The default constructor provides a basic {@code TimeBasedPackageParser} setting.
     * The basic setup includes a package validator {@code TimeBasedPackageValidator}
     * and a record validator {@code TimeBasedRecordValidator}.
     */
    public TimeBasedPackageParser() {
        super(new TimeBasedRecordParser(), new PackageSplitter("\\r?\\n|\\n"), new ValidatorBuilder<>(new ArrayList<>(List.of(new TimeBasedPackageValidator()))));
    }

    /**
     * The constructor initializes a {@code TimeBasedPackageParser} object with a set of
     * package validators that can be assembled into a {@code ValidatorBuilder}.
     * By default, {@code TimeBasedPackageParser} is initialized with the
     * {@code TimeBasedRecordValidator} record validator.
     *
     * @param packageValidatorBuilder a builder object that can include an unlimited number of
     * package validators of type {@code TimeBasedPackageValidator}.
     *
     * @see ValidatorBuilder
     */
    public TimeBasedPackageParser(ValidatorBuilder<TimeBasedPackageValidator> packageValidatorBuilder) {
        super(new TimeBasedRecordParser(), new PackageSplitter("\\r?\\n|\\n"), packageValidatorBuilder);
    }

    /**
     * The constructor initializes a {@code TimeBasedPackageParser} object with a set of batch validators,
     * which can be built into a {@code ValidatorBuilder}, and a set of record validators,
     * {@code TimeBasedRecordParser}, which can be built into a {@code ValidatorBuilder}.
     *
     * @param packageValidatorBuilder a builder object that can include an unlimited number of package
     *                                validators of type {@code TimeBasedPackageValidator}.
     * @param recordValidatorBuilder a builder object that can include an unlimited number of batch validators
     *                               of type {@code TimeBasedRecordValidator}.
     *
     * @see ValidatorBuilder
     */
    public TimeBasedPackageParser(ValidatorBuilder<TimeBasedPackageValidator> packageValidatorBuilder, ValidatorBuilder<TimeBasedRecordValidator> recordValidatorBuilder) {
        super(new TimeBasedRecordParser(recordValidatorBuilder), new PackageSplitter("\\r?\\n|\\n"), packageValidatorBuilder);
    }

    /**
     * Returns {@code double} the bit depth value.
     *
     * @return double value for item 08 (BITDEPTH)
     * @throws WitsParseException {@inheritDoc}
     */
    @Item(number = "08")
    public Double getBitDepth() throws WitsParseException {
        return getDoubleValue("08");
    }

    /**
     * Returns {@code double} well depth value.
     *
     * @return double value for item 10 (MD)
     * @throws WitsParseException {@inheritDoc}
     */
    @Item(number = "10")
    public Double getMeasuredDepth() throws WitsParseException {
        return getDoubleValue("10");
    }

    /**
     * Returns the {@code double} value of the block position.
     *
     * @return double value for item 12 (BPOS)
     * @throws WitsParseException {@inheritDoc}
     */
    @Item(number = "12")
    public Double getBlockPos() throws WitsParseException {
        return getDoubleValue("12");
    }

    /**
     * Returns {@code double} the value of the load on the hook.
     *
     * @return double value for item 14 (HKLD)
     * @throws WitsParseException {@inheritDoc}
     */
    @Item(number = "14")
    public Double getHKLD() throws WitsParseException {
        return getDoubleValue("14");
    }

    /**
     * Returns {@code double} weight on bit value.
     *
     * @return double value for item 16 (WOB)
     * @throws WitsParseException {@inheritDoc}
     */
    @Item(number = "16")
    public Double getWOB() throws WitsParseException {
        return getDoubleValue("16");
    }

    /**
     * Returns {@code double} the pressure value in the discharge line.
     *
     * @return double value for item 21 (pressure)
     * @throws WitsParseException {@inheritDoc}
     */
    @Item(number = "21")
    public Double getPressure() throws WitsParseException {
        return getDoubleValue("21");
    }
}
