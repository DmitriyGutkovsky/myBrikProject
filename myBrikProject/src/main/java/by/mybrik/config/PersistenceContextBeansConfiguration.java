package by.mybrik.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public class PersistenceContextBeansConfiguration {

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");
        properties.put("current_session_context_class",
                "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

}
