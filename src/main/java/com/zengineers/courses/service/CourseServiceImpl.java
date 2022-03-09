package com.zengineers.courses.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zengineers.courses.dao.CourseDAO;
import com.zengineers.courses.model.Course;
import com.zengineers.courses.service.statistics.StatisticStrategy;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	@Qualifier("CourseDAOImpl")
	private CourseDAO courseDAO;
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
	@Transactional
	public void save(Course course) {
		System.out.println("> CourseService");
		courseDAO.save(course);
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

}
