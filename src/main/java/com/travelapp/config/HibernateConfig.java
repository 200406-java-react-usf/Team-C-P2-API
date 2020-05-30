//package com.travelapp.config;
//
//import com.travelapp.models.User;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.reflections.Reflections;
//
//
//import javax.persistence.Entity;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.Set;
//
//public class HibernateConfig {
//
//    private static SessionFactory sessionFactory;
//
//    private static Properties props = new Properties();
//
//    static {
//        try {
//            props.load(new FileReader("hibernate-demo/src/main/resources/app.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static SessionFactory buildSessionFactory() {
//
//        if (sessionFactory == null) {
//
//            System.out.println("Instantiating SessionFactory...");
//
//            try {
//
//                Configuration config = new Configuration();
//                config.setProperties(props);
//
//                assignAnnotatedClasses(config);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(config.getProperties())
//                        .build();
//
//                sessionFactory = config.buildSessionFactory(serviceRegistry);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("SessionFactory instantiation complete!");
//
//        }
//
//        return sessionFactory;
//
//    }
//
//    private static void assignAnnotatedClasses(Configuration config) {
//        Reflections reflect = new Reflections("com.revature.quizzard.entities");
//        Set<Class<? extends Object>> entities = reflect.getTypesAnnotatedWith(Entity.class);
//        entities.forEach(config::addAnnotatedClass);
//    }
//
//}
