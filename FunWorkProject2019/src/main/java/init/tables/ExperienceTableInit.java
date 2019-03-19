package init.tables;

import com.funwork.model.Experience;
import com.funwork.model.Resume;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ExperienceTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ExperienceTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Experience table.
   */
  public void initExperience() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Experience.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Experience experience = new Experience();
        experience.setCompany(token[0]);
        experience.setPosition(token[1]);
        experience.setTerm(token[2]);
        Resume resume = session.get(Resume.class, Integer.valueOf(token[3]));
        experience.setResume(resume);
        session.save(experience);
      }
      tx.commit();
      logger.info("Experience資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Experience表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}