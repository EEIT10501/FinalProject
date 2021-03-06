package init.tables;

import com.funwork.model.City;
import com.funwork.model.Company;
import com.funwork.model.Job;
import com.funwork.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class JobTableInit {
  Logger logger = Logger.getLogger("com.funwork");
  private SessionFactory factory;
  private static final String UTF8_BOM = "\uFEFF";

  public JobTableInit(SessionFactory factory) {
    this.factory = factory;
  }

  /**
   * Initialize Job table.
   */
  public void initJob() {
    Session session = factory.getCurrentSession();
    Transaction tx = null;
    String line = "";
    try (FileReader fr = new FileReader("data/Job.dat"); 
         BufferedReader br = new BufferedReader(fr);) {
      tx = session.beginTransaction();
      while ((line = br.readLine()) != null) {
        if (line.startsWith(UTF8_BOM)) {
          line = line.substring(1);
        }
        String[] token = line.split("\\|");
        String address = token[0];
        String addresssup = token[1];
        String comment = token[2];
        String isExposure = token[3];
        String isFilled = token[4];
        String postEndDate = token[5];
        String reviewStatus = token[6];
        String title = token[7];
        String viewTimes = token[8];
        String city = token[9];
        String contact = token[10];
        String description = token[11];
        String jobEmail = token[12];
        String industry = token[13];
        String other = token[14];
        String paidDate = token[15];
        String jobPhone = token[16];
        String positionNum = token[17];
        String rateByHour = token[18];
        String jobOwner = token[19];
        String jobCompany = token[20];
        String submitTime = token[21];
        String reviewTime = token[22];
        String lat = token[23];
        String lng = token[24];
        String workDate = token[25];
        String workTime = token[26];
        Job job = new Job();
        job.setAddress(address);
        job.setAddresssup(addresssup);
        job.setComment(comment);
        job.setIsExposure(Boolean.valueOf(isExposure));
        job.setIsFilled(Boolean.valueOf(isFilled));
        job.setPostEndDate(Date.valueOf(postEndDate));
        job.setReviewStatus(reviewStatus);
        job.setTitle(title);
        job.setViewTimes(Integer.valueOf(viewTimes));
        City cityname = session.get(City.class, Integer.valueOf(city));
        job.setCity(cityname);
        job.setContact(contact);
        job.setDescription(description);
        job.setJobEmail(jobEmail);
        job.setIndustry(industry);
        job.setOther(other);
        job.setPaidDate(paidDate);
        job.setJobPhone(jobPhone);
        job.setPositionNum(Integer.valueOf(positionNum));
        job.setRateByHour(Integer.valueOf(rateByHour));
        User userid = session.get(User.class, Integer.valueOf(jobOwner));
        job.setJobOwner(userid);
        Company companyid = session.get(Company.class, Integer.valueOf(jobCompany));
        job.setJobCompany(companyid);
        job.setSubmitTime(Timestamp.valueOf(submitTime));
        job.setReviewTime(Timestamp.valueOf(reviewTime));
        job.setJobLat(lat);
        job.setJobLng(lng);
        job.setWorkDate(workDate);
        job.setWorkTime(workTime);
        session.save(job);
      }
      tx.commit();
      logger.info("Job資料新增成功");
    } catch (Exception e) {
      logger.warning("新建Job表格時發生例外: " + e.getMessage());
      if (tx != null) {
        tx.rollback();
      }
    }
  }
}