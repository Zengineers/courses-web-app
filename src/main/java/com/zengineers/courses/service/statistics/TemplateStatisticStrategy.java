package com.zengineers.courses.service.statistics;

import com.zengineers.courses.model.Course;

public abstract class TemplateStatisticStrategy implements StatisticStrategy {

	public TemplateStatisticStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double calculateStatistic(Course course) {
		// TODO
		prepareDataSet();
		return 0;
	}
	
	private void prepareDataSet() {
		// TODO	
	}
	
	protected abstract void doActualCalculation();
}
