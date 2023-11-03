package filters;

import descriptions.SurveyDirectionalDescription;
import parsers.SurveyDirectionalPackageParser;

import java.util.Set;

public class SurveyDirectionalItemFilter extends ItemFilter<SurveyDirectionalDescription>{

    public SurveyDirectionalItemFilter(Set<SurveyDirectionalDescription> descriptors) {
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
