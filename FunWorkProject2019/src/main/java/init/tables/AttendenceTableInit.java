package init.tables;

import com.funwork.model.Attendence;
import com.funwork.model.Job;
import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Time;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AttendenceTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private static final String UTF8_BOM = "\uFEFF";
  private SessionFactory factory;

  public AttendenceTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Attendence table.
   */
  public void initAttendence() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Attendence.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Attendence attendence = new Attendence();
        attendence.setDate(Date.valueOf(token[0]));
        attendence.setTime(Time.valueOf(token[1]));
        attendence.setType(Integer.parseInt(token[2]));
        attendence.setDailySalary(Float.parseFloat(token[3]));
        Job job = session.get(Job.class, Integer.valueOf(token[4]));
        User user = session.get(User.class, Integer.valueOf(token[5]));
        attendence.setJob(job);
        attendence.setUser(user);
        session.save(attendence);
      }
      tx.commit();
      logger.info("Attendence資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Attendence表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}