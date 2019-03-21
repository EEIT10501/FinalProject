package com.funwork.controller;

import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;
import com.funwork.service.impl.GoogleService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	public static final Logger logger = Logger.getLogger("com.funwork");
	private static final String REDIRECT_TO_INDEX = "redirect:/";
	private static final String LOGIN_USER = "loginUser";

	@Autowired
	ResumeService resumeService;
	@Autowired
	UserService userService;
	@Autowired
	ServletContext context;
	@Autowired
	GoogleService googleService;

	/**
	 * Return index.jsp，如果登入的是管理員，傳回管理員View.
	 */
	@GetMapping("/")
	public String home(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute(LOGIN_USER);
		if (loginUser != null && loginUser.getRole() == 1) {
			return "redirect:/jobsReview";
		}
		return "index";
	}

	@GetMapping("/form")
	public String form() {
		return "pages/form";
	}

	/**
	 * Return user picture based on userId.
	 */
	@GetMapping(value = "/getPicture/{userId}")
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
					logger.warning("HomeController的getPicture()發生SQLException:" + e.getMessage());
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
		return new ResponseEntity<>(media, headers, HttpStatus.OK);
	}

	/**
	 * Process user login.
	 */
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest req) {
		User user = userService.loginCheck(email, password);
		if (user != null) {
//			if (user.getIsOpen()) {
			HttpSession session = req.getSession();
			session.setAttribute(LOGIN_USER, user);
			return "OK";
//			} else {
//				return "notOpen";
//			}
		} else {
			return "fail";
		}
	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		return "register";
	}

	/**
	 * Process user register.
	 */
	@PostMapping(value = "/register")
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
		if ((password != null && password2 != null) && (password.trim().length() > 0 && password2.trim().length() > 0)
				&& !password.trim().equals(password2.trim())) {
			errorMeg.put("errPd2Empty", "密碼欄必須與確認欄一致");
			errorMeg.put("errPdEmpty", "*");
		}
		// 回傳上面的錯誤訊息到/register頁面
		if (!errorMeg.isEmpty()) {
			return "/register";
		}
		if (userService.idExists(email)) {
			errorMeg.put("errorIDExs", "帳號已存在請重新輸入");
		} else {
			Integer userId = userService.insertUser(email, name, password);

//			googleService.sendEmail(email, "sam810331@gmail.com", "趣打工會員註冊成功!",
//					"<h1>哈囉!" + name
//							+ "，歡迎您成為趣打工會員!</h1><br><a href='http://localhost:8080/FunWorkProject2019/userOpen/"
//							+ userId + "'><p>請點擊本連結進行帳號驗證</p></a>");
			return REDIRECT_TO_INDEX;
		}
		return "/register";
	}

	/**
	 * Process User logout.
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute(LOGIN_USER);
		return REDIRECT_TO_INDEX;

	}

	@GetMapping("/userOpen/{userId}")
	public String userOpen(@PathVariable("userId") Integer userId) {
		userService.openUser(userId);
		return REDIRECT_TO_INDEX;
	}

	/**
	 * Process User Account setting.
	 */
	@GetMapping(value = "/accountSetting")
	public String accountSetting(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loginUser");
		if (user != null) {
			req.setAttribute("Users", user);
			System.out.println(user);
			return "/accountSetting";
		} else {
			return REDIRECT_TO_INDEX;
		}
	}

	@PostMapping(value = "/accountSetting")
	public String accountSetting(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("password2") String password2, HttpServletRequest req, HttpSession session) {
		Map<String, String> errorMeg = new HashMap<String, String>();
		Map<String, String> rightMeg = new HashMap<String, String>();
		User user = (User) session.getAttribute("loginUser");
		req.setAttribute("Users", user);
		req.setAttribute("Msg", errorMeg);
		req.setAttribute("Ms", rightMeg);
		Integer userId = user.getUserId();
	
		if (userService.idExists(email) && !email.equals(user.getEmail())) {
			errorMeg.put("errorIDExs", "帳號已存在請重新輸入");
		}
		if (userService.idExists(email) && email.equals(user.getEmail())) {
			userService.updateAccount(email, password, userId);
			rightMeg.put("pswOK", "密碼更新成功");
		}
		if (!userService.idExists(email)) {
			userService.updateAccount(email, password, userId);
			rightMeg.put("acutOK", "帳號更新成功");
			rightMeg.put("pswOK", "密碼更新成功");
		}
		return "/accountSetting";
	}

	/**
	 * Google第三方登入.
	 */
	@PostMapping("/googleLogin")
	@ResponseBody
	public String googleLogin(@RequestParam("idtoken") String idtoken, HttpServletRequest req) {

		GoogleIdToken idToken = null;
		idToken = googleService.idTokenVerify(idtoken);

		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String googleId = payload.getSubject();
			String email = payload.getEmail();
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			String name = givenName.trim() + familyName.trim();
			if (userService.idExists(email)) {
				User user = userService.getUserByGoogleEmail(email, googleId);
				HttpSession session = req.getSession();
				session.setAttribute(LOGIN_USER, user);
			} else {
				Integer userId = userService.insertGoogleUser(email, name, googleId);
				User user = userService.getUserById(userId);
				HttpSession session = req.getSession();
				session.setAttribute(LOGIN_USER, user);
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
		int currentBytesRead = 0;
		int totalBytesRead = 0;
		try {
			java.io.File file = new java.io.File(fileLocation);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filePath);
			while ((currentBytesRead = fis.read(b)) > 0) {
				totalBytesRead += currentBytesRead;
			}
			logger.log(Level.ALL, "totalBytesRead:{0}", totalBytesRead);
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
		return b;
	}

	@RequestMapping("/qapage")
	public String qaPage(Model model) {

		return "qapage";
	}

}