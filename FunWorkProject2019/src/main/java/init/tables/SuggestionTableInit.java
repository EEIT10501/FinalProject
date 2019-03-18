package init.tables;

import com.funwork.model.Suggestion;
import init.util.SystemUtils2018;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SuggestionTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public SuggestionTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Suggestion table.
   */
  public void initSuggestion() {

    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/Suggestion.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        String comment = token[0];
        String email = token[1];
        String submitTime = token[2];
        String attachment = token[3];
        Suggestion suggestion = new Suggestion();
        suggestion.setComment(comment);
        suggestion.setEmail(email);
        suggestion.setSubmitTime(Timestamp.valueOf(submitTime));
        Blob attachmentBlob = SystemUtils2018.fileToBlob(attachment.trim());
        suggestion.setAttachment(attachmentBlob);
        session.save(suggestion);
      }
      tx.commit();
      logger.info("Suggestion資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Suggestion表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}