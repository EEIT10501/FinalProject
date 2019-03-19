package init.tables;

import com.funwork.model.Application;
import com.funwork.model.Message;
import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MessageTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public MessageTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Message table.
   */
  public void initMessage() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/Message.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        Message message = new Message();
        message.setContent(token[0]);
        message.setTime(Timestamp.valueOf(token[1]));
        message.setStatus(Integer.valueOf(token[2]));
        message.setReceiver(session.get(User.class, Integer.valueOf(token[3])));
        message.setSender(session.get(User.class, Integer.valueOf(token[4])));
        message.setApplication(session.get(Application.class, Integer.valueOf(token[5])));
        session.save(message);
      }
      tx.commit();
      logger.info("Message資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Message表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}