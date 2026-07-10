package com.cts.javasedemo.ui;

import java.util.function.Consumer;

public class ThreadDemo2 {

	public static void main(String[] args) {
		
		Consumer<Integer> printSeries = (n) -> {
			String currentThreadName = Thread.currentThread().getName();
			for(int i=1;i<=n;i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
				System.out.println(currentThreadName  + ">> "+ i);
			}
		};		
		
		printSeries.accept(5);
		printSeries.accept(5);
		printSeries.accept(5);
		
		Runnable runnable = () -> {
			int n=5;
			String currentThreadName = Thread.currentThread().getName();
			for(int i=1;i<=n;i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
				System.out.println(currentThreadName  + ">> "+ i);
			}
		};
		
		Thread t1 = new Thread(runnable, "Thread1");
		Thread t2 = new Thread(runnable, "Thread2");
		Thread t3 = new Thread(runnable, "Thread3");
		
		t1.start();
		t2.start();
		t3.start();		
	}

}
