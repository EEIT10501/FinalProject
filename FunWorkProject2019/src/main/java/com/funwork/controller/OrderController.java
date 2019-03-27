package com.funwork.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import com.funwork.model.Order;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Product;
import com.funwork.model.User;
import com.funwork.service.JobService;
import com.funwork.service.OrderService;
import com.funwork.service.UserService;

import allPay.payment.integration.AllInOne;
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

  public OrderController() {

  }

  @RequestMapping("/order")
  public String Order(Model model) {
    return "order";
  }

  @RequestMapping("/product")
  public String product(Model model) {
    List<Product> productList = orderService.getAllProducts();

    model.addAttribute("productList", productList);
    return "product";
  }

  @RequestMapping(value = "/orderCheck/{productId}")
  public String OrderCheck(Model model, @PathVariable("productId") Integer productId, HttpServletRequest req) {
    List<Product> prolist = orderService.getAllProducts();
    Hashtable<String, String> params = new Hashtable<>();
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    params.put("MerchantTradeNo", sdf.format(date));
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    params.put("MerchantTradeDate", sdf2.format(date));
    params.put("TotalAmount", Integer.toString(prolist.get(productId - 1).getPrice()));
    params.put("TradeDesc", prolist.get(productId - 1).getDescription());
    params.put("ItemName", prolist.get(productId - 1).getProductName());
    params.put("Payment", "信用卡");

    Order order = new Order(); // 建立資料庫訂單開始
    order.setOrderTradeNo(sdf.format(date));
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    order.setOrderTime(Timestamp.valueOf(sdf3.format(date)));
    order.setPrice(prolist.get(productId - 1).getPrice());
    order.setStatus(0);
    order.setProduct(prolist.get(productId - 1));
    HttpSession session = req.getSession(); // 取得session物件
    User user = (User) session.getAttribute("loginUser"); // 取的在session裡面名為loginUser的物件
    order.setUser(user);
    orderService.insertOrder(order);

    model.addAttribute("params", params);
    return "order";
  }

  @RequestMapping(value = "/orderReturn", method = RequestMethod.POST)
  public String OrderReturn(Model model, HttpServletRequest req, HttpServletResponse res) {
    System.out.println("交易代碼:" + req.getParameter("RtnCode"));

    Order order = orderService.getOrderByTradeNo(req.getParameter("MerchantTradeNo"));
    order.setStatus(Integer.valueOf(req.getParameter("RtnCode")));
    orderService.insertOrder(order);

    if (req.getParameter("RtnCode").equals("1") == true) {
      User user = order.getUser();
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
      Date date = new Date();
      cal.setTime(date);
      if (order.getProduct().getProductId() == 1) {
        cal.add(Calendar.DATE, 30);
      } else if (order.getProduct().getProductId() == 2) {
        cal.add(Calendar.DATE, 180);
        user.setExposureLimit(2);
      } else if (order.getProduct().getProductId() == 3) {
        cal.add(Calendar.DATE, 365);
        user.setExposureLimit(5);
      }

      java.sql.Date sqldate = new java.sql.Date(0);
      if (user.getVipEndDate() == null) {
        user.setVipEndDate(sqldate.valueOf(sdf.format(cal.getTime())));
      } else if (user.getVipEndDate().before(sqldate.valueOf(sdf.format(date)))) {
        user.setVipEndDate(sqldate.valueOf(sdf.format(cal.getTime())));
      } else {
        cal.setTime(user.getVipEndDate());
        System.out.println(cal.getTime());
        if (order.getProduct().getProductId() == 1) {
          cal.add(Calendar.DATE, 30);
          user.setVipEndDate(sqldate.valueOf(sdf.format(cal.getTime())));
        } else if (order.getProduct().getProductId() == 2) {
          cal.add(Calendar.DATE, 180);
          user.setVipEndDate(sqldate.valueOf(sdf.format(cal.getTime())));
          user.setExposureLimit(2);
        } else if (order.getProduct().getProductId() == 3) {
          cal.add(Calendar.DATE, 365);
          user.setVipEndDate(sqldate.valueOf(sdf.format(cal.getTime())));
          user.setExposureLimit(5);
        }

      }
      user.setJobPostLimit(99);
      user.setJobPostPeriod(365);
      user.setMebershipLevel(2);
      userService.updateUser(user);
    }

    model.addAttribute(order);

    return "order";
  }

  @RequestMapping(value = "/orderSave", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public @ResponseBody String aioCheckOutDevide(AioCheckOutOneTime aio, HttpServletRequest req) {
    try {
      all = new AllInOne("");
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    InvoiceObj invoice = new InvoiceObj();
    invoice = null;// 不開發票

//    aio.setMerchantID("2000132");
    aio.setMerchantTradeNo(req.getParameter("MerchantTradeNo"));
    aio.setMerchantTradeDate(req.getParameter("MerchantTradeDate"));
    aio.setTotalAmount(req.getParameter("TotalAmount"));
    aio.setTradeDesc(req.getParameter("TradeDesc"));
    aio.setItemName(req.getParameter("ItemName"));
    aio.setReturnURL("http://localhost:8080/FunWorkProject2019/orderReturn");
    aio.setOrderResultURL("http://localhost:8080/FunWorkProject2019/orderReturn");

    try {
      String html = all.aioCheckOut(aio);
      System.out.println(html);
      return html;
    } catch (AllPayException e) {
      throw new Error(e.getNewExceptionMessage());
    }
  }

  /**
   * Return order history list.
   */
  @GetMapping(value = "/memberOrder")
  public String getMemberOrderHistoryList(Model model, HttpSession session) {
    User user = (User) session.getAttribute("loginUser");
    List<Order> orderlist = orderService.getOrdersById(user.getUserId());
    model.addAttribute("orderlist", orderlist);
    return "userOrderHistory";
  }
}
