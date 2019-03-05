package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Attendence;
import com.funwork.model.Job;
import com.funwork.model.User;

public class AttendenceTableInit {

	public static final String UTF8_BOM = "\uFEFF";
	SessionFactory factory;
	String line = "";

	public AttendenceTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initAttendence() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Attendence.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String date = token[0];
				String time = token[1];
				String type = token[2];
				String dailySalary = token[3];
				String jobId = token[4];
				String userId = token[5];

				Attendence attendence = new Attendence();

				Date date1 = strToDate(date);
				Time time1 = strToTime(time);

				attendence.setDate(date1);
				attendence.setTime(time1);
				attendence.setType(Integer.parseInt(type));
				attendence.setDailySalary(Float.parseFloat(dailySalary));

				Job job = session.get(Job.class, Integer.valueOf(jobId));
				User user = session.get(User.class, Integer.valueOf(userId));

				attendence.setJob(job);
				attendence.setUser(user);
				session.save(attendence);
			}
			tx.commit();
			System.out.println("Attendence資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Attendence表格時發生例外: " + e.getMessage());
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
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Time date = new java.sql.Time(d.getTime());
		return date;
	}
}