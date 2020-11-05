package by.mybrik.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("by.mybrik")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({DatabaseConfig.class, ApplicationBeans.class})
public class ApplicationMainConfiguration {
}
