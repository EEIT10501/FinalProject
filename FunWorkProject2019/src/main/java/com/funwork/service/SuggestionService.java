package com.funwork.service;

import com.funwork.model.Suggestion;
import java.util.List;

public interface SuggestionService {
  List<Suggestion> getAllSuggestions();

  Suggestion getSuggestionById(Integer sgId);

  void insertSg(Suggestion suggestion);
}