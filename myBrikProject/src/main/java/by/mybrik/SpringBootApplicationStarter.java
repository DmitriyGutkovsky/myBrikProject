package by.mybrik;

import by.mybrik.config.ApplicationBeansDatabaseConnections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "by.mybrik")
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ApplicationBeansDatabaseConnections.class})
public class SpringBootApplicationStarter {

  public static void main(String[] args) {

      SpringApplication.run(SpringBootApplicationStarter.class, args);

  }
}
