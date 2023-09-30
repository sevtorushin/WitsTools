package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@ComponentScan({"configuration", "filters"})
@PropertySource("classpath:application.properties")
public class Configuration {
}
