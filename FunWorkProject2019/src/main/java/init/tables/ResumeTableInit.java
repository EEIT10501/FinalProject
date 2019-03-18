package init.tables;

import com.funwork.model.Resume;
import com.funwork.model.User;
import init.util.SystemUtils2018;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ResumeTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ResumeTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Resume table.
   */
  public void initResume() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Resume.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Resume resume = new Resume();
        resume.setName(token[0]);
        resume.setPhoneNum(token[1]);
        resume.setBirth(Date.valueOf(token[2]));
        resume.setEducationLevel(token[3]);
        resume.setFileName(SystemUtils2018.extractFileName(token[4].trim()));
        resume.setProfilePic(SystemUtils2018.fileToBlob(token[4].trim()));
        resume.setSelfIntro(token[5]);
        resume.setUser(session.get(User.class, Integer.valueOf(token[6])));
        session.save(resume);
      }
      tx.commit();
      logger.info("Resume資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Resume表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}