package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Job;
import com.funwork.model.Schedule;

public class ScheduleTableInit {

  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ScheduleTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  public void initSchedule() {

    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/Schedule.dat"); BufferedReader br = new BufferedReader(fr);) {
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

        Date workdate1 = strToDate(workdate);
        Timestamp endtime1 = Timestamp.valueOf(endtime);
        Timestamp startime1 = Timestamp.valueOf(startime);
        Float restHour1 = Float.parseFloat(restHour);

        schedule.setScheduleName(scheduleName);
        schedule.setColor(color);
        schedule.setEndTime(endtime1);
        schedule.setStartTime(startime1);
        schedule.setRestHour(restHour1);
        schedule.setWorkDate(workdate1);

        Job job = session.get(Job.class, Integer.valueOf(jobId));
        schedule.setJob(job);
        session.save(schedule);
      }
      tx.commit();
      System.out.println("Schedule資料新增成功");
    } catch (Exception e) {
      System.err.println("新建Schedule表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }

  }

  private Date strToDate(String strDate) {
    String str = strDate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date d = null;
    try {
      d = format.parse(str);
      return new java.sql.Date(d.getTime());
    } catch (Exception e) {
      System.out.println("SalaryTableInit strToDate()發生錯誤");
    }
    return null;
  }

}