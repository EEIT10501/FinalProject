package init.tables;

import com.funwork.model.Complaint;
import com.funwork.model.Job;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ComplaintTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ComplaintTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Complaint table.
   */
  public void initComplaint() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Complaint.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Complaint complaint = new Complaint();
        complaint.setContent(token[0]);
        complaint.setSubmitTime(Timestamp.valueOf(token[1]));
        complaint.setProcessTime(Timestamp.valueOf(token[2]));
        complaint.setType(token[3]);
        complaint.setStatus(token[4]);
        Job job = session.get(Job.class, Integer.valueOf(token[5]));
        complaint.setJob(job);
        session.save(complaint);
      }
      tx.commit();
      logger.info("Complaint資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Complaint表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}