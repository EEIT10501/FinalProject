package _00_init;

import org.hibernate.SessionFactory;

import _00_init.tables.MessageTableInit;
import _00_init.tables.NotificationTableInit;
import _00_init.tables.OrderTableInit;
import _00_init.tables.ProductTableInit;
import _00_init.util.HibernateUtils;

public class DatabaseInit {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		// 需要先有User
		new NotificationTableInit(factory).initNotificaion();
		new ProductTableInit(factory).initProduct();
		// 需要先有User、Product
		new OrderTableInit(factory).initOrder();
		// 需要先有User、Application
		new MessageTableInit(factory).initMessage();

		factory.close();
	}
}
