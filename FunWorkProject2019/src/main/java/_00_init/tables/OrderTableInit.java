package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.model.User;

public class OrderTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public OrderTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initOrder() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Order.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String time = token[0];
				String status = token[1];
				String price = token[2];
				String expirationDate = token[3];
				String userId = token[4];
				String productId = token[5];
				Order order = new Order();
				order.setTime(Timestamp.valueOf(time));
				order.setStatus(Integer.valueOf(status));
				order.setPrice(Double.valueOf(price));
				order.setExpirationDate(Date.valueOf(expirationDate));
				User user = session.get(User.class, Integer.valueOf(userId));
				order.setUser(user);
				Product product = session.get(Product.class, Integer.valueOf(productId));
				order.setProduct(product);
				session.save(order);
			}
			tx.commit();
			System.out.println("Order資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Order表格時發生例外: " + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}