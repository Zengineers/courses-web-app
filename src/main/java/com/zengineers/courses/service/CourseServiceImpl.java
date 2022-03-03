package com.zengineers.courses.service;

import java.util.List;
import java.util.Map;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.service.statistics.StatisticStrategy;

public class CourseServiceImpl implements CourseService {

	private List<StatisticStrategy> statCalculationStrategies;
	
	public CourseServiceImpl() {
		// TODO
	}
	
	public List<StatisticStrategy> getStatCalculationStrategies() {
		return statCalculationStrategies;
	}

	public void setStatCalculationStrategies(List<StatisticStrategy> statCalculationStrategies) {
		this.statCalculationStrategies = statCalculationStrategies;
	}

	public Map<String,Double> getCourseStatistics() {
		// TODO
		return null;
	}
	
	@Override
	public List<Course> findCourseByInstructorLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

}
