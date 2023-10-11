package builders;

import descriptions.TimeBasedDescription;
import generators.wits.TimeBasedGenerator;


public class TimeBasedBuilder extends WitsPackageBuilder{

    public TimeBasedBuilder(TimeBasedGenerator generator) {
        super(TimeBasedDescription.WELL_IDENTIFIER.getRecordNumber(), generator);
    }

    public TimeBasedBuilder(TimeBasedGenerator generator, String... items) {
        super(TimeBasedDescription.WELL_IDENTIFIER.getRecordNumber(), generator, items);
    }
}