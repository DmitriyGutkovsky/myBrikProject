package by.mybrik.config;

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
