package _00_init;

import org.hibernate.SessionFactory;
import _00_init.tables.AttendenceTableInit;
import _00_init.tables.MessageTableInit;
import _00_init.tables.NotificationTableInit;
import _00_init.tables.SalaryTableInit;
import _00_init.tables.ScheduleTableInit;
import _00_init.tables.OrderTableInit;
import _00_init.tables.ProductTableInit;
import _00_init.tables.ResumeTableInit;
import _00_init.tables.UserTableInit;
import _00_init.util.HibernateUtils;

public class DatabaseInit {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		new UserTableInit(factory).initUser();
		// 需要先有User
		new ResumeTableInit(factory).initResume();
		// 需要先有User
		new NotificationTableInit(factory).initNotificaion();
		// 需要先有Job
		new ScheduleTableInit(factory).initSchedule();
		// 需要先有User、Job
		new SalaryTableInit(factory).initSalary();
		// 需要先有User、Job
		new AttendenceTableInit(factory).initAttendence();

		new ProductTableInit(factory).initProduct();
		// 需要先有User、Product
		new OrderTableInit(factory).initOrder();
		// 需要先有User、Application
//		new MessageTableInit(factory).initMessage();

		factory.close();
	}
}