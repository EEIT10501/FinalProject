package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Product;

public class ProductTableInit {

	SessionFactory factory;
	String line = "";
	public static final String UTF8_BOM = "\uFEFF";

	public ProductTableInit(SessionFactory factory) {
		this.factory = factory;
	}

	public void initProduct() {

		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try (FileReader fr = new FileReader("data/Product.dat"); BufferedReader br = new BufferedReader(fr);) {
			tx = session.beginTransaction();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				String name = token[0];
				String description = token[1];
				String price = token[2];
				Product product = new Product();
				product.setName(name);
				product.setDescription(description);
				product.setPrice(Double.valueOf(price));
				session.save(product);
			}
			tx.commit();
			System.out.println("Product資料新增成功");
		} catch (Exception e) {
			System.err.println("新建Product表格時發生例外: " + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}