package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Application;
import com.funwork.model.Interview;

public class InterviewTableInit {

	public static final String UTF8_BOM = "\uFEFF";
	SessionFactory factory;
	String line = "";

	public InterviewTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initInterview() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Interview.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				Interview interview = new Interview();
				interview.setInterviewComment(token[0]);
				interview.setInterviewType(token[1]);
				interview.setInterviewPlace(token[2]);
				interview.setInterviewStatus(token[3]);
				interview.setInterviewTime(Timestamp.valueOf(token[4]));
				Application application = session.get(Application.class, Integer.valueOf(token[5]));
				interview.setApplication(application);
				session.save(interview);
			}
			System.out.println("Interview資料新增成功");
			tx.commit();
		} catch (Exception e) {
			System.err.println("新建Interview表格時發生例外: " + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}