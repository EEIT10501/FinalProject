package com.funwork.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.model.Schedule;
import com.funwork.model.User;
import com.funwork.model.Job;
import com.funwork.service.InterviewService;
import com.funwork.service.JobService;
import com.funwork.service.ScheduleService;
import com.funwork.service.UserService;

@Controller
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	InterviewService interviewService;

	@Autowired
	UserService userService;

	@Autowired
	JobService jobService;

	public ScheduleController() {
	}

	@RequestMapping("/calendar")
	public String calendar() {
		return "schedule/calendar";
	}

	@RequestMapping("/ScheduleCalendar")
	public String calendarSave(Model model) {

		int jobId = 1; // 測試用
		List<Interview> interviewList = interviewService.findInterviewByAdmit(jobId);

		List<Schedule> scheduleList = scheduleService.getSchedulesByDate(jobId);
		JSONArray jsonArray = new JSONArray();
		JSONObject object = null;
		for (Schedule sj : scheduleList) {
			object = new JSONObject();
			object.put("id", sj.getScheduleId());
			object.put("title", sj.getScheduleName());
			object.put("start", sj.getStartTime());
			object.put("end", sj.getEndTime());
			jsonArray.put(object);
		}
		System.out.println(jsonArray);
		model.addAttribute("json", jsonArray);
		model.addAttribute("interviewList", interviewList);

		return "schedule/ScheduleCalendar";
	}

	@RequestMapping("/ScheduleCalendar/change")
	public String calendarChange(Model model) {
		model.addAttribute("change", true);

		int jobId = 1; // 測試用
		List<Interview> interviewList = interviewService.findInterviewByAdmit(jobId);

		List<Schedule> scheduleList = scheduleService.getSchedulesByDate(jobId);
		JSONArray jsonArray = new JSONArray();
		JSONObject object = null;
		for (Schedule sj : scheduleList) {
			object = new JSONObject();
			object.put("id", sj.getScheduleId());
			object.put("title", sj.getScheduleName());
			object.put("start", sj.getStartTime());
			object.put("end", sj.getEndTime());
			jsonArray.put(object);
		}
		model.addAttribute("json", jsonArray);
		model.addAttribute("interviewList", interviewList);

		return "schedule/ScheduleCalendar";
	}

	@RequestMapping(value = "/ScheduleCalendar/save", method = RequestMethod.POST)
	public String calendarIntoSql(Model model, @RequestParam("scheduleJSONArray") String scheduleJSON,
			@RequestParam("delString") String delString) throws JSONException, ParseException {
		model.addAttribute("change", true);

//		System.out.println(scheduleJSON);
		System.out.println(delString);
//		String[] delArray = delString.replaceAll(","," ").trim().split("\\s+");
		String[] delArray = delString.split(",");
		for (int i = 0; i < delArray.length; i++) {
			if (delArray[i].length() != 0) {
//				System.out.println(delArray[i]);
				scheduleService.deleteScheduleByPrimaryKey(Integer.parseInt(delArray[i]));
			}
		}

		JSONArray jsonArray = new JSONArray(scheduleJSON);

		System.out.println(jsonArray);
		for (int i = 0; i < jsonArray.length(); i++) {
			Schedule schedule = new Schedule();
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			if (jsonObject.isNull("scheduleId") == false) {
				schedule.setScheduleId((Integer) jsonObject.get("scheduleId"));
			}
			schedule.setScheduleName((String) jsonObject.get("scheduleName"));
			String starttime = ((String) jsonObject.get("startTime")).replaceAll("[^(0-9),-:]", " ");
//			System.out.println(starttime);
			schedule.setStartTime(Timestamp.valueOf(starttime));
			String endtime = ((String) jsonObject.get("endTime")).replaceAll("[^(0-9),-:]", " ");
			schedule.setEndTime(Timestamp.valueOf(endtime));

			int jobId = 1; // 測試用
			Interview interview = interviewService.findByAdmit_Job_UserName(jobId,
					(String) jsonObject.get("scheduleName"));
			schedule.setInterview(interview);

			scheduleService.insertSchedule(schedule);
		}

		return "schedule/ScheduleCalendar";
	}

	@RequestMapping(value = "/addSchedule", method = RequestMethod.GET)
	public String getScheduleForm(Model model) {
		Schedule schedule = new Schedule();
		model.addAttribute("schedule", schedule);
		return "schedule/addSchedule";
	}

	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	public String addScheduleForm(@ModelAttribute("schedule") Schedule schedule, BindingResult result,
			HttpServletRequest request) {
		scheduleService.insertSchedule(schedule);
		return "redirect:/scheduleManage";
	}

	@RequestMapping("/scheduleManage")
	public String scheduleManage(Model model) {
		List<Schedule> schedulelist = scheduleService.getAllSchedules();
		model.addAttribute("schedules", schedulelist);
		return "schedule/scheduleManage";
	}

//	@RequestMapping("/scheduleManage2")
//	public String scheduleManage2(Model model) {
//		List<Schedule> schedulelist = scheduleService.getAllSchedules();
//		model.addAttribute("schedules", schedulelist);
//		return "schedule/scheduleManage2";
//	}

	@RequestMapping("/deleteSchedule")
	public String deleteScheduleByPrimaryKey(Model model, @RequestParam("scheduleId") Integer scheduleId) {
		scheduleService.deleteScheduleByPrimaryKey(scheduleId);
		return "redirect:/scheduleManage";
	}

	@RequestMapping(value = "/updateSchedule", method = RequestMethod.GET)
	public String getUpdateScheduleForm(Model model, @RequestParam("scheduleId") Integer scheduleId) {
		Schedule updateSchedule = scheduleService.getScheduleByPrimaryKey(scheduleId);
		model.addAttribute("schedule", updateSchedule);
		return "schedule/updateSchedule";
	}

	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
	public String UpdateScheduleForm(@ModelAttribute("schedule") Schedule schedule, BindingResult result,
			HttpServletRequest request) {
		scheduleService.updateScheduleByPrimaryKey(schedule);
		return "redirect:/scheduleManage";
	}

	@RequestMapping("/wageManage")
	public String wageManage(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			model.addAttribute("user", loginUser);
			List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
			model.addAttribute("postJobList", postJobList);
			return "schedule/wageManage";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/selectWage", method = RequestMethod.POST)
	public String selectWage(Model model, @RequestParam("jobId") Integer jobId, @RequestParam("start") String start,
			@RequestParam("end") String end, HttpServletRequest req) throws ParseException {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			if(start.equals("")||end.equals("")) {
				model.addAttribute("user", loginUser);
				List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
				model.addAttribute("postJobList", postJobList);				
				return "schedule/wageManage";
			}else
			model.addAttribute("user", loginUser);
			List<Job> postJobList = jobService.findJobByUserId(loginUser.getUserId());
			model.addAttribute("postJobList", postJobList);
			String ad = new String(" 00:00:00");
			String ed = new String(" 23:00:00");
			String starte= start+ad;
			String ende= end+ed;
			System.out.println(jobId);
			System.out.println(start);
			System.out.println(end);
			// 設定日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 進行轉換
			Timestamp startTime = null;
			Timestamp endTime = null;
			Date startD = sdf.parse(starte);
			Date endD = sdf.parse(ende);
			startTime = new Timestamp(startD.getTime());
			endTime = new Timestamp(endD.getTime());
			System.out.println(startTime);
			System.out.println(endTime);
			List<Schedule> admitScheduleList = scheduleService.getSchedulesByJobIdAndTime(jobId, startTime, endTime);																												
			model.addAttribute("admitScheduleList", admitScheduleList);
			System.out.println("admitScheduleList:"+admitScheduleList.size());
			return "schedule/wageManage";
		} else {
			return "redirect:/";
		}
	}

	public int removeDuplicates(int[] data) {
		if (data.length == 0)
			return 0;
		int index = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[index] != data[i]) { // 與我不同的值
				index++; // 下一個位置
				data[index] = data[i]; // 放進來
			}
		}
		return index + 1;
	}

}
