package com.funwork.service.impl;

import com.funwork.dao.SuggestionDao;
import com.funwork.model.Suggestion;
import com.funwork.service.SuggestionService;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class SuggestionServiceImpl implements SuggestionService {
  public static final Logger logger = Logger.getLogger("com.funwork");
  @Autowired
  SuggestionDao suggestionDao;

  @Override
  public List<Suggestion> getAllSuggestions() {
    return suggestionDao.getAllSuggestions();
  }

  @Override
  public Suggestion getSuggestionById(Integer sgId) {
    return suggestionDao.getSuggestionById(sgId);
  }

  @Override
  public void insertSg(Suggestion suggestion) {
    suggestion.setSubmitTime(new Timestamp(System.currentTimeMillis()));
    MultipartFile attachmentPart = suggestion.getAttachmentPart();
    String originalFilename = attachmentPart.getOriginalFilename();
    if (originalFilename.trim().length() == 0) {
      suggestion.setAttachment(null);
      suggestion.setFileName(null);
      suggestionDao.insertSg(suggestion);
      return;
    }
    suggestion.setFileName(originalFilename);
    try {
      byte[] b = attachmentPart.getBytes();
      Blob blob = new SerialBlob(b);
      suggestion.setAttachment(blob);
      suggestionDao.insertSg(suggestion);
    } catch (Exception e) {
      logger.log(Level.WARNING, "檔案上傳發生異常: {0}", e.getMessage());
    }
  }
}