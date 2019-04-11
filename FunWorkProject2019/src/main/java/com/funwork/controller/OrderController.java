package com.funwork.controller;

import allPay.payment.integration.AllInOne;
import allPay.payment.integration.domain.AioCheckOutOneTime;
import allPay.payment.integration.exception.AllPayException;
import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.model.User;
import com.funwork.service.OrderService;
import com.funwork.service.UserService;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
  private static final String MERCHANT_TRADE_NO = "MerchantTradeNo";
  static final Logger logger = Logger.getLogger("com.funwork");
  @Autowired
  UserService userService;
  @Autowired
  OrderService orderService;
  AllInOne all;

  /**
   * Get product.
   */
  @GetMapping("/product")
  public String product(Model model) {
    List<Product> productList = orderService.getAllProducts();
    model.addAttribute("productList", productList);
    return "product";
  }

  /**
   * Check order product.
   */
  @GetMapping(value = "/orderCheck/{productId}")
  public String orderCheck(Model model, @PathVariable("productId") Integer productId, 
      HttpSession session) {
    List<Product> prolist = orderService.getAllProducts();
    Hashtable<String, String> params = new Hashtable<>();
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    params.put(MERCHANT_TRADE_NO, sdf.format(date));
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
    User user = (User) session.getAttribute("loginUser");
    order.setUser(user);
    orderService.insertOrder(order);

    model.addAttribute("params", params);
    return "order";
  }

  /**
   * If success, return order page.
   */
  @PostMapping(value = "/orderReturn")
  public String orderReturn(Model model, HttpServletRequest req) {
    Order order = orderService.getOrderByTradeNo(req.getParameter(MERCHANT_TRADE_NO));
    order.setStatus(Integer.valueOf(req.getParameter("RtnCode")));
    orderService.insertOrder(order,Integer.valueOf(req.getParameter("RtnCode")));

    

    model.addAttribute(order);

    return "order";
  }

  /**
   * Save order.
   */
  @PostMapping(value = "/orderSave", produces = "text/html;charset=UTF-8")
  public @ResponseBody String aioCheckOutDevide(AioCheckOutOneTime aio, HttpServletRequest req) {
    all = new AllInOne("");

    // aio.setMerchantID("2000132");
    aio.setMerchantTradeNo(req.getParameter(MERCHANT_TRADE_NO));
    aio.setMerchantTradeDate(req.getParameter("MerchantTradeDate"));
    aio.setTotalAmount(req.getParameter("TotalAmount"));
    aio.setTradeDesc(req.getParameter("TradeDesc"));
    aio.setItemName(req.getParameter("ItemName"));
    aio.setReturnURL("http://funwork.southeastasia.cloudapp.azure.com:8080/FunWorkProject2019/orderReturn");
    aio.setOrderResultURL("http://funwork.southeastasia.cloudapp.azure.com:8080/FunWorkProject2019/orderReturn");

    try {
      return all.aioCheckOut(aio);
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