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
	
	@RequestMapping("/deleteSchedule")
	public String deleteScheduleByPrimaryKey(Model model, @RequestParam("scheduleId") Integer scheduleId) {
		scheuleService.deleteScheduleByPrimaryKey(scheduleId);
		return "redirect:/scheduleManage";
	}
	
	
	@RequestMapping(value = "/updateSchedule", method = RequestMethod.GET)
	public String getUpdateScheduleForm(Model model, @RequestParam("scheduleId") Integer scheduleId) {
//		Schedule schedule = new Schedule();
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
	
//	http://localhost:8080/FunWorkProject2019/scheduleManage/updateSchedule
//	
//	@RequestMapping("/scheduleManage")
//	public String scheduleManage(Model model) {
//		List<Schedule> schedulelist = scheuleService.getAllSchedules();
//		model.addAttribute("schedules",schedulelist);
//		return "schedule/scheduleManage";
//	}
	
//	@RequestMapping("/updateSchedule")
//	public String updateScheduleByPrimaryKey(Model model, @RequestParam("scheduleId") Integer scheduleId) {
//		scheuleService.updateScheduleByPrimaryKey(scheduleId);
//		return "redirect:/scheduleManage";
//	}
	

//	@RequestMapping(value = "/products/add", method = RequestMethod.GET) // 空白表單
//	public String getAddNewProductForm(Model model) {
//		BookBean bb = new BookBean();
//		bb.setTitle("美麗人生");// test
//		bb.setAuthor("溫麗");// test
//		model.addAttribute("bookBean", bb);
//		return "addProduct";
//	}

//	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
//	public String processAddNewProductForm(@ModelAttribute("bookBean") BookBean bb, BindingResult result,
//			HttpServletRequest request) {
//		String[] suppressedFields = result.getSuppressedFields();
//		if (suppressedFields.length > 0) {
//			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
//		}
//		if (bb.getStock() == null) {
//			bb.setStock(0);
//		}
//
//		MultipartFile productImage = bb.getProductImage();
//		String originalFilename = productImage.getOriginalFilename();
//		bb.setFileName(originalFilename);
//
//		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//		String rootDirectory = context.getRealPath("/");
//		// 建立Blob物件，交由 Hibernate 寫入資料庫
//		if (productImage != null && !productImage.isEmpty()) {
//			try {
//				byte[] b = productImage.getBytes();
//				Blob blob = new SerialBlob(b);
//				bb.setCoverImage(blob);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//			}
//		}
//		service.addProduct(bb);
//
//		try {
//			File imageFolder = new File(rootDirectory, "images");
//			if (!imageFolder.exists())
//				imageFolder.mkdirs();
//			File file = new File(imageFolder, "EEIT_105_BB_" + bb.getBookId() + ext);
//			productImage.transferTo(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//		}
//		return "redirect:/products";
//	}
	

}
