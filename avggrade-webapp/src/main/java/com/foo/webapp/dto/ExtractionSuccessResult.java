package com.foo.webapp.dto;

import java.util.List;

import com.foo.logic.StudentWithAvgGrade;

public class ExtractionSuccessResult implements IExtractionResult {
	
	public final List<StudentWithAvgGrade> students;
	
	public ExtractionSuccessResult(final List<StudentWithAvgGrade> students) {
		super();
		this.students = students;
	}

}
