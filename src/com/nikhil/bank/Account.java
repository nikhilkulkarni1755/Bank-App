package com.nikhil.bank;

import java.util.Scanner; 

public class Account 
{
	private double balance; 
	private String name; //of the person with the account
	private String username; //this is going to be the unique part of the name
	private int pin; // 4 digit pin code 
	private int accountNumber;//use random number, check if has been used yet
	private boolean isLoggedIn; 
	
	
	public Account(String name, String user, double money, int pin, int accountNumber)
	{
		this.name = name; 
		this.username = user; 
		this.balance = money;
		this.pin = pin; 
		this.accountNumber = accountNumber;
		isLoggedIn = false; 
	}
	
	public String getName()
	{
		return this.name; 
	}
	
	public String getUserName()
	{
		return this.username; 
	}
	
	public double getBalance()
	{
		return this.balance; 
	}
	
	protected int getPIN()
	{
		return this.pin; 
	}
	
	protected int getAccountNumber()
	{
		return this.accountNumber; 
	}
	
	//cannot create a method that returns the pin, thats absurd
	
	//not sure whether we can just get the account number. 
	
	/*
	 *public int getAccountNumber()
	{
		return this.accountNumber; 
	}
	 */
	
	//use boolean whenever there is an iffy method, not something sure shot like this. 
	
	public void setName(String name)
	{
		this.name = name; 	
	}
	
	public void setLogInStatus(boolean a)
	{
		isLoggedIn = a; 
	}
	
	public void setUserName(String user)
	{
		this.username = user; 
	}
	
	public void changeBalance(double num, boolean add)//if add is false, we need to take out the money
	{
		if(add == true)
			this.balance += num; 
		else
		{
			if(balance >= num)
				this.balance -= num;
			else
				System.out.println("You do not have " + num + "$ in your account currently. ");
		}
	}
	
	public boolean changePIN(int pin)//must be 4 digits
	{
		if(pin > 999 && pin < 10000)
		{
			this.pin = pin;
			System.out.println("You have successfully changed your PIN. Congrats.");
			return true; 
		}
		else
		{
			System.out.println("The PIN number you have chosen is not 4 digits, please try again. ");
			return false; 
		}
	}
	
	
	public String toString()//account statement
	{
		return username + " has a total of " + balance + " $ currently with the Bank of Nikhil."; 
	}
	
	public String toTxt()
	{
		//Raj Nguyen | dvwcjaoabu | 78283.41 | 3992 | 472
		return getName() + " | " + getUserName() + " | " + getBalance() + " | " + getPIN() + " | " + getAccountNumber(); 
	}

	public void chooseAction() {
		// TODO Auto-generated method stub
		System.out.println(toString()); 
		System.out.println("Choose function you want to use. ");
		System.out.println("Enter 1 if you want to add or take out money. ");
		System.out.println("Enter 2 if you want to change your name ");
		System.out.println("Enter 3 if you want to change your username ");
		System.out.println("Enter 4 if you want to change your pin code. ");
		System.out.println("Enter 5 to log out of your account. "); 
		Scanner scanner = new Scanner(System.in); 
		int ans = scanner.nextInt();
		
		if(ans == 1)
		{
			Scanner scan = new Scanner(System.in); 
			System.out.println("Would you like to add money(A) or take out money(T) from your account?");
			String s = scan.nextLine(); 
			if(s.equals("A") || s.equals("a"))
			{
				System.out.println("Enter the amount of money you would like to add to your account: ");
				double money = scan.nextDouble(); 
				changeBalance(money, true);
				chooseAction();
				
			}
			if(s.equals("T") || s.equals("t"))
			{
				System.out.println("Enter the amount of money you would like to take out of your account: ");
				double money = scan.nextDouble(); 
				changeBalance(money, false);
				chooseAction(); 
			}
		}
		else if(ans == 2)
		{
			
		}
		else if(ans == 3)
		{
			
		}
		else if(ans == 4)
		{
			
		}
		else if(ans == 5)
		{
			
		}
		else
		{
			System.out.println("You entered wrong input. Please try again. ");
			chooseAction(); 
		}
		
		 
		
	}
	
	
	
	
	
}
