package com.funwork.controller;

import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.Schedule;
import com.funwork.model.User;
import com.funwork.service.InterviewService;
import com.funwork.service.JobService;
import com.funwork.service.ScheduleService;
import com.funwork.service.UserService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {
  private static final String LOGIN_USER = "loginUser";
  private static final String REDIRECT_TO_INDEX = "redirect:/";
  private static final String SCHEDULE_CALENDAR = "schedule/ScheduleCalendar";
  private static final String WAGE_STAFF = "schedule/wageStaff";
  private static final String TITLE = "title";
  private static final String START = "start";
  private static final String INTERVIEW_LIST = "interviewList";
  private static final String POST_JOB_LIST = "postJobList";
  private static final String STAFF_SCHEDULES = "staffSchedules";
  private static final String WAGE_MANAGE = "schedule/wageManage";
  private static final String HH_MM_SS_00 = " 00:00:00";
  private static final String HH_MM_SS_23 = " 23:00:00";
  private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
  @Autowired
  ScheduleService scheduleService;
  @Autowired
  InterviewService interviewService;
  @Autowired
  UserService userService;
  @Autowired
  JobService jobService;

  /**
   * Get ScheduleCalendar page.
   */
  @GetMapping("/ScheduleCalendar")
  public String calendar(Model model, HttpSession session) {
    User user = (User) session.getAttribute(LOGIN_USER);
    if (user != null) {
      List<Job> list = jobService.findJobByUserId(user.getUserId());
      model.addAttribute("jobs", list);
      return SCHEDULE_CALENDAR;
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Save calendar.
   */
  @GetMapping("/ScheduleCalendar/{jobId}")
  public String calendarSave(Model model, @PathVariable("jobId") Integer jobId) {
    List<Schedule> scheduleList = scheduleService.getSchedulesByDate(jobId);
    JSONArray jsonArray = new JSONArray();
    JSONObject object = null;
    for (Schedule sj : scheduleList) {
      object = new JSONObject();
      object.put("id", sj.getScheduleId());
      object.put(TITLE, sj.getScheduleName());
      object.put(START, sj.getStartTime());
      object.put("end", sj.getEndTime());
      jsonArray.put(object);
    }
    List<Interview> interviewList = interviewService.findInterviewByAdmit(jobId);
    model.addAttribute("json", jsonArray);
    model.addAttribute(INTERVIEW_LIST, interviewList);

    return SCHEDULE_CALENDAR;
  }

  /**
   * Change calendar.
   */
  @GetMapping("/ScheduleCalendar/change/{jobId}")
  public String calendarChange(Model model, @PathVariable("jobId") Integer jobId) {
    model.addAttribute("change", true);
    List<Interview> interviewList = interviewService.findInterviewByAdmit(jobId);
    List<Schedule> scheduleList = scheduleService.getSchedulesByDate(jobId);
    JSONArray jsonArray = new JSONArray();
    JSONObject object = null;
    for (Schedule sj : scheduleList) {
      object = new JSONObject();
      object.put("id", sj.getScheduleId());
      object.put(TITLE, sj.getScheduleName());
      object.put(START, sj.getStartTime());
      object.put("end", sj.getEndTime());
      jsonArray.put(object);
    }
    model.addAttribute("json", jsonArray);
    model.addAttribute(INTERVIEW_LIST, interviewList);

    return SCHEDULE_CALENDAR;
  }

  /**
   * Insert schedule.
   */
  @PostMapping(value = "/ScheduleCalendar/save/{jobId}")
  public String calendarIntoSql(Model model, @RequestParam("scheduleJSONArray") String scheduleJson,
      @RequestParam("delString") String delString, @PathVariable("jobId") Integer jobId) {
    model.addAttribute("change", true);
    String[] delArray = delString.split(",");
    for (int i = 0; i < delArray.length; i++) {
      if (delArray[i].length() != 0) {
        scheduleService.deleteScheduleByPrimaryKey(Integer.parseInt(delArray[i]));
      }
    }

    JSONArray jsonArray = new JSONArray(scheduleJson);

    for (int i = 0; i < jsonArray.length(); i++) {
      Schedule schedule = new Schedule();
      JSONObject jsonObject = (JSONObject) jsonArray.get(i);
      if (!jsonObject.isNull("scheduleId")) {
        schedule.setScheduleId((Integer) jsonObject.get("scheduleId"));
      }
      schedule.setScheduleName((String) jsonObject.get("scheduleName"));
      String starttime = ((String) jsonObject.get("startTime")).replaceAll("[^(0-9),-:]", " ");

      schedule.setStartTime(Timestamp.valueOf(starttime));
      String endtime = ((String) jsonObject.get("endTime")).replaceAll("[^(0-9),-:]", " ");
      schedule.setEndTime(Timestamp.valueOf(endtime));
      float restTime = (float) ((Timestamp.valueOf(endtime).getTime() 
          - Timestamp.valueOf(starttime).getTime())
          / (1000 * 60 * 60.0) / 4.0 * 0.5);
      schedule.setRestHour(restTime);

      Interview interview = interviewService
          .findByAdmitJobUserName(jobId, (String) jsonObject.get("scheduleName"));
      schedule.setInterview(interview);

      scheduleService.insertSchedule(schedule);
    }

    return SCHEDULE_CALENDAR;
  }

  /**
   * Get schedule form.
   */
  @GetMapping(value = "/addSchedule")
  public String getScheduleForm(Model model) {
    Schedule schedule = new Schedule();
    model.addAttribute("schedule", schedule);
    return "schedule/addSchedule";
  }

  @PostMapping(value = "/addSchedule")
  public String addScheduleForm(@ModelAttribute("schedule") Schedule schedule) {
    scheduleService.insertSchedule(schedule);
    return SCHEDULE_CALENDAR;
  }

  /**
   * Manage wage.
   */
  @GetMapping("/wageManage")
  public String wageManage(Model model, HttpServletRequest req) {
    HttpSession session = req.getSession();
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      model.addAttribute("user", loginUser);
      List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
      model.addAttribute(POST_JOB_LIST, postJobList);
      return WAGE_MANAGE;
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Get wage by search condition.
   */
  @PostMapping(value = "/selectWage")
  public String selectWage(Model model, @RequestParam("jobId") Integer jobId, 
      @RequestParam("start") String start,
      @RequestParam("end") String end, HttpSession session) throws ParseException {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      if (start.equals("") || end.equals("")) {
        model.addAttribute("user", loginUser);
        List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
        model.addAttribute(POST_JOB_LIST, postJobList);
        return WAGE_MANAGE;
      }
      model.addAttribute("user", loginUser);
      model.addAttribute("btn1", start);
      model.addAttribute("btn2", end);
      List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
      model.addAttribute(POST_JOB_LIST, postJobList);

      String starte = start + HH_MM_SS_00;
      String ende = end + HH_MM_SS_23;

      // 設定日期格式
      SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
      // 進行轉換
      Timestamp startTime = null;
      Timestamp endTime = null;
      Date startD = sdf.parse(starte);
      Date endD = sdf.parse(ende);
      startTime = new Timestamp(startD.getTime());
      endTime = new Timestamp(endD.getTime());

      List<Schedule> admitScheduleList = scheduleService
          .getSchedulesByJobIdAndTime(jobId, startTime, endTime);
      model.addAttribute("admitScheduleList", admitScheduleList);
      return WAGE_MANAGE;
    } else {
      return REDIRECT_TO_INDEX;
    }

  }

  /**
   * Let staff get wage.
   */
  @GetMapping("/wageStaff")
  public String wageStaff(Model model, HttpSession session) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      model.addAttribute("user", loginUser);
      List<Schedule> staffSchedules = scheduleService.getSchedulesByUser(loginUser.getUserId());
      model.addAttribute(STAFF_SCHEDULES, staffSchedules);
      return WAGE_STAFF;
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Get wage by search condition.
   */
  @PostMapping(value = "/selectWageStaff")
  public String selectWageStaff(Model model, @RequestParam("start") String start, 
      @RequestParam("end") String end, HttpSession session) throws ParseException {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      if (start.equals("") || end.equals("")) {
        model.addAttribute("user", loginUser);
        List<Schedule> staffSchedules = scheduleService.getSchedulesByUser(loginUser.getUserId());
        model.addAttribute(STAFF_SCHEDULES, staffSchedules);
        return WAGE_STAFF;
      }

      model.addAttribute("user", loginUser);
      model.addAttribute("btn1", start);
      model.addAttribute("btn2", end);

      List<Schedule> staffSchedules = scheduleService.getSchedulesByUser(loginUser.getUserId());
      model.addAttribute(STAFF_SCHEDULES, staffSchedules);

      String starte = start + HH_MM_SS_00;
      String ende = end + HH_MM_SS_23;

      // 設定日期格式
      SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
      // 進行轉換
      Timestamp startTime = null;
      Timestamp endTime = null;
      Date startD = sdf.parse(starte);
      Date endD = sdf.parse(ende);
      startTime = new Timestamp(startD.getTime());
      endTime = new Timestamp(endD.getTime());

      List<Schedule> staffScheduleList = scheduleService
          .getSchedulesByNameAndTime(loginUser.getUserName(), startTime, endTime);
      model.addAttribute("staffScheduleList", staffScheduleList);
      return WAGE_STAFF;
    } else {
      return REDIRECT_TO_INDEX;
    }

  }

  /**
   * Let user check schedule.
   */
  @GetMapping("/UserSchedule")
  public String userCalendar(Model model, HttpSession session) {
    User user = (User) session.getAttribute(LOGIN_USER);

    if (user != null) {
      List<Schedule> scheduleList = scheduleService.getSchedulesByUser(user.getUserId());
      JSONArray jsonArray = new JSONArray();
      JSONObject object = null;
      for (Schedule sj : scheduleList) {
        object = new JSONObject();
        object.put("id", sj.getScheduleId());
        object.put(TITLE, sj.getScheduleName());
        object.put(START, sj.getStartTime());
        object.put("end", sj.getEndTime());
        jsonArray.put(object);
      }
      model.addAttribute("json", jsonArray);

      return "schedule/UserSchedule";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Show schedule by condition.
   */
  @GetMapping("/UserSchedule/Date/{time1}/{time2}")
  public String userCalendarDate(Model model, HttpSession session, 
      @PathVariable("time1") String time1, @PathVariable("time2") String time2) {

    User user = (User) session.getAttribute(LOGIN_USER);
    if (user != null) {
      List<Schedule> scheduleList = scheduleService.getUserScheduleByRange(user.getUserId(),
          java.sql.Date.valueOf(time1), java.sql.Date.valueOf(time2));
      JSONArray jsonArray = new JSONArray();
      JSONObject object = null;
      for (Schedule sj : scheduleList) {
        object = new JSONObject();
        object.put("id", sj.getScheduleId());
        object.put(TITLE, sj.getScheduleName());
        object.put(START, sj.getStartTime());
        object.put("end", sj.getEndTime());
        jsonArray.put(object);
      }
      model.addAttribute("json", jsonArray);

      return "schedule/UserSchedule";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Show schedule by condition.
   */
  @GetMapping("/ScheduleCalendar/{jobId}/{time1}/{time2}")
  public String jobCalendarDate(Model model, @PathVariable("jobId") Integer jobId, 
      @PathVariable("time1") String time1, @PathVariable("time2") String time2) {
    List<Interview> interviewList = interviewService.findInterviewByAdmit(jobId);
    List<Schedule> scheduleList = scheduleService
        .getJobSchedulesByRange(jobId, java.sql.Date.valueOf(time1),
        java.sql.Date.valueOf(time2));
    JSONArray jsonArray = new JSONArray();
    JSONObject object = null;
    for (Schedule sj : scheduleList) {
      object = new JSONObject();
      object.put("id", sj.getScheduleId());
      object.put(TITLE, sj.getScheduleName());
      object.put(START, sj.getStartTime());
      object.put("end", sj.getEndTime());
      jsonArray.put(object);
    }
    model.addAttribute("json", jsonArray);
    model.addAttribute(INTERVIEW_LIST, interviewList);

    return SCHEDULE_CALENDAR;
  }

  /**
   * Display Schedule on EXCEL.
   */
  @PostMapping(value = "showSchedule", produces = "application/vnd.ms-excel")
  public String displayScheduleExcel(Model model, @RequestParam("start") String start, 
      @RequestParam("end") String end,
      HttpSession session) throws ParseException {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      if (start.equals("") || end.equals("")) {
        model.addAttribute("user", loginUser);
        List<Schedule> staffSchedules = scheduleService.getSchedulesByUser(loginUser.getUserId());
        model.addAttribute(STAFF_SCHEDULES, staffSchedules);
        return WAGE_STAFF;
      } else {
        model.addAttribute("user", loginUser);

        String starte = start + HH_MM_SS_00;
        String ende = end + HH_MM_SS_23;

        // 設定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        // 進行轉換
        Timestamp startTime = null;
        Timestamp endTime = null;
        Date startD = sdf.parse(starte);
        Date endD = sdf.parse(ende);
        startTime = new Timestamp(startD.getTime());
        endTime = new Timestamp(endD.getTime());

        List<Schedule> staffScheduleList = scheduleService
            .getSchedulesByNameAndTime(loginUser.getUserName(), startTime,
            endTime);
        model.addAttribute("staffScheduleList", staffScheduleList);

        return "fileDownload/showSchedule";
      }

    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Display Schedule on EXCEL.
   */
  @PostMapping(value = "showSchedules", produces = "application/vnd.ms-excel")
  public String displayShedulesExcel(Model model, @RequestParam("jobId") Integer jobId,
      @RequestParam("start") String start, @RequestParam("end") String end, HttpServletRequest req)
      throws ParseException {
    HttpSession session = req.getSession();
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      if (start.equals("") || end.equals("")) {
        model.addAttribute("user", loginUser);
        List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
        model.addAttribute(POST_JOB_LIST, postJobList);
        return WAGE_MANAGE;
      }

      model.addAttribute("user", loginUser);
      List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
      model.addAttribute(POST_JOB_LIST, postJobList);
      String starte = start + HH_MM_SS_00;
      String ende = end + HH_MM_SS_23;

      // 設定日期格式
      SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
      // 進行轉換
      Timestamp startTime = null;
      Timestamp endTime = null;
      Date startD = sdf.parse(starte);
      Date endD = sdf.parse(ende);
      startTime = new Timestamp(startD.getTime());
      endTime = new Timestamp(endD.getTime());

      List<Schedule> admitScheduleList = scheduleService
          .getSchedulesByJobIdAndTime(jobId, startTime, endTime);
      model.addAttribute("admitScheduleList", admitScheduleList);
      return "fileDownload/showOtherSchedule";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }
}