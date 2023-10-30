package parsers;

import annotation.Package;
import org.reflections.Reflections;
import parsers.splitters.PackageSplitter;
import parsers.splitters.RecordSplitter;

import java.util.*;

@SuppressWarnings("rawtypes")
public class WitsParsersProvider {
    private final Map<String, WitsParser<?>> parsers;
    private final PackageSplitter packageSplitter;
    private final RecordSplitter recordSplitter;

    public WitsParsersProvider() {
        packageSplitter = new PackageSplitter("\\r?\\n|\\n");
        recordSplitter = new RecordSplitter(2, 2);
        this.parsers = new HashMap<>();
        Reflections reflections = new Reflections("parsers");
        Set<Class<? extends WitsParser>> classes = reflections.getSubTypesOf(WitsParser.class);
        classes.stream()
                .filter(aClass -> aClass.isAnnotationPresent(Package.class))
                .forEach(aClass -> {
                    try {
                        parsers.put(aClass.getAnnotation(Package.class).number(), aClass.getDeclaredConstructor().newInstance());
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException("Parser creation error");
                    }
                });
    }

    public WitsParser<?> getParserForPackage(String witsPackage) {
        String recordOne = packageSplitter.split(witsPackage)[1];
        String packageNumber = recordSplitter.split(recordOne)[0];
        WitsParser<?> parser = parsers.get(packageNumber);
        if (parser == null)
            throw new NoSuchElementException("A suitable parser was not found");
        return parser;
    }
}
