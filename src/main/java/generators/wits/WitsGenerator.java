package generators.wits;

import annotation.Item;
import descriptions.TimeBasedDescription;
import generators.ValueGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WitsGenerator {

    private final List<ValueGenerator> valueGenerators;

    private Map<String, Method> methodMap = new HashMap<>();

    public WitsGenerator(List<ValueGenerator> valueGenerators) {
        this.valueGenerators = valueGenerators;
        Class<?> superClass = getClass().getSuperclass();
        Method[] superMethods = superClass.getDeclaredMethods();
        Method[] methods = getClass().getDeclaredMethods();
        Arrays.stream(superMethods)
                .filter(method -> method.isAnnotationPresent(Item.class))
                .forEach(method -> methodMap.put(method.getAnnotation(Item.class).number(), method));
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Item.class))
                .forEach(method -> methodMap.put(method.getAnnotation(Item.class).number(), method));
    }

    @Item(number = "01")
    String getWellId() {
        String item = TimeBasedDescription.WELL_IDENTIFIER.getItem();
        ValueGenerator wellIdGen = getValueGenerator(item);
        if (wellIdGen == null)
            return "0";
        else
        return String.format(Locale.ROOT, "%s", (int) wellIdGen.getValue());
    }

    @Item(number = "02")
    String getHoleSectNo() {
        String item = TimeBasedDescription.HOLE_SECT_NO.getItem();
        ValueGenerator holeSectNoGen = getValueGenerator(item);
        if (holeSectNoGen == null)
            return "0";
        else
            return String.format(Locale.ROOT, "%s", (int) holeSectNoGen.getValue());
    }

    @Item(number = "03")
    String getRecId() {
        return "0";
    }

    @Item(number = "04")
    String getSeqId() {
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
    String getActCode() {
        return "0";
    }

    public String getValueForItem(String item) throws InvocationTargetException, IllegalAccessException {
        Method m = methodMap.get(item);
        if (m == null)
            return "0";
        else {
            m.setAccessible(true);
            return (String) m.invoke(this);
        }
    }

    public List<ValueGenerator> getValueGenerators() {
        return valueGenerators;
    }

    ValueGenerator getValueGenerator(String item) {
        return getValueGenerators().stream()
                .filter(generator -> generator.getItem().equals(item))
                .findFirst()
                .orElse(null);
    }
}
