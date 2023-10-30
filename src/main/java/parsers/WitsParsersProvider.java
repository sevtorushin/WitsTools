package parsers;

import annotation.Package;
import org.reflections.Reflections;
import parsers.splitters.PackageSplitter;
import parsers.splitters.RecordSplitter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@SuppressWarnings("rawtypes")
public class WitsParsersProvider {
    private List<WitsParser<?>> parsers;
    private final PackageSplitter packageSplitter;
    private final RecordSplitter recordSplitter;

    public WitsParsersProvider() {
        this.parsers = new ArrayList<>();
        packageSplitter = new PackageSplitter("\\r?\\n|\\n");
        recordSplitter = new RecordSplitter(2, 2);
    }

    public WitsParser<?> getParserForPackage(String witsPackage) {
        String recordOne = packageSplitter.split(witsPackage)[1];
        String packageNumber = recordSplitter.split(recordOne)[0];
        Optional<Class<? extends WitsParser>> parserClass = getParserClass(packageNumber);
        if (parserClass.isEmpty())
            throw new NoSuchElementException("A suitable parser was not found");
        try {
            return parserClass.get().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Parser creation error");
    }

    private Optional<Class<? extends WitsParser>> getParserClass(String packageNumber) {
        Reflections reflections = new Reflections("parsers");
        Set<Class<? extends WitsParser>> classes = reflections.getSubTypesOf(WitsParser.class);
        return classes.stream()
                .filter(aClass -> aClass.isAnnotationPresent(Package.class))
                .filter(aClass -> aClass.getAnnotation(Package.class).number().equals(packageNumber))
                .findFirst().or(Optional::empty);
    }
}
