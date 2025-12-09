package com.cts.javasedemo.ui;

import java.util.Scanner;

import com.cts.javasedemo.exceptions.BankingException;
import com.cts.javasedemo.models.BankAccount;
import com.cts.javasedemo.service.BankingService;

public class BankingAppForExcepiotonsDemo {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		BankAccount acc = new BankAccount("100000011",12000 );
		BankingService bService = new BankingService();
		
		boolean shallContinue = true;
		
		while(shallContinue) {
			System.out.println("Deposiute/Withdraw/Quit? ");
			String cmd = scan.next().toLowerCase();
			
			System.out.println("Amount? ");
			double amt = scan.nextDouble();
			
			switch (cmd) {
			case "deposite": 
				try {
					bService.deposite(acc, amt);
				} catch (BankingException e) {
					System.out.println(e.getMessage());
				}				
				break;
			case "withdraw":
				try {
					bService.withdraw(acc, amt);
				} catch (BankingException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "quit":
				System.out.println("App Terminated");
				shallContinue=false;
				break;
			default:
				System.out.println("Unknown Command");
			}
		}
		
		scan.close();
	}

}
