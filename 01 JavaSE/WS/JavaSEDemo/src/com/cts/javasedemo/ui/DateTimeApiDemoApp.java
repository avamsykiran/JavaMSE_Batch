package com.cts.javasedemo.ui;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateTimeApiDemoApp {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		System.out.println("Joining Date ('dd-MM-yyyy'): ");
		LocalDate joinDate = LocalDate.parse(scan.next(),formatter);
		
		LocalDate today = LocalDate.now();
		System.out.println("Today: " + today.format(formatter));
		
		Period experience = Period.between(joinDate, today);
		
		System.out.println("Exprience: " + experience.getYears() + " yrs approx");
		System.out.println("Exprience: " + (experience.toTotalMonths()/12.0) + " yrs");

		scan.close();
	}

}
