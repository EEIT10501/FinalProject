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
import com.funwork.model.Interview;
import com.funwork.model.Job;
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

		try {
			tx = session.beginTransaction();

			// 1. 由"data/user.dat"逐筆讀入User表格內的初始資料，
			// 然後依序新增到User表格中
			try (FileReader fr = new FileReader("data/user_Bom.dat"); BufferedReader br = new BufferedReader(fr);) {
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
					cb.setReviewStatus(Integer.parseInt(token[4].trim()));
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
				session.flush();
				System.out.println("Company資料新增成功");
			}

			// 3. Member表格
			// 由"data/Input.txt"逐筆讀入Member表格內的初始資料，
			// 然後依序新增到Member表格中
//			try (
//				FileInputStream fis = new FileInputStream("data/Input.txt");
//				InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
//				BufferedReader br = new BufferedReader(isr0);
//			) {
//				while ((line = br.readLine()) != null) {
//					String[] sa = line.split("\\|");
//					MemberBean bean = new MemberBean();
//					bean.setMemberId(sa[0]);
//					bean.setName(sa[1]);
//					String pswd = GlobalService.getMD5Endocing(GlobalService.encryptString(sa[2]));
//					bean.setPassword(pswd);
//					bean.setAddress(sa[3]);
//					bean.setEmail(sa[4]);
//					bean.setTel(sa[5]);
//					bean.setUserType(sa[6]);
//					bean.setTotalAmt(Double.parseDouble(sa[7]));
//					bean.setTs(new java.sql.Timestamp(System.currentTimeMillis()));
//					// --------------處理Blob(圖片)欄位----------------
//					Blob sb = SystemUtils2018.fileToBlob(sa[8]);
//					bean.setMemberImage(sb);
//					String imageFileName = sa[8].substring(sa[8].lastIndexOf("/") + 1);
//					bean.setFileName(imageFileName);
//					Clob clob = SystemUtils2018.fileToClob(sa[9]);
//					bean.setComment(clob);
//					bean.setUnpaid_amount(0.0);
//					session.save(bean);
//					count++;
//					System.out.println("新增" + count + "筆記錄:" + sa[1]);
//				}
//				session.flush();
//				System.out.println("Member表格資料新增成功");
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}

//		 嘗試新增3份工作
			Job jb = new Job();
			Job jb2 = new Job();
			Job jb3 = new Job();

			User ub = session.get(User.class, 1);
			Company cb = session.get(Company.class, 1);
			Company cb2 = session.get(Company.class, 2);

			jb.setTitle("業務經理");
			jb2.setTitle("專案經理");
			jb3.setTitle("資深研究員");

			// 同一人才招募
			jb.setJobOwner(ub);
			jb2.setJobOwner(ub);
			jb3.setJobOwner(ub);

			// 代表不同的公司
			jb.setJobCompany(cb);
			jb2.setJobCompany(cb2);
			jb3.setJobCompany(cb2);

			session.save(jb);
			session.save(jb2);
			session.save(jb3);

			Application ab = new Application();

			// 嘗試新增一筆投遞紀錄
			ab.setApplicationTime(new Date());
			ab.setAnswer("沒有問題");
			ab.setAppliedStatus("已邀約");
			ab.setJob(jb);
			// 由第四名user投遞
			User u4 = session.get(User.class, 4);
			ab.setUser(u4);

			session.save(ab);

			// 嘗試新增2 筆面試紀錄
			SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
			Interview ib = new Interview();
			ib.setInterviewPlace("Taipei");
			ib.setInterviewStatus(false);
			ib.setInterviewTime(stf.parse("2019-03-01"));
			ib.setInterviewType("現場");
			Interview ib2 = new Interview();
			ib2.setInterviewPlace("Taipei");
			ib2.setInterviewStatus(true);
			ib2.setInterviewTime(stf.parse("2019-02-16"));
			ib2.setInterviewType("電話");
			Application app = session.get(Application.class, 1);
			ib.setApplication(app);
			ib2.setApplication(app);

			session.save(ib);
			session.save(ib2);

			tx.commit();
		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}
		factory.close();

	}

}