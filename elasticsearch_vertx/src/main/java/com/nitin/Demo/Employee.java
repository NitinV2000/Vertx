package com.nitin.Demo;

public class Employee {
	private String empName;
	private String empJob;
	
	public Employee(String empName, String empJob) {
		super();
		this.empName = empName;
		this.empJob = empJob;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpJob() {
		return empJob;
	}

	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empJob=" + empJob + "]";
	}
	
}
