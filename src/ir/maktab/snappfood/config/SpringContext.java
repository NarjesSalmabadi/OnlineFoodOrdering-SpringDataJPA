package ir.maktab.snappfood.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "ir.maktab")
@Import(SpringDataJPAConfig.class)
public class SpringContext {
}
