package com.cts.javasedemo.ui;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cts.javasedemo.models.Employee;
import com.cts.javasedemo.service.EmployeeFullNameComparator;

public class ListDemo {
	public static void main(String a[]) {
		List<Employee> emps = new ArrayList<>();
		
		emps.add(new Employee(10, "Vamsy", 45000, LocalDate.of(2021, Month.MARCH, 10),Arrays.asList("Java","ReactJS")));		
		emps.add(new Employee(1, "Varun", 50000, LocalDate.of(2021, Month.MARCH, 1),Arrays.asList("Java","Angular")));
		emps.add(new Employee(4, "Vasanth", 55000, LocalDate.of(2021, Month.MARCH, 20),Arrays.asList("Java","DevOps")));
		emps.add(new Employee(2, "Vijay", 35000, LocalDate.of(2021, Month.MARCH, 5),Arrays.asList("Python","ReactJS")));
		emps.add(new Employee(7, "Varma", 25000, LocalDate.of(2021, Month.MARCH, 6),Arrays.asList("Python","Angular")));
		emps.add(new Employee(3, "Vasavi", 145000, LocalDate.of(2021, Month.MARCH, 7),Arrays.asList("C","C++")));
		emps.add(new Employee(9, "Vasundhara", 85000, LocalDate.of(2021, Month.MARCH, 21),Arrays.asList("Java",".Net")));
	
		for(Employee e : emps) {
			System.out.println(e);
		}
		
		System.out.println("---------------------------------------------------------------------");
		Collections.sort(emps);
		for(Employee e : emps) {
			System.out.println(e);
		}
		
		System.out.println("---------------------------------------------------------------------");
		Collections.sort(emps, new EmployeeFullNameComparator());
		for(Employee e : emps) {
			System.out.println(e);
		}
	}
}
