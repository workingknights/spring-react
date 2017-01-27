package name.aknights.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringReactApplication implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SpringReactApplication.class);

    @Value("${spring.profiles.active}")
    protected String springProfilesActive;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("=======================================");
        LOG.info("App running with active profiles: {}", springProfilesActive);
        LOG.info("=======================================");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringReactApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                LOG.info("Added CORS Mapping");
                registry.addMapping("/api/**").allowedOrigins("http://localhost:8080");
            }
        };
    }
//
//    @Configuration
//    public class MainConfig {
//
//        @Bean
//        public BasicDataSource dataSource() throws URISyntaxException {
//            String dbUrl = System.getenv("JDBC_DATABASE_URL");
//            String username = System.getenv("JDBC_DATABASE_USERNAME");
//            String password = System.getenv("JDBC_DATABASE_PASSWORD");
//
//            BasicDataSource basicDataSource = new BasicDataSource();
//            basicDataSource.setUrl(dbUrl);
//            basicDataSource.setUsername(username);
//            basicDataSource.setPassword(password);
//
//            return basicDataSource;
//        }
//    }
}
