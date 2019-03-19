package com.funwork.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Product;
import com.funwork.service.JobService;
import com.funwork.service.OrderService;
import com.funwork.service.UserService;
import allPay.payment.integration.*;
import allPay.payment.integration.allPayOperator.AllPayFunction;
import allPay.payment.integration.domain.AioCheckOutOneTime;
import allPay.payment.integration.domain.InvoiceObj;
import allPay.payment.integration.exception.AllPayException;

@Controller
public class OrderController {
	@Autowired
	JobService jobService;

	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;

	AllInOne all;

	AllPayFunction allPay;

	public OrderController() {

	}

	@RequestMapping("/order")
	public String Order(Model model) {
		return "order";
	}

	@RequestMapping(value = "/orderCheck/{productId}")
	public String OrderCheck(Model model, @PathVariable("productId") Integer productId,HttpServletRequest req) {
		List<Product> prolist = orderService.getAllProducts();
		
		
		allPay = new AllPayFunction();
		Hashtable<String, String> params = new Hashtable<>();
		params.put("MerchantID", "2000132");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		params.put("MerchantTradeNo", sdf.format(date));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		params.put("MerchantTradeDate", sdf2.format(date));
		params.put("TotalAmount",Integer.toString(prolist.get(productId-1).getPrice()));
		params.put("TradeDesc", prolist.get(productId-1).getDescription());
		params.put("ItemName", prolist.get(productId-1).getProductName());
		params.put("ReturnURL", "http://localhost:8080/FunWorkProject2019//FunWorkProject2019/orderReturn");
		params.put("ChoosePayment", "Credit");
		params.put("PaymentType", "aio");

		model.addAttribute("params", params);
		model.addAttribute("CheckMacValue", allPay.genCheckMacValue("5294y06JbISpM5x9", "v77hoKGq4kWxNNIS", params));

		return "order";
	}
	
	@RequestMapping("/product")
	public String product(Model model) {
		List<Product> productList =  orderService.getAllProducts();
		
		model.addAttribute("productList",productList);
		return "product";
	}

	@RequestMapping(value = "/orderReturn", method = RequestMethod.POST)
	public String OrderReturn(Model model, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("1");
		System.out.println("交易成功" + req.getParameter("RtnCode"));
		return "order";
	}

	@RequestMapping("/order2")
	public String Order2(Model model) {

		return "ordertest";
	}

	@RequestMapping(value = "/orderCheck1", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String aioCheckOutDevide(AioCheckOutOneTime aio) {
		try {
			all = new AllInOne("");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println(aio.getRemark());
		InvoiceObj invoice = new InvoiceObj();
		// 模擬不開發票
		invoice = null;
		// 廠商系統自行產生
		aio.setMerchantTradeNo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20));
		Date date = new Date();
		// 廠商可自行決定交易時間
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		aio.setMerchantTradeDate(sdf.format(date));
		// 從廠商DB撈出的商品資訊
		aio.setItemName("item1");
		aio.setTotalAmount("50");
		aio.setTradeDesc("item desc");
		// 廠商可自行決定是否延遲撥款
		aio.setHoldTradeAMT("0");
		// 後端設定付款完成通知回傳網址
		aio.setReturnURL("http://localhost:8080/FunWorkProject2019//FunWorkProject2019/orderReturn");
		aio.setOrderResultURL("http://localhost:8080/FunWorkProject2019/orderReturn");
		try {
			String html = all.aioCheckOut(aio, invoice);
			System.out.println(html);
			return html;
		} catch (AllPayException e) {
			throw new Error(e.getNewExceptionMessage());
		}
	}
}
