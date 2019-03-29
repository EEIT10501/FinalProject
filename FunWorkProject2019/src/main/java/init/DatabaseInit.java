package init;

import init.tables.ApplicationTableInit;
import init.tables.CityTableInit;
import init.tables.CompanyTableInit;
import init.tables.ComplaintTableInit;
import init.tables.InterviewTableInit;
import init.tables.JobTableInit;
import init.tables.NotificationTableInit;
import init.tables.OrderTableInit;
import init.tables.ProductTableInit;
import init.tables.ResumeTableInit;
import init.tables.ScheduleTableInit;
import init.tables.SuggestionTableInit;
import init.tables.UserTableInit;
import init.util.HibernateUtils;
import org.hibernate.SessionFactory;

public class DatabaseInit {

  /**
   * Initialize All table.
   */
  public static void main(String[] args) {
    SessionFactory factory = HibernateUtils.getSessionFactory();
    new UserTableInit(factory).initUser();
//    new CompanyTableInit(factory).initCompany();
    new ResumeTableInit(factory).initResume();
//    new NotificationTableInit(factory).initNotification();
    new CityTableInit(factory).initCity();
//    new JobTableInit(factory).initJob();
//    new ComplaintTableInit(factory).initComplaint();
//    new ApplicationTableInit(factory).initApplicatoin();
//    new InterviewTableInit(factory).initInterview();
//    new ScheduleTableInit(factory).initSchedule();
    new ProductTableInit(factory).initProduct();
    new OrderTableInit(factory).initOrder();
//    new SuggestionTableInit(factory).initSuggestion();
    factory.close();
  }
}