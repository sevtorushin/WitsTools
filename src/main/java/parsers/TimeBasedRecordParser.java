package parsers;

import parsers.splitters.RecordSplitter;
import validators.TimeBasedRecordValidator;
import validators.ValidatorBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 *The {@code TimeBasedRecordParser} class allows you to parse individual records in WITS packages, dividing each record into package number, item and value.
 * <p>
 * {@code TimeBasedRecordParser} comes with a {@code TimeBasedRecordValidator} record validator that validates each record according to {@code TimeBasedDescription} - a WITS packet description based on the Wellsite transfer specification. Optionally, record validation can be disabled using the {@code resetValidation()} method.
 *
 * @apiNote
 * {@code TimeBasedRecordParser} extends the functionality of the {@code WitsRecordParser} class.
 *
 * @author Sergey Vorushin
 * @see WitsRecordParser
 */

public class TimeBasedRecordParser extends WitsRecordParser {

    /**
     * The default constructor initializes the parser object {@code TimeBasedRecordParser} with the default validator {@code TimeBasedRecordValidator}.
     */
    public TimeBasedRecordParser() {
        super(new ValidatorBuilder<>(new ArrayList<>(List.of(new TimeBasedRecordValidator()))), new RecordSplitter(2, 2));
    }

    /**
     * The extended constructor allows you to initialize the parser with your own validator, which is a descendant of {@code TimeBasedRecordValidator}.
     * @param validatorBuilder builder, which accepts several validators - descendants of {@code TimeBasedRecordValidator}.
     */
    public TimeBasedRecordParser(ValidatorBuilder<TimeBasedRecordValidator> validatorBuilder) {
        super(validatorBuilder, new RecordSplitter(2, 2));
    }
}
