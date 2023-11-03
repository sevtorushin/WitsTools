package filters;

import descriptions.TimeBasedDescription;
import exceptions.WitsParseException;
import parsers.TimeBasedPackageParser;
import parsers.WitsPackageParser;

import java.util.*;

public class TimeBasedItemFilter extends ItemFilter<TimeBasedDescription>{

    public TimeBasedItemFilter(List<TimeBasedDescription> descriptors) {
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
