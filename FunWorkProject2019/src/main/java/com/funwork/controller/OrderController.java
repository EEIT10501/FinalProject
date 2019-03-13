package com.funwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.service.JobService;
import com.funwork.service.UserService;
import allPay.payment.integration.AllInOne;
import allPay.payment.integration.domain.CaptureObj;

@Controller
public class OrderController {
	@Autowired
	JobService jobService;

	@Autowired
	UserService userService;
	
	public OrderController(){
		
	}
	
	@RequestMapping("/order")
	public String Order(Model model) {
//		AllInOne capture = new AllInOne(null);
//		CaptureObj captest = new CaptureObj();
//		captest.setMerchantID("2000132");
//		captest.setMerchantTradeNo("201903130449001");
//		captest.setPlatformChargeFee("1");
//		captest.setPlatformID("123");
//		captest.setRemark("TEST");
//		captest.setUpdatePlatformChargeFee("N");
//		captest.setUserRefundAMT("0");
//		captest.setCaptureAMT("0");
//		
//		String captest2 = capture.capture(captest);
//		model.addAttribute("cattest2", captest2);
		
		
		return "order";
	}
}
