package filters;

import descriptions.TimeBasedDescription;
import parsers.TimeBasedPackageParser;

import java.util.*;

public class TimeBasedItemFilter extends ItemFilter<TimeBasedDescription>{

    public TimeBasedItemFilter(Set<TimeBasedDescription> descriptors) {
        super(descriptors, new TimeBasedPackageParser());
    }

    public TimeBasedItemFilter() {
        super(new TimeBasedPackageParser());
    }

    @Override
    public Class<TimeBasedDescription> getType() {
        return TimeBasedDescription.class;
    }
}
