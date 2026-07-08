package com.cts.javasedemo.ui;

import com.cts.javasedemo.models.Hostler;
import com.cts.javasedemo.models.Student;

public class ClassAndObjectDemoApp {

	public static void main(String[] args) {
		Student s = new Student(101,"Vamsy","Grade - III",87000);
		Hostler h = new Hostler(102,"Sagar" , "Grade - X", 187900, 5001, 89000);
		
		System.out.println(s);
		System.out.println(h);
	}

}
