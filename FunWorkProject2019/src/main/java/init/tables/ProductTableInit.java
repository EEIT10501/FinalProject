package init.tables;

import com.funwork.model.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ProductTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Product table.
   */
  public void initProduct() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Product.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Product product = new Product();
        product.setProductName(token[0]);
        product.setDescription(token[1]);
        product.setPrice(Integer.valueOf(token[2]));
        session.save(product);
      }
      tx.commit();
      logger.info("Product資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Product表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}