package init.tables;

import com.funwork.model.Job;
import com.funwork.model.Schedule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ScheduleTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ScheduleTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Schedule table.
   */
  public void initSchedule() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Schedule.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        String scheduleName = token[0];
        String color = token[1];
        String endtime = token[2];
        String startime = token[3];
        String restHour = token[4];
        String workdate = token[5];
        String jobId = token[6];
        Schedule schedule = new Schedule();
        Float restHour1 = Float.parseFloat(restHour);
        schedule.setScheduleName(scheduleName);
        schedule.setColor(color);
        schedule.setEndTime(Timestamp.valueOf(endtime));
        schedule.setStartTime(Timestamp.valueOf(startime));
        schedule.setRestHour(restHour1);
        schedule.setWorkDate(Date.valueOf(workdate));
        Job job = session.get(Job.class, Integer.valueOf(jobId));
        schedule.setJob(job);
        session.save(schedule);
      }
      tx.commit();
      logger.info("Schedule資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Schedule表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}