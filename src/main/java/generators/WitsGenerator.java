package generators;

import annotation.Item;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WitsGenerator {

    private final List<ValueGenerator> valueGenerators;

    public WitsGenerator(List<ValueGenerator> valueGenerators) {
        this.valueGenerators = valueGenerators;
    }

    @Item(number = "01")
    String getWellId(){
        return "01";
    }

    @Item(number = "02")
    String getHoleSectNo(){
        return "0";
    }

    @Item(number = "03")
    String getRecId(){
        return "0";
    }

    @Item(number = "04")
    String getSeqId(){
        return "0";
    }

    @Item(number = "05")
    String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        return LocalDate.now().format(formatter);
    }

    @Item(number = "06")
    String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        return LocalTime.now().format(formatter);
    }

    @Item(number = "07")
    String getActCode(){
        return "0";
    }

    public String getValueForItem(String item) throws InvocationTargetException, IllegalAccessException {
        Class<?> superClass = getClass().getSuperclass();
        Method[] superMethods = superClass.getDeclaredMethods();
        Method[] methods = getClass().getDeclaredMethods();
        List<Method> methodList = new ArrayList<>(List.of(superMethods));
        methodList.addAll(List.of(methods));
        for (Method m : methodList) {
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

    ValueGenerator getValueGenerator(String item){
        return getValueGenerators().stream()
                .filter(generator -> generator.getItem().equals(item))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Specified generator not found"));
    }
}
