package init.tables;

import com.funwork.model.City;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class CityTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public CityTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize City table.
   */
  public void initCity() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/City.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        City city = new City();
        city.setCityName(token[0]);
        city.setCityArea(token[1]);
        session.save(city);
      }
      tx.commit();
      logger.info("City資料新增成功");
    } catch (Exception e) {
      logger.warning("新建City表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}