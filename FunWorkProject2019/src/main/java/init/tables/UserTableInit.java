package init.tables;

import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public UserTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize User table.
   */
  public void initUser() {

    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/User.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        String userName = token[0];
        String password = token[1];
        String phoneNum = token[2];
        String email = token[3];
        String mebershipLevel = token[4];
        String exposureLimit = token[5];
        String jobPostLimit = token[6];
        String jobPostPeriod = token[7];
        String rating = token[8];
        String role = token[9];
        String abscence = token[10];
        String facebook = token[11];
        String google = token[12];
        String isOpen = token[13];
        String vipEndDate = token[14];
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setMebershipLevel(Integer.valueOf(mebershipLevel));
        user.setExposureLimit(Integer.valueOf(exposureLimit));
        user.setJobPostLimit(Integer.valueOf(jobPostLimit));
        user.setJobPostPeriod(Integer.valueOf(jobPostPeriod));
        user.setRating(Double.valueOf(rating));
        user.setRole(Integer.valueOf(role));
        user.setAbscence(Integer.valueOf(abscence));
        user.setFacebook(facebook);
        user.setGoogle(google);
        user.setIsOpen(Boolean.valueOf(isOpen));
        user.setVipEndDate(Date.valueOf(vipEndDate));
        session.save(user);
      }
      tx.commit();
      logger.info("User資料新增成功");
    } catch (Exception e) {
      logger.warning("新建User表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}