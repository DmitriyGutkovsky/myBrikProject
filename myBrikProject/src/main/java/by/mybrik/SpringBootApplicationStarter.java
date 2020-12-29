package by.mybrik;

import by.mybrik.config.ApplicationBeans;
import by.mybrik.config.PersistenceContextBeansConfiguration;
import by.mybrik.security.configuration.JwtTokenConfig;
import by.mybrik.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.mybrik")
//@EnableWebMvc
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories
@EnableCaching
@Import({ApplicationBeans.class,
        PersistenceContextBeansConfiguration.class,
        WebSecurityConfiguration.class,
        JwtTokenConfig.class
})
public class SpringBootApplicationStarter {

  public static void main(String[] args) {

      SpringApplication.run(SpringBootApplicationStarter.class, args);

  }
}
