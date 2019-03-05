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

public class CompanyTableInit {
	
	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	
	public CompanyTableInit(SessionFactory factory) {
		this.factory=factory;
	}

	public void initCompany() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

			try (FileReader fr = new FileReader("data/user_Bom.dat"); BufferedReader br = new BufferedReader(fr);) {
				tx = session.beginTransaction();
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					String name = token[0];
					System.out.println(name);
				}
			} catch (IOException e) {
				System.err.println("新建User表格時發生IO例外: " + e.getMessage());
			}
			session.flush();
			System.out.println("User 資料新增成功");

			// 2. 由"data/company.dat"逐筆讀入Book表格內的初始資料，然後依序新增
			// 到Company表格中
			File file = new File("data/company.dat");
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					Company cb = new Company();
					cb.setName(token[0]);
					cb.setAddress(token[2]);
					Blob licBlob = SystemUtils2018.fileToBlob(token[3].trim());
					cb.setLicensure(licBlob);
					cb.setReviewStatus(token[4].trim());
					cb.setTaxId(token[1]);
					cb.setNotificationTimes(Integer.parseInt(token[6]));
					Blob covBlob = SystemUtils2018.fileToBlob(token[8].trim());
					cb.setCoverPic(covBlob);
					Blob logBlob = SystemUtils2018.fileToBlob(token[7].trim());
					cb.setLogo(logBlob);
					Clob clob = SystemUtils2018.fileToClob(token[9]);
					cb.setDescription(clob);
					cb.setSiteURL(token[10]);
					int userId = Integer.parseInt(token[5].trim());
					User ub = session.get(User.class, userId);
					cb.setUser(ub);
					session.save(cb);
					System.out.println("新增一筆Company紀錄成功");
				}
				// 印出資料新增成功的訊息
				tx.commit();
				System.out.println("Company資料新增成功");

		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}

	}

}