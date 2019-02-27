package _00_init;

import org.hibernate.SessionFactory;

import _00_init.tables.NotificationTableInit;
import _00_init.util.HibernateUtils;

public class DatabaseInit {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		new NotificationTableInit(factory).initNotificaion();

		factory.close();
	}
}
