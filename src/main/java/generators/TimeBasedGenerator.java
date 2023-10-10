package generators;

import annotation.Item;
import descriptions.TimeBasedDescription;
import java.util.List;

public class TimeBasedGenerator extends WitsGenerator{ //todo Дописать класс, закоммитить и срастить с веткой мастер
    private double deltaBPos;

    public TimeBasedGenerator(List<ValueGenerator> generators) {
        super(generators);
    }

    @Item(number = "08")
    private String getBDepth(){
        String item = TimeBasedDescription.DEPTH_BIT_MEAS.getItem();
        ValueGenerator bPosGen = getValueGenerator(item);
        double bDepth = bPosGen.buildNextValue() - deltaBPos;
        bPosGen.setValue(bDepth);
        return String.format("%.7s", bDepth);
    }

    @Item(number = "12")
    private String getBPOS() {
        String item = TimeBasedDescription.BLOCK_POSITION.getItem();
        ValueGenerator bPosGen = getValueGenerator(item);
        double beforeIncrement = bPosGen.getValue();
        Double afterIncrement = getValueGenerator(item).buildNextValue();
        deltaBPos = afterIncrement - beforeIncrement;
        return String.format("%.7s", afterIncrement);
    }
}
