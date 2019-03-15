package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Resume;
import com.funwork.model.User;

import _00_init.util.SystemUtils2018;

public class ResumeTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public ResumeTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initResume() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Resume.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String name = token[0];
				String phoneNum = token[1];
				String bitrh = token[2];
				String educationLevel = token[3];
				String profilePic = token[4];
				String selfIntro = token[5];
				String userId = token[6];
				Resume resume = new Resume();
				resume.setName(name);
				resume.setPhoneNum(phoneNum);
				resume.setBirth(Date.valueOf(bitrh));
				resume.setEducationLevel(educationLevel);
				Blob profilePicBlob = SystemUtils2018.fileToBlob(profilePic.trim());
				resume.setFileName(SystemUtils2018.extractFileName(profilePic.trim()));
				resume.setProfilePic(profilePicBlob);
				resume.setSelfIntro(selfIntro);
				User user = session.get(User.class, Integer.valueOf(userId));
				resume.setUser(user);
				session.save(resume);
			}
			tx.commit();
			System.out.println("Resume資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Resume表格時發生例外: " + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}