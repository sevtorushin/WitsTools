package validators;

import descriptions.MwdEvaluationDescription;
import descriptions.SurveyDirectionalDescription;
import exceptions.WitsRecordValidationException;
import exceptions.WitsValidationException;
import parsers.splitters.RecordSplitter;

public class MwdEvaluationRecordValidator extends RecordValidator {

    public MwdEvaluationRecordValidator() {
        super(SurveyDirectionalDescription.class, new RecordSplitter(2, 2));
    }

    @Override
    boolean isValidSpecificValue(String item, String value) throws WitsValidationException {
        boolean isValidValue;
        int parseItem;
        try {
            parseItem = Integer.parseInt(item);
            if (parseItem > MwdEvaluationDescription.values().length)
                throw new WitsValidationException("Unexpected record item: " + item);
            if (!(isValidValue = isNumber(value)))
                throw new WitsRecordValidationException("Invalid value: " + value + " for item: " + item);
        } catch (NumberFormatException e) {
            throw new WitsRecordValidationException("Unexpected record item: " + item);
        }
        return isValidValue;
    }
}
