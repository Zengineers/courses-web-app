package com.zengineers.courses.service.statistics;

import com.zengineers.courses.model.Course;

public interface StatisticStrategy {

	public double calculateStatistic(Course course);
}
