package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@EnableAspectJAutoProxy
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.itis.javalab.repositories")
@Configuration
@EnableJdbcHttpSession
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationConfig {

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.mail.port"))));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

        mailSender.setJavaMailProperties(properties());
        return mailSender;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        properties.setProperty("mail.smtp.allow8bitmime", environment.getProperty("spring.mail.properties.mail.smtp.allow8bitmime"));
        properties.setProperty("mail.smtp.ssl.trust", environment.getProperty("spring.mail.properties.mail.smtp.ssl.trust"));
        properties.setProperty("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));

        return properties;
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        // создаем адаптер, который позволит Hibernate работать с Spring Data Jpa
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        // создали фабрику EntityManager как Spring-бин
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ru.itis.javalab.models");
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        properties.setProperty("hibernate.connection.charSet","UTF-8");
        properties.setProperty("hibernate.hbm2ddl.import_files","schema.sql");
        properties.setProperty("connection.autocommit","true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
