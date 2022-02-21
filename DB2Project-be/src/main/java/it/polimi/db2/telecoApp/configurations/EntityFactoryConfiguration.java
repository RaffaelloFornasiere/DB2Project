//package it.polimi.db2.telecoApp.configurations;
//
//import org.hibernate.jpa.HibernatePersistenceProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories
//@EnableTransactionManagement
//public class EntityFactoryConfiguration {
//
//    private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.BillingEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.OptionalPackageEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.OrderEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.RoleEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.ServiceEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.UserEntity",
//            "it.polimi.db2.telecoApp.dataaccess.entities.ValidityPeriodEntity"};
//
////    @Value("$spring.datasource.username")
////    String username;
//
//    @Autowired
//    private Environment env;
//
//    public DataSource dataSource() {
//        String driverClass = env.getProperty("spring.datasource.driver-class-name");
//        String username = env.getProperty("spring.datasource.username");
//        String password = env.getProperty("spring.datasource.password");
//        String url = env.getProperty("spring.datasource.url");
//
//        return DataSourceBuilder.create().username(username).password(password).url(url).driverClassName(driverClass)
//
//                .build();
//    }
//
//    private HibernateJpaVendorAdapter vendorAdaptor() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setShowSql(true);
//        return vendorAdapter;
//    }
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
//        entityManagerFactoryBean.setDataSource(dataSource());
//        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
//
//        return entityManagerFactoryBean;
//    }
//
//}
