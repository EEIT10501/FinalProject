package init.tables;

import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ApplicationTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private static final String UTF8_BOM = "\uFEFF";
  private SessionFactory factory;
  
  public ApplicationTableInit(SessionFactory factory) {
    this.factory = factory;
  }
  
  /**
   * Initialize Application table.
   */
  public void initApplicatoin() {
    Session session = factory.getCurrentSession();
    String line = "";
    Transaction tx = null;
    try (FileReader fr = new FileReader("data/Application.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Application ab = new Application();
        ab.setAnswer(token[0]);
        ab.setApplicationTime(Timestamp.valueOf(token[1]));
        ab.setAppliedStatus(token[2]);
        Integer jobId = Integer.valueOf(token[3]);
        Job job = session.get(Job.class, jobId);
        ab.setJob(job);
        Integer userId = Integer.valueOf(token[4]);
        User ub = session.get(User.class, userId);
        ab.setUser(ub);
        session.save(ab);
      }
      logger.info("Application資料新增成功");
      tx.commit();
    } catch (Exception e) {
      logger.warning("新建Application表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}