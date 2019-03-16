package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Application;
import com.funwork.model.Message;
import com.funwork.model.User;

public class MessageTableInit {

  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public MessageTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  public void initMessage() {

    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/Message.dat"); BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        String content = token[0];
        String time = token[1];
        String status = token[2];
        String receiverId = token[3];
        String senderId = token[4];
        String applicationId = token[5];
        Message message = new Message();
        message.setContent(content);
        message.setTime(Timestamp.valueOf(time));
        message.setStatus(Integer.valueOf(status));
        User receiver = session.get(User.class, Integer.valueOf(receiverId));
        message.setReceiver(receiver);
        User sender = session.get(User.class, Integer.valueOf(senderId));
        message.setSender(sender);
        Application application = session.get(Application.class, Integer.valueOf(applicationId));
        message.setApplication(application);
        session.save(message);
      }
      tx.commit();
      System.out.println("Message資料新增成功");
    } catch (Exception e) {
      System.err.println("新建Message表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}