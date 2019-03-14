package com.funwork.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.funwork.model.Schedule;
import com.funwork.service.ScheduleService;




@Controller
public class ScheduleController {
	
	@Autowired
	ScheduleService scheuleService;

	public ScheduleController() {
	}
	
	@RequestMapping("/calendar")
	public String calendar() {
		return "schedule/calendar";
	}
	
	@RequestMapping("/calendarTest")
	public String calendarTest() {
		return "schedule/calendarTest";
	}

	
	@RequestMapping("/calendarTest2")
	public String calendarTest2() {
		return "schedule/calendarTest2";
	}


	@RequestMapping(value = "/addSchedule", method = RequestMethod.GET)
	public String getScheduleForm(Model model) {
		Schedule schedule = new Schedule();
		model.addAttribute("schedule", schedule);
		return "schedule/addSchedule";
	}
	
	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	public String addScheduleForm(@ModelAttribute("schedule")Schedule schedule, BindingResult result,
			HttpServletRequest request) {
		scheuleService.insertSchedule(schedule);
		return "redirect:/scheduleManage";
	}
	
	@RequestMapping("/scheduleManage")
	public String scheduleManage(Model model) {
		List<Schedule> schedulelist = scheuleService.getAllSchedules();
		model.addAttribute("schedules",schedulelist);
		return "schedule/scheduleManage";
	}
	
	@RequestMapping("/scheduleManage2")
	public String scheduleManage2(Model model) {
		List<Schedule> schedulelist = scheuleService.getAllSchedules();
		model.addAttribute("schedules",schedulelist);
		return "schedule/scheduleManage2";
	}
	
	@RequestMapping("/deleteSchedule")
	public String deleteScheduleByPrimaryKey(Model model, @RequestParam("scheduleId") Integer scheduleId) {
		scheuleService.deleteScheduleByPrimaryKey(scheduleId);
		return "redirect:/scheduleManage";
	}
	
	
	@RequestMapping(value = "/updateSchedule", method = RequestMethod.GET)
	public String getUpdateScheduleForm(Model model, @RequestParam("scheduleId") Integer scheduleId) {
		Schedule updateSchedule = scheuleService.getScheduleByPrimaryKey(scheduleId);
		model.addAttribute("schedule", updateSchedule);
		return "schedule/updateSchedule";
	}

	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
	public String UpdateScheduleForm(@ModelAttribute("schedule")Schedule schedule, BindingResult result,
			HttpServletRequest request) {
		scheuleService.updateScheduleByPrimaryKey(schedule);
		return "redirect:/scheduleManage";
	}
	

	

}
