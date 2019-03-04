package _00_init.tables;

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
import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.User;

import _00_init.util.HibernateUtils;
import _00_init.util.SystemUtils2018;

public class ApplicationTableInit {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
	SessionFactory factory;
	String line = "";
	Transaction tx;

	public ApplicationTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initApplicatoin() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		

		File file = new File("data/Application.dat");
		try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);
				) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				System.out.println("line=" + line);
				// 去除 UTF8_BOM: \uFEFF
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				Application ab = new Application();
				ab.setAnswer(token[0]);
				ab.setApplicationTime(new Date());
				ab.setAppliedStatus("已邀約");
				Integer jobNumber = Integer.valueOf(token[3]);
				Job job = session.get(Job.class, jobNumber);
				ab.setJob(job);
				Integer userNumber = Integer.valueOf(token[4]);
				User ub = session.get(User.class,userNumber);
				ab.setUser(ub);
				System.out.println("新增一筆Application紀錄成功");
				session.save(ab);
			}
			System.out.println("Application Table 新增成功");
			tx.commit();
	}catch(	Exception e)
	{
		System.err.println("新建表格時發生例外: " + e.getMessage());
		e.printStackTrace();
		tx.rollback();
	}
	}
}