package _00_init.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.funwork.model.Complaint;
import com.funwork.model.Job;

public class ComplaintTableInit {

  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public ComplaintTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  public void initSuggestion() {

    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";

    try (FileReader fr = new FileReader("data/Complaint.dat"); BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        String content = token[0];
        String submitTime = token[1];
        String processTime = token[2];
        String type = token[3];
        String status = token[4];
        String jobId = token[5];

        Complaint complaint = new Complaint();

        complaint.setContent(content);
        complaint.setSubmitTime(Timestamp.valueOf(submitTime));
        complaint.setProcessTime(Timestamp.valueOf(processTime));
        complaint.setType(type);
        complaint.setStatus(status);
        Job job = session.get(Job.class, Integer.valueOf(jobId));
        complaint.setJob(job);

        session.save(complaint);
      }
      tx.commit();
      System.out.println("Complaint資料新增成功");
    } catch (Exception e) {
      System.err.println("新建Complaint表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}