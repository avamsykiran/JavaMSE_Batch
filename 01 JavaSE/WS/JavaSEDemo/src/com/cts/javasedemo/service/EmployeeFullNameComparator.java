package com.cts.javasedemo.service;

import java.util.Comparator;

import com.cts.javasedemo.models.Employee;

public class EmployeeFullNameComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getFullName().compareTo(o2.getFullName());
	}

}
