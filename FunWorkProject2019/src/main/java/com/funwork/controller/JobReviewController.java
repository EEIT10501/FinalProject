package com.funwork.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funwork.model.Job;

@Controller
public class JobReviewController {

	@RequestMapping(value = "/job/1", method = RequestMethod.GET)
	public String reviewJobForm(Model model) {
		Job job = new Job();
		job.setTitle("賣場促銷");
		job.setComment("於大潤發進行商品促銷");
		job.setAddress("內湖大潤發");
		model.addAttribute("jobBean", job);
		return "pages/reviewJob";
	}

	@RequestMapping(value = "/job/1", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("jobBean") Job job, BindingResult result,
			HttpServletRequest request) {

		String[] suppressedFields = result.getSuppressedFields();

		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		System.out.println(job.getTitle());
		System.out.println(job.getComment());
		System.out.println(job.getTitle());

		return "redirect:/job/1";
	}
}
