package init.util;

import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
  public static final Logger logger = Logger.getLogger("com.funwork");

  private HibernateUtils() {
    throw new IllegalStateException("Utility class");
  }

  private static SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      // Hibernate 5.x 的寫法
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml")
          .build();

      Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    } catch (Exception ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  // 外界呼叫此靜態方法來取得 SessionFactory物件
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  // 外界呼叫此靜態方法來關閉 SessionFactory物件
  public static void close() {
    getSessionFactory().close();
  }

}