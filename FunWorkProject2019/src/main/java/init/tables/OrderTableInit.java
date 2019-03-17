package init.tables;

import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OrderTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public OrderTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Order table.
   */
  public void initOrder() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Order.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Order order = new Order();
        order.setTime(Timestamp.valueOf(token[0]));
        order.setStatus(Integer.valueOf(token[1]));
        order.setPrice(Double.valueOf(token[2]));
        order.setExpirationDate(Date.valueOf(token[3]));
        order.setUser(session.get(User.class, Integer.valueOf(token[4])));
        order.setProduct(session.get(Product.class, Integer.valueOf(token[5])));
        session.save(order);
      }
      tx.commit();
      logger.info("Order資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Order表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}