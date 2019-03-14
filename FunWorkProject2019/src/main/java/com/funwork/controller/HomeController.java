package com.funwork.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.funwork.service.ScheduleService;
import com.funwork.service.UserService;
import com.funwork.service.impl.SendGmailService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Controller
public class HomeController {

	@Autowired
	ScheduleService scheuleService;
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
	public String Home(HttpServletRequest req) {
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
	public String Form() {
		return "pages/form";
	}

	@RequestMapping(value = "/getPicture/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletRequest resp, @PathVariable Integer userId) {

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
//				media = toByteArray(filePath);
//				fileName = filePath;
			}
		} else {
//			media = toByteArray(filePath);
//			fileName = filePath;
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
			if (user.getIsOpen()) {
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", user);
				return "OK";
			} else {
				return "notOpen";
			}
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		User ub = new User();
		model.addAttribute("userBean", ub);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("email") String email, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("password2") String password2,
			HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 存放錯誤訊息errorMeg
		Map<String, String> errorMeg = new HashMap<String, String>();
		Map<String, String> okMeg = new HashMap<String, String>();
		HttpSession session = req.getSession();
		req.setAttribute("Msg", errorMeg); // 顯示錯誤訊息, errorMeg傳到前端用EL接
		session.setAttribute("OK", okMeg);

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
				errorMeg.put("errPd2Empty", "密碼欄必須與確認欄一致");
				errorMeg.put("errPdEmpty", "*");
			}
		}
		// 可以使用Pattern的靜態方法compile()來編譯
		// 之後就可以重覆使用這個pattern的matcher()方法來進行字串比對,matcher.matches()傳回true or false
//		if (errorMeg.isEmpty()) {
//			pattern = Pattern.compile(PASSWORDPATTERN);
//			matcher = pattern.matcher(password);
//			if (!matcher.matches()) {
//				errorMeg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
//			}
//		}

		// 回傳上面的錯誤訊息到/register頁面
		if (!errorMeg.isEmpty()) {
//			RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
//			rd.forward(req, res);
			return "/register";
		}

		if (userService.idExists(email)) {
			errorMeg.put("errorIDExs", "帳號已存在請重新更新");
			System.out.println(userService.idExists(email));

		} else {
			User ub = new User();
			ub.setEmail(email);
			ub.setUserName(name);
			ub.setPassword(password);
			ub.setRole(2);
			ub.setAbscence(0);
			ub.setExposureLimit(0);
			ub.setJobPostLimit(3);
			ub.setJobPostPeriod(7);
			ub.setMebershipLevel(1);
			ub.setIsOpen(false);
			Serializable userId = userService.insertUser(ub);
			sendGmailService.sendEmail(email, "sam810331@gmail.com", "趣打工會員註冊成功!",
					"<h1>哈囉!" + name
							+ "，歡迎您成為趣打工會員!</h1><br><a href='http://localhost:8080/FunWorkProject2019/userOpen/"
							+ userId + "'><p>請點擊本連結進行帳號驗證</p></a>");

			return "redirect:/";
		}

		return "/register";

	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
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

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance())
						.setAudience(Collections.singletonList(
								"784516300990-g9mc0al77s74lmmi0q6hb9777k3om0qj.apps.googleusercontent.com"))
						.build();

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idtoken);
		} catch (GeneralSecurityException e) {
			System.out.println("驗證時出現GeneralSecurityException異常");
		} catch (IOException e) {
			System.out.println("驗證時出現IOException異常");
		}
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String userId = payload.getSubject();
			String email = payload.getEmail();
//			String name = (String) payload.get("name");	這個name 是 偉廷石	
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			String name = givenName.trim() + familyName.trim();

			if (userService.idExists(email)) {
				User user = userService.getUserByGoogleEmail(email, userId);
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", user);
			} else {
				User ub = new User();
				ub.setEmail(email);
				ub.setUserName(name);
//				ub.setPassword(password);
				ub.setGoogle(userId);
				ub.setRole(2);
				ub.setAbscence(0);
				ub.setExposureLimit(0);
				ub.setJobPostLimit(3);
				ub.setJobPostPeriod(7);
				ub.setMebershipLevel(1);
				ub.setIsOpen(true);
				userService.insertUser(ub);

				HttpSession session = req.getSession();
				session.setAttribute("loginUser", ub);

			}
			return "OK";
		} else {
			return "Fail";
		}
	}
}
