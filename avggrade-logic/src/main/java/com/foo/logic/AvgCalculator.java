package com.foo.logic;

import java.util.ArrayList;
import java.util.List;

import com.foo.logic.gen.Course;
import com.foo.logic.gen.Student;
import com.foo.logic.gen.Students;

public class AvgCalculator {
	
	public List<StudentWithAvgGrade> calculateAvgGradeForStudents(final Students students) {
		List<StudentWithAvgGrade> ret = new ArrayList<StudentWithAvgGrade>();
		for (Student student : students.getStudent()) {
			Double weightedAvgGrade = calculateWeightedAvgGrade(student.getCourse());
			ret.add(new StudentWithAvgGrade(student.getName(), weightedAvgGrade));
		}
		return ret;
		
	}

	private Double calculateWeightedAvgGrade(final List<Course> courses) {
		if (courses.size() < 1) {
			return null;
		}
		int totalSum = 0, creditsSum = 0;
		for (Course course : courses) {
			totalSum += course.getCredits() * course.getGrade();
			creditsSum += course.getCredits();
		}
		return (double)totalSum / (double)creditsSum;
	}

}
