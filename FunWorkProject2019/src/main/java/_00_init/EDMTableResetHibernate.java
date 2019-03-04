package _00_init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Application;
import com.funwork.model.Company;
import com.funwork.model.Experience;
import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.Resume;
import com.funwork.model.User;

import _00_init.util.HibernateUtils;
import _00_init.util.SystemUtils2018;

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {

		String line = "";

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Experience.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();


			// 1. 由"data/user.dat"逐筆讀入User表格內的初始資料，
			// 然後依序新增到User表格中
			try (FileReader f = new FileReader("data/user_Bom.dat"); BufferedReader b = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					String name = token[0];
					System.out.println(name);
//					User ub = new User(null, name);
//					session.save(ub);

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