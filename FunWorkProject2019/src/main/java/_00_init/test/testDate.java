package _00_init.test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Company;
import com.funwork.model.User;

import _00_init.util.HibernateUtils;

public class testDate {
	
	public static void main(String[] args) {
//		String str = "2019-09-16";
//		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date d = stf.parse(str);
//			System.out.println(d);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(new java.util.Date());
//		System.out.println(new Time(System.currentTimeMillis()));
//		System.out.println(new Timestamp(System.currentTimeMillis()));
//		System.out.println(new java.sql.Date(System.currentTimeMillis()));
//	
//		SessionFactory factory = HibernateUtils.getSessionFactory();
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			Company cb = new Company();
//			User ub = session.get(User.class, 0);
//			cb.setName("checkfornullentry");
//			cb.setTaxId("123GGG456AA7");
//			cb.setReviewStatus(3);
//			cb.setNotificationTimes(3);
//			cb.setUser(ub);
//			session.save(cb);
//			tx.commit();
//		} catch (Exception e) {
//			if(tx!=null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
//		
//	
	}
}
