package com.zengineers.courses.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zengineers.courses.dao.CourseDAO;
import com.zengineers.courses.model.Course;
import com.zengineers.courses.service.statistics.StatisticStrategy;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
//	@Qualifier("CourseDAOImpl")
	private CourseDAO courseDAO;
	private List<StatisticStrategy> statCalculationStrategies;
	
	@Autowired
	public CourseServiceImpl(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
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
	@Transactional
	public List<Course> findAll() {
		return courseDAO.findAll();
	}
	
	@Override
	@Transactional
	public void delete(Long courseId) {
		courseDAO.deleteById(courseId);
	}

	@Override
	@Transactional
	public void save(Course course) {
		courseDAO.save(course);
	}

	@Override
	@Transactional
	public Course update(Long courseId) {
		Course course = courseDAO.findById(courseId).get();
		
		if (course != null ) {
			return course;
		}
		throw new RuntimeException("Did not find course with id: " + courseId);
	}

}
