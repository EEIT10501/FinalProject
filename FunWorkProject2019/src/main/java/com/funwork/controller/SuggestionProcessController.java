package com.funwork.controller;

import com.funwork.model.Suggestion;
import com.funwork.model.User;
import com.funwork.service.SuggestionService;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SuggestionProcessController {
  static final Logger logger = Logger.getLogger("com.funwork");
  @Autowired
  SuggestionService suggestionService;
  @Autowired
  ServletContext context;

  /**
   * Let users post their suggestion.
   */
  @GetMapping("/insertSg")
  public String getSgPage(Model model, HttpSession session) {
    Suggestion suggestion = new Suggestion();
    User user = (User) session.getAttribute("loginUser");
    if (user != null) {
      suggestion.setName(user.getUserName());
      suggestion.setEmail(user.getEmail());
    }
    model.addAttribute("sgBean", suggestion);
    return "insertSg";
  }

  /**
   * Process suggestion.
   */
  @PostMapping("/insertSg")
  public String processSg(@ModelAttribute("sgBean") Suggestion sgBean, Model model) {
    model.addAttribute("okMsg", "您的訊息已傳送成功");
    suggestionService.insertSg(sgBean);
    return "insertSg";
  }

  /**
   * Review suggestion in backstage.
   */
  @GetMapping(value = "/sgsReview")
  public String getsuggestionList(Model model) {
    List<Suggestion> suggestionList = suggestionService.getAllSuggestions();
    model.addAttribute("suggestionList", suggestionList);
    return "review/sgsList";
  }

  /**
   * Get suggestion detail.
   */
  @GetMapping(value = "/sgReview/{sgId}")
  public String getsuggestionList(Model model, @PathVariable("sgId") Integer sgId) {
    Suggestion sgBean = suggestionService.getSuggestionById(sgId);
    model.addAttribute("sgBean", sgBean);
    return "review/sgDetail";
  }

  /**
   * Get suggestion attachment.
   */
  @GetMapping(value = "/getSgPicture/{sgId}")
  public ResponseEntity<byte[]> getSgPicture(@PathVariable Integer sgId) {
    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String fileName = "";
    int len = 0;
    Suggestion suggestion = suggestionService.getSuggestionById(sgId);
    if (suggestion != null) {
      Blob blob = suggestion.getAttachment();
      fileName = suggestion.getFileName();
      if (blob != null) {
        try {
          len = (int) blob.length();
          media = blob.getBytes(1, len);
        } catch (SQLException e) {
          logger.warning("SuggestionProcessController的getSgPicture()發生SQLException:" 
              + e.getMessage());
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
}