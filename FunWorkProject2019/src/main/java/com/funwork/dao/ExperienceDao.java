package com.funwork.dao;

import java.util.List;

import com.funwork.model.Experience;

public interface ExperienceDao {

	List<Experience> getAllExperiences();

	void insertExperience(Experience experience);
}
