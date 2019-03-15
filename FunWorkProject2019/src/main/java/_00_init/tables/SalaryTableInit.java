package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Job;
import com.funwork.model.Salary;
import com.funwork.model.User;

public class SalaryTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public SalaryTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initSalary() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Salary.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String hours = token[0];
				String reviewComment = token[1];
				String reviewStatus = token[2];
				String paymentStatus = token[3];
				String rating = token[4];
				String jobId = token[5];
				String userId = token[6];

				Salary salary = new Salary();

				salary.setHours(Float.parseFloat(hours));
				salary.setReviewComment(reviewComment);
				salary.setReviewStatus(Integer.parseInt(reviewStatus));
				salary.setPaymentstatus(Integer.parseInt(paymentStatus));
				salary.setRating(Float.parseFloat(rating));

				Job job = session.get(Job.class, Integer.valueOf(jobId));
				User user = session.get(User.class, Integer.valueOf(userId));
				salary.setJob(job);
				salary.setUser(user);

				session.save(salary);
			}
			tx.commit();
			System.out.println("Salary資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Salary表格時發生例外: " + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
		}

	}

	public java.sql.Date strToDate(String strDate) {
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

	public java.sql.Time strToTime(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		java.util.Date d = null;
		try {
			d = format.parse(str);
			return new java.sql.Time(d.getTime());
		} catch (Exception e) {
			System.out.println("SalaryTableInit strToTime()發生錯誤");
		}
		return null;
	}
}