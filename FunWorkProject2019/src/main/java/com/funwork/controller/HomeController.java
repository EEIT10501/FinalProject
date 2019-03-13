package com.funwork.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
import com.funwork.service.UserService;

@Controller
public class HomeController {

	private static final String PASSWORDPATTERN = null;
	private Pattern pattern = null;
	private Matcher matcher = null;

	@Autowired
	ResumeService resumeService;
	@Autowired
	UserService userService;
	@Autowired
	ServletContext context;

	public HomeController() {
	}

	@RequestMapping("/")
	public String Home() {
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
			@RequestParam("rememberMe") String rememberMe, HttpServletRequest req, HttpServletResponse res) {
		User user = userService.loginCheck(email, password);
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user);

			if (rememberMe.equals(true)) {
				cookieUser = new Cookie("user", String.valueOf(user.getEmail()));
				cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				cookieUser.setPath(req.getContextPath());

				cookiePassword = new Cookie("password", password);
				cookiePassword.setMaxAge(7 * 24 * 60 * 60);
				cookiePassword.setPath(req.getContextPath());

				cookieRememberMe = new Cookie("rm", "true");
				cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
				cookieRememberMe.setPath(req.getContextPath());
			} else { // 使用者沒有對 RememberMe 打勾
				cookieUser = new Cookie("user", String.valueOf(user.getEmail()));
				cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
				cookieUser.setPath(req.getContextPath());

				cookiePassword = new Cookie("password", password);
				cookiePassword.setMaxAge(0);
				cookiePassword.setPath(req.getContextPath());

				cookieRememberMe = new Cookie("rm", "false");
				cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
				cookieRememberMe.setPath(req.getContextPath());
			}
			res.addCookie(cookieUser);
			res.addCookie(cookiePassword);
			res.addCookie(cookieRememberMe);

			return "OK";
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
		
		//回傳上面的錯誤訊息到/register頁面
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
			userService.insertUser(ub);
			System.out.println("email");
			System.out.println("新增成功");
			System.out.println(userService.idExists(email));
			return "redirect:/";
		}

		return "/register";

	}

}
