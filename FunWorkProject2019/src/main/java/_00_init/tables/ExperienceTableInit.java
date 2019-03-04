package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Experience;
import com.funwork.model.Resume;

public class ExperienceTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public ExperienceTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initExperience() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Experience.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String company = token[0];
				String position = token[1];
				String term = token[2];
				String resumeId = token[3];
	
				Experience experience = new Experience();
				experience.setCompany(company);
				experience.setPosition(position);
				experience.setTerm(term);
				Resume resume = session.get(Resume.class, Integer.valueOf(resumeId));
				experience.setResume(resume);
				session.save(experience);
			}
			tx.commit();
			System.out.println("Experience資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Experience表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}
	}
}