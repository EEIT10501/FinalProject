package com.funwork.controller;

import com.funwork.model.Application;
import com.funwork.model.Message;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.MessageService;
import com.funwork.service.impl.WSMessageService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

  private static final String LOGIN_USER = "loginUser";
  private static final String CACHECONTROL_HEADER = "Cache-Control";
  private static final String NO_CACHE = "no-cache";

  @Autowired
  private WSMessageService wsMessageService;
  @Autowired
  MessageService messageService;
  @Autowired
  ApplicationService applicationService;

  /**
   * Create WS connection between client and server.
   */
  @GetMapping(value = "/message/connWS")
  @ResponseBody
  public String connWs(@RequestParam(value = "userId", required = true) Integer userId,
      @RequestParam(value = "toUserId", required = true) Integer toUserId,
      @RequestParam(value = "message", required = true) String message,
      @RequestParam(value = "apId", required = true) Integer apId) {
    applicationService.updateLatestMsg(apId, message);
    messageService.insertMessage(message, userId, toUserId, apId, 0);
    if (wsMessageService.sendToAllTerminal(toUserId.toString(), message)) {
      return "success";
    } else {
      return "fail";
    }
  }

  /**
   * Return chat list page to user.
   */
  @GetMapping("/chat")
  public String getChatList(Model model, HttpServletRequest req, HttpServletResponse res) {
    HttpSession session = req.getSession();
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    model.addAttribute("user", loginUser);
    res.setHeader(CACHECONTROL_HEADER, NO_CACHE);
    res.setHeader(CACHECONTROL_HEADER, "no-store");
    res.setDateHeader("Expires", 0);
    res.setHeader("Pragma", NO_CACHE);
    return "pages/chatList";
  }

  /**
   * return chat page by applicationId.
   */
  @GetMapping("/chat/{applicationId}")
  public String chat(Model model, @PathVariable("applicationId") Integer applicationId, 
      HttpServletRequest req, HttpServletResponse res) {
    HttpSession session = req.getSession();
    res.setHeader(CACHECONTROL_HEADER, NO_CACHE);
    res.setHeader(CACHECONTROL_HEADER, "no-store");
    res.setDateHeader("Expires", 0);
    res.setHeader("Pragma", NO_CACHE);
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser == null) {
      return "redirect:/";
    }
    User user = null;
    User toUser = null;
    List<Message> list = messageService.getOldMessageByApplicationId(applicationId);
    Application application = applicationService.findByPrimaryKey(applicationId);
    if (loginUser.getUserId().equals(application.getUser().getUserId())) {
      user = application.getUser();
      toUser = application.getJob().getJobOwner();
    } else {
      user = application.getJob().getJobOwner();
      toUser = application.getUser();
    }
    model.addAttribute("oldMessageList", list);
    model.addAttribute("application", application);
    model.addAttribute("user", user);
    model.addAttribute("toUser", toUser);
    return "pages/chat";
  }

  /**
   * Use JSON to return old message based on applicationId.
   */
  @GetMapping(value = "/chatJSON", produces = { "application/json" })
  public ResponseEntity<List<Message>> getOldMsgByApId(@RequestParam("apId") Integer apId, 
      HttpServletRequest req) {
    HttpSession session = req.getSession();
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    messageService.changeMsgStatusToRead(loginUser.getUserId(), apId);
    List<Message> list = messageService.getOldMessageByApplicationId(apId);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping(value = "/apJSON", produces = { "application/json" })
  public ResponseEntity<List<Application>> getApByUserId(@RequestParam("userId") Integer userId) {
    List<Application> list = applicationService.getApplicationByUserId(userId);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  /**
   * Return new message count to show in the navbar.
   */
  @GetMapping("/newMsg")
  @ResponseBody
  public int newMsg(HttpServletRequest req) {
    HttpSession session = req.getSession();
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    int count;
    if (loginUser == null) {
      count = 0;
    } else {
      count = messageService.getNewMsgCount(loginUser.getUserId());
    }
    return count;
  }
}