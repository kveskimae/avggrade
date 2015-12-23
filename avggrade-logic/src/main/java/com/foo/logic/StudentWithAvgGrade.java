package com.foo.logic;

public class StudentWithAvgGrade {
	
    private final String name;
    
    private final Double weightedAvgGrade;

	public StudentWithAvgGrade(String name, Double weightedAvgGrade) {
		super();
		this.name = name;
		this.weightedAvgGrade = weightedAvgGrade;
	}

	public String getName() {
		return name;
	}

	public Double getWeightedAvgGrade() {
		return weightedAvgGrade;
	}

}
