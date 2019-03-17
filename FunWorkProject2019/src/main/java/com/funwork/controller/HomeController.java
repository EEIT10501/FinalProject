package com.funwork.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;
import com.funwork.service.impl.SendGmailService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

@Controller
public class HomeController {

	@Autowired
	ResumeService resumeService;
	@Autowired
	UserService userService;
	@Autowired
	ServletContext context;
	@Autowired
	SendGmailService sendGmailService;

	public HomeController() {
	}

	@RequestMapping("/")
	public String home(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			if (loginUser.getRole() == 1) {
				return "redirect:/jobsReview";
			}
		}
		return "index";
	}

	@RequestMapping("/form")
	public String form() {
		return "pages/form";
	}

	@RequestMapping(value = "/getPicture/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getUserPicture(@PathVariable Integer userId) {

		String filePath = "/resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String fileName = "";
		int len = 0;
		Resume resume = resumeService.getResumeByUserId(userId);
		if (resume != null) {
			Blob blob = resume.getProfilePic();
			fileName = resume.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("HomeController的getPicture()發生SQLException:" + e.getMessage());
				}
			} else {
				media = toByteArray(filePath);
				fileName = filePath;
			}
		} else {
			media = toByteArray(filePath);
			fileName = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(fileName);
		MediaType mediaType = MediaType.valueOf(mimeType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest req) {
		User user = userService.loginCheck(email, password);

		if (user != null) {
//			if (user.getIsOpen()) {
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user);
			return "OK";
//			} else {
//				return "notOpen";
//			}
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("email") String email, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("password2") String password2,
			HttpServletRequest req) {

		// 存放錯誤訊息errorMeg
		Map<String, String> errorMeg = new HashMap<String, String>();
		req.setAttribute("Msg", errorMeg); // 顯示錯誤訊息, errorMeg傳到前端用EL接

		if (email == null || email.trim().length() == 0) {
			errorMeg.put("errEmailEmpty", "帳號欄必須輸入");
		}
		if (name == null || name.trim().length() == 0) {
			errorMeg.put("errNameEmpty", "姓名欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errorMeg.put("errPdEmpty", "密碼欄必須輸入");
		}
		if (password2 == null || password2.trim().length() == 0) {
			errorMeg.put("errPd2Empty", "密碼確認欄必須輸入");
		}
		if (password.trim().length() > 0 && password2.trim().length() > 0) {
			if (!password.trim().equals(password2.trim())) {
				errorMeg.put("errPd2Empty", "必須與密碼欄一致");
			}
		}
		// 回傳上面的錯誤訊息到/register頁面
		if (!errorMeg.isEmpty()) {
			return "/register";
		}

		if (userService.idExists(email)) {
			errorMeg.put("errorIDExs", "帳號已存在請重新更新");
			System.out.println(userService.idExists(email));

		} else {
			Integer userId = userService.insertUser(email, name, password);
//			sendGmailService.sendEmail(email, "sam810331@gmail.com", "趣打工會員註冊成功!",
//					"<h1>哈囉!" + name
//							+ "，歡迎您成為趣打工會員!</h1><br><a href='http://localhost:8080/FunWorkProject2019/userOpen/"
//							+ userId + "'><p>請點擊本連結進行帳號驗證</p></a>");
			return "redirect:/";
		}
		return "/register";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";

	}

	@RequestMapping("/userOpen/{userId}")
	public String userOpen(@PathVariable("userId") String userId) {
		userService.openUser(userId);
		return "redirect:/";
	}

	@RequestMapping("/googleLogin")
	@ResponseBody
	public String googleLogin(@RequestParam("idtoken") String idtoken, HttpServletRequest req) {

		GoogleIdToken idToken = null;
		idToken = sendGmailService.idTokenVertify(idtoken);

		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String googleId = payload.getSubject();
			String email = payload.getEmail();
//			String name = (String) payload.get("name");	這個name 是 偉廷石	
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			String name = givenName.trim() + familyName.trim();
			if (userService.idExists(email)) {
				User user = userService.getUserByGoogleEmail(email, googleId);
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", user);
			} else {
				Integer userId = userService.insertGoogleUser(email, name, googleId);
				User user = userService.getUserById(userId);
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", user);
			}
			return "OK";
		} else {
			return "Fail";
		}
	}

	private byte[] toByteArray(String filePath) {
		String root = context.getRealPath("/");
		root = root.substring(0, root.length() - 1);
		String fileLocation = root + filePath;
		byte[] b = null;
		try {
			java.io.File file = new java.io.File(fileLocation);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filePath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
}
