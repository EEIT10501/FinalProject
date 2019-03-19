package init.tables;

import com.funwork.model.Job;
import com.funwork.model.Salary;
import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SalaryTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public SalaryTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Salary table.
   */
  public void initSalary() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Salary.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Salary salary = new Salary();
        salary.setHours(Float.parseFloat(token[0]));
        salary.setReviewComment(token[1]);
        salary.setReviewStatus(Integer.parseInt(token[2]));
        salary.setPaymentstatus(Integer.parseInt(token[3]));
        salary.setRating(Float.parseFloat(token[4]));
        salary.setJob(session.get(Job.class, Integer.valueOf(token[5])));
        salary.setUser(session.get(User.class, Integer.valueOf(token[6])));
        session.save(salary);
      }
      tx.commit();
      logger.info("Salary資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Salary表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}