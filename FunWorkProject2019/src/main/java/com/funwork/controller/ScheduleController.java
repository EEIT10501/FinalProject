package com.funwork.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.funwork.model.Salary;
import com.funwork.model.Schedule;
import com.funwork.service.AttendenceService;
import com.funwork.service.SalaryService;
import com.funwork.service.ScheuleService;

@Controller
public class ScheduleController {

	public ScheduleController() {
	}
	
	@RequestMapping("/calendar")
	public String calendar() {
		return "schedule/calendar";
	}
	
	@RequestMapping("/scheduleManage")
	public String scheduleManage() {
		return "schedule/scheduleManage";
	}
	
	@RequestMapping("/groupManage")
	public String calendar2() {
		return "schedule/groupManage";
	}
	
	@RequestMapping("/addSchedule")
	public String addWorker() {
		return "schedule/addSchedule";
	}
	
	@RequestMapping("/addWorker")
	public String editWorker() {
		return "schedule/addWorker";
	}


}
