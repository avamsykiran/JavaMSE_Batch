package com.cts.javasedemo.models;

import java.util.Objects;

public class Student {
	
	private long admno;
	private String fullName;
	private String grade;
	private double fee;
	
	public Student() {
		super();
	}
	
	public Student(long admno, String fullName, String grade, double fee) {
		super();
		this.admno = admno;
		this.fullName = fullName;
		this.grade = grade;
		this.fee = fee;
	}

	public long getAdmno() {
		return admno;
	}

	public void setAdmno(long admno) {
		this.admno = admno;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(admno, fee, fullName, grade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return admno == other.admno && Double.doubleToLongBits(fee) == Double.doubleToLongBits(other.fee)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(grade, other.grade);
	}

	@Override
	public String toString() {
		return "admno=" + admno + ", fullName=" + fullName + ", grade=" + grade + ", fee=" + fee;
	}
	
	

}
