package generators.wits;

import annotation.Item;
import descriptions.TimeBasedDescription;
import generators.ValueGenerator;

import java.util.List;

public class TimeBasedGenerator extends WitsGenerator {
    private double deltaBPos;

    public TimeBasedGenerator(List<ValueGenerator> generators) {
        super(generators);
    }

    @Item(number = "08")
    private String getBDepth() {
        String item = TimeBasedDescription.DEPTH_BIT_MEAS.getItem();
        ValueGenerator bDepthGen = getValueGenerator(item);
        if (bDepthGen == null)
            return "0";
        else {
            double bDepth = bDepthGen.nextDependentOnPrevValue() - deltaBPos;
            bDepthGen.setValue(bDepth);
            return String.format("%.7s", bDepth);
        }
    }

    @Item(number = "10")
    private String getDepth() {
        String depthItem = TimeBasedDescription.DEPTH_HOLE_MEAS.getItem();
        String bDepthItem = TimeBasedDescription.DEPTH_BIT_MEAS.getItem();
        ValueGenerator depthGen = getValueGenerator(depthItem);
        ValueGenerator bDepthGen = getValueGenerator(bDepthItem);
        if (depthGen == null)
            return "0";
        else {
            double bDepth = bDepthGen.getValue();
            double depth = depthGen.getValue();
            if (bDepth > depth) {
                depth = bDepth;
                depthGen.setValue(depth);
            }
            return String.format("%.7s", depth);
        }
    }

    @Item(number = "12")
    private String getBPos() {
        String item = TimeBasedDescription.BLOCK_POSITION.getItem();
        ValueGenerator bPosGen = getValueGenerator(item);
        if (bPosGen == null)
            return "0";
        else {
            double beforeIncrement = bPosGen.getValue();
            Double afterIncrement = bPosGen.nextDependentOnPrevValue();
            deltaBPos = afterIncrement - beforeIncrement;
            return String.format("%.7s", afterIncrement);
        }
    }

    @Item(number = "14")
    private String getHKLD(){
        String item = TimeBasedDescription.HOOK_LOAD_AVG.getItem();
        ValueGenerator hkldGen = getValueGenerator(item);
        if (hkldGen == null)
            return "0";
        else {
            Double hkld = hkldGen.nextIndependentValue();
            return String.format("%.7s", hkld);
        }
    }

    @Item(number = "21")
    private String getPressure(){
        String item = TimeBasedDescription.STAND_PIPE_PRESSURE_AVG.getItem();
        ValueGenerator presGen = getValueGenerator(item);
        if (presGen == null)
            return "0";
        else {
            Double hkld = presGen.nextIndependentValue();
            return String.format("%.7s", hkld);
        }
    }
}
