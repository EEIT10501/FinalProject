package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Notification;
import com.funwork.model.User;

public class NotificationTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public NotificationTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initNotificaion() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Notification.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String content = token[0];
				String time = token[1];
				String type = token[2];
				String relatedUserId = token[3];
				String userId = token[4];
				Notification notification = new Notification();
				notification.setContent(content);
				notification.setTime(Timestamp.valueOf(time));
				notification.setType(Integer.valueOf(type));
				if (!relatedUserId.equals("")) {
					User relatedUser = session.get(User.class, Integer.valueOf(relatedUserId));
					notification.setRelatedUser(relatedUser);
				}
				User user = session.get(User.class, Integer.valueOf(userId));
				notification.setUser(user);
				session.save(notification);
			}
			tx.commit();
			System.out.println("Notification資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Notification表格時發生例外: " + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}