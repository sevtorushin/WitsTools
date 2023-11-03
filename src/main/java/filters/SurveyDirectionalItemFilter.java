package filters;

import descriptions.SurveyDirectionalDescription;
import parsers.SurveyDirectionalPackageParser;

import java.util.List;

public class SurveyDirectionalItemFilter extends ItemFilter<SurveyDirectionalDescription>{

    public SurveyDirectionalItemFilter(List<SurveyDirectionalDescription> descriptors) {
        super(descriptors, new SurveyDirectionalPackageParser());
    }

    public SurveyDirectionalItemFilter() {
        super(new SurveyDirectionalPackageParser());
    }

    @Override
    public Class<SurveyDirectionalDescription> getType() {
        return SurveyDirectionalDescription.class;
    }
}
