package com.cts.javasedemo.ui;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import com.cts.javasedemo.models.Employee;
import com.cts.javasedemo.service.EmployeeFullNameComparator;

public class SetDemo {
	public static void main(String a[]) {
		//Set<Employee> emps = new HashSet<>();
		//Set<Employee> emps = new LinkedHashSet<>();
		//Set<Employee> emps = new TreeSet<>();
		Set<Employee> emps = new TreeSet<>(new EmployeeFullNameComparator());
		
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
		
	}
}
