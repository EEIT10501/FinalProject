package _00_init;

import org.hibernate.SessionFactory;

import _00_init.tables.AttendenceTableInit;
import _00_init.tables.NotificationTableInit;
import _00_init.tables.SalaryTableInit;
//import _00_init.tables.NotificationTableInit;
import _00_init.tables.ScheduleTableInit;
import _00_init.util.HibernateUtils;

public class DatabaseInit {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		new NotificationTableInit(factory).initNotificaion();
		
		new ScheduleTableInit(factory).initSchedul();
		
		new SalaryTableInit(factory).initSalary();	
		
		new AttendenceTableInit(factory).initAttendence();	
		
		factory.close();
	}
}
