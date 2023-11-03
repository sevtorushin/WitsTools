package filters;

import descriptions.MwdEvaluationDescription;
import parsers.MwdEvaluationPackageParser;

import java.util.List;

public class MwdEvaluationItemFilter extends ItemFilter<MwdEvaluationDescription>{

    public MwdEvaluationItemFilter(List<MwdEvaluationDescription> descriptors) {
        super(descriptors, new MwdEvaluationPackageParser());
    }

    public MwdEvaluationItemFilter() {
        super(new MwdEvaluationPackageParser());
    }

    @Override
    public Class<MwdEvaluationDescription> getType() {
        return MwdEvaluationDescription.class;
    }
}
