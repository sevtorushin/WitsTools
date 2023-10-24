package validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TimeBasedPackageValidatorTest {
    private static PackageValidator validator;

    @BeforeAll
    static void setUp() {
        validator = new TimeBasedPackageValidator();
    }

    static Stream<Arguments> methodDataProvider() {
        return Stream.of(
                Arguments.arguments("&&\n" +
                        "0101ANY\n" +
                        "01020\n" +
                        "0105231022\n" +
                        "0106173700\n" +
                        "01071\n" +
                        "0108560.1\n" +
                        "0110566.3\n" +
                        "01126.2\n" +
                        "011457.25\n" +
                        "0121112.4\n" +
                        "!!", true),
                Arguments.arguments("&&\n" +
                        "0108-0.2\n" +
                        "!!", true),
                Arguments.arguments("&&\n" +
                        "0112-0.1e-005\n" +
                        "!!", true),
                Arguments.arguments("&&\n" +
                        "0105231032\n" +
                        "!!", false),
                Arguments.arguments("&&\n" +
                        "0106126100\n" +
                        "!!", false),
                Arguments.arguments("&&\n" +
                        "0105231022\n" +
                        "0206120000\n" +
                        "!!", false),
                Arguments.arguments("0105231023\n" +
                        "0106122000\n" +
                        "!!", false),
                Arguments.arguments("&&\n" +
                        "0105231023\n" +
                        "0106122000", false),
                Arguments.arguments("fdbfgnfh", false)
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataProvider")
    void isValid(String pack, boolean result) {
        Assertions.assertEquals(result, validator.isValid(pack));
    }
}