package com.cts.javasedemo.ui;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cts.javasedemo.models.Employee;

public class ListDemoWithFunctionalInterfaces {
	public static void main(String a[]) {
		List<Employee> emps = new ArrayList<>();
		
		emps.add(new Employee(10, "Vamsy", 45000, LocalDate.of(2021, Month.MARCH, 10)));
		emps.add(new Employee(10, "Vamsy", 45000, LocalDate.of(2021, Month.MARCH, 10)));
		emps.add(new Employee(1, "Varun", 50000, LocalDate.of(2021, Month.MARCH, 1)));
		emps.add(new Employee(4, "Vasanth", 55000, LocalDate.of(2021, Month.MARCH, 20)));
		emps.add(new Employee(2, "Vijay", 35000, LocalDate.of(2021, Month.MARCH, 5)));
		emps.add(new Employee(7, "Varma", 25000, LocalDate.of(2021, Month.MARCH, 6)));
		emps.add(new Employee(3, "Vasavi", 145000, LocalDate.of(2021, Month.MARCH, 7)));
		emps.add(new Employee(9, "Vasundhara", 85000, LocalDate.of(2021, Month.MARCH, 21)));
	
		for(Employee e : emps) {
			System.out.println(e);
		}
		
		System.out.println("---------------------------------------------------------------------");
		Collections.sort(emps);
		for(Employee e : emps) {
			System.out.println(e);
		}
		
		System.out.println("---------------------------------------------------------------------");
		Collections.sort(emps, (e1,e2) -> e1.getFullName().compareTo(e2.getFullName()));
		for(Employee e : emps) {
			System.out.println(e);
		}
		
		System.out.println("---------------------------------------------------------------------");
		Collections.sort(emps, (e1,e2) -> e1.getHireDate().compareTo(e2.getHireDate()));
		for(Employee e : emps) {
			System.out.println(e);
		}
		
		System.out.println("---------------------------------------------------------------------");
		Collections.sort(emps, (e1,e2) -> ((Double)e1.getSalary()).compareTo(e2.getSalary()));
		for(Employee e : emps) {
			System.out.println(e);
		}
	}
}
