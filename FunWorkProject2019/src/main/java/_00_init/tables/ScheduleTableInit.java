package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Job;
import com.funwork.model.Schedule;


public class ScheduleTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public ScheduleTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initSchedul() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Schedule.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String endtime = token[0];
				String startime = token[1];
				String workdate = token[2];
				String jobId = token[3];
				
				Schedule schedule  = new Schedule();

				Date workdate1 = strToDate(workdate);
				Time endtime1 = strToTime(endtime);
				Time startime1 = strToTime(startime);
								
				schedule.setEndTime(endtime1);
				schedule.setStartTime(startime1);
				schedule.setWorkDate(workdate1);

//				Job job = session.get(Job.class, Integer.valueOf(jobId));
//				System.out.println(job);
//				schedule.setJob(job);
				session.save(schedule);
			}
			tx.commit();
			System.out.println("Schedule資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Schedule表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}
		
	}
	public Date strToDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}
	
		public Time strToTime(String strDate) {
			 String str = strDate;
			 SimpleDateFormat format =new SimpleDateFormat("hh:mm:ss");
			 java.util.Date d =null;
			 try{
			  d = format.parse(str);
			 }catch(Exception e) {
			  e.printStackTrace();
			 }
			 Time date =new java.sql.Time(d.getTime());
			 return date;
			}
}