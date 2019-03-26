package com.funwork.dao;

import com.funwork.model.Suggestion;
import java.util.List;

public interface SuggestionDao {
  List<Suggestion> getAllSuggestions();

  Suggestion getSuggestionById(Integer sgId);

  void insertSg(Suggestion sg);
}