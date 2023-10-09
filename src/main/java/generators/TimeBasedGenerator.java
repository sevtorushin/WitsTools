package generators;

import annotation.Item;
import descriptions.TimeBasedDescription;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeBasedGenerator {
    private List<ValueGenerator> valueGenerators;
    private double deltaBPos;

    public TimeBasedGenerator(List<ValueGenerator> generators) {
        this.valueGenerators = generators;
    }

    @Item(number = "05")
    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        return LocalDate.now().format(formatter);
    }

    @Item(number = "06")
    private String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        return LocalTime.now().format(formatter);
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

    private ValueGenerator getValueGenerator(String item){
        return valueGenerators.stream()
                .filter(generator -> generator.getItem().equals(item))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Specified generator not found"));
    }

    public String getValueForItem(String item) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getDeclaredMethods();
        for (Method m : methods) {
            m.setAccessible(true);
            Item c = m.getAnnotation(Item.class);
            if (c == null) continue;
            if (c.number().equals(item)) {
                return (String) m.invoke(this);
            }
        }
        return "0";
    }

    public List<ValueGenerator> getValueGenerators() {
        return valueGenerators;
    }
}
