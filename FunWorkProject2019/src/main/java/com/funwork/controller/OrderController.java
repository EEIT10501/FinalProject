package com.funwork.controller;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funwork.service.JobService;
import com.funwork.service.UserService;
import allPay.payment.integration.*;
import allPay.payment.integration.allPayOperator.AllPayFunction;

@Controller
public class OrderController {
	@Autowired
	JobService jobService;

	@Autowired
	UserService userService;
	
	AllInOne all;
	
	AllPayFunction allPay;
	
	public OrderController(){
		
	}
	
	@RequestMapping("/order")
	public String Order(Model model) {
	
		
		return "order";
	}
	
	@RequestMapping(value = "/orderCheck", method = RequestMethod.POST)
	public String OrderCheck(Model model,HttpServletRequest req) {
		allPay = new AllPayFunction();
		Hashtable<String, String> params = new Hashtable<>();
		String random =Integer.toString((int)(Math.random()*100)) ;
		params.put("MerchantID", req.getParameter("MerchantID"));
		params.put("MerchantTradeNo", req.getParameter("MerchantTradeNo")+random);
		params.put("MerchantTradeDate", req.getParameter("MerchantTradeDate"));
		params.put("TotalAmount", req.getParameter("TotalAmount"));
		params.put("TradeDesc", req.getParameter("TradeDesc"));
		params.put("ItemName", req.getParameter("ItemName"));
		params.put("ReturnURL", req.getParameter("ReturnURL"));
		params.put("ChoosePayment", req.getParameter("ChoosePayment"));
		params.put("PaymentType", req.getParameter("PaymentType"));
		
		model.addAttribute("params",params);
		model.addAttribute("CheckMacValue", allPay.genCheckMacValue("5294y06JbISpM5x9", "v77hoKGq4kWxNNIS", params));
		
		return "order";
	}
	
	@RequestMapping(value = "/orderReturn", method = RequestMethod.POST)
	public String OrderReturn(Model model,HttpServletRequest req,HttpServletResponse res) {
		System.out.println("1");
		System.out.println(req.getParameter("RtnCode"));
		
		return "order";
	}
}
