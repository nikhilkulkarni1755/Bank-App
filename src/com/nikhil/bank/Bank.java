package com.nikhil.bank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.io.*; 

public class Bank {
	List<Account> accountList;

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountLists) {
		this.accountList = accountLists;
	}

	public Bank() {
		accountList = new ArrayList<Account>();
	}

	public static void main(String[] args) {
		// this finds all the text in the txt file, and puts it in the arraylist.
		//
		Bank myBank = new Bank();

		List<String> accounts = new ArrayList<String>();
		String fileName = "accounts.txt";
		String line = null;

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// this will add all the lines inside of the txt file into the accounts
			// arraylist
			while ((line = bufferedReader.readLine()) != null) {
				accounts.add(line);
				myBank.getAccountList().add(myBank.createAccount(line));
				bufferedReader.close();
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Error finding " + fileName);
		} catch (IOException ex) {
			System.out.println("Error reading " + fileName);
		}

		myBank.welcome(myBank.getAccountList());
	}
	
	public void updateTxtFile(List<Account> accountList)
	{
		try {
			FileWriter fileWriter = new FileWriter("accounts.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for(int i = 0; i < accountList.size(); i++)
			{
				bufferedWriter.write(accountList.get(i).toTxt());
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
		}catch(IOException ex){
			System.out.println("IO Exception. "); 
		}
	}

	public void welcome(List<Account> accountList) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Would you like to log in (L) or sign up (S)? or SortNames(SN) or SortAccountNumbers(SA)");
		String ans = scanner.nextLine();
		if (ans.equals("L") || ans.equals("l"))
			logIn(accountList);
		else if (ans.equals("S") || ans.equals("s"))
			signUp(accountList);
		else if (ans.equals("SN") || ans.equals("sn") || ans.equals("Sn") || ans.equals("sN"))
			sortNames(accountList);
		else if (ans.equals("SA") || ans.equals("sa") || ans.equals("Sa") || ans.equals("sA"))
			sortAccountNumbers(accountList);
		else {
			System.out.println("Invalid input. Try again. ");
			welcome(accountList);
		}
	}

	// this will be used if the user wants to log in and access his/her account.
	public void logIn(List<Account> accounts) {
		System.out.println("Enter username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Enter pin: ");
		int pin = scanner.nextInt();

		Account myAccount = null;
		for (Account a : accounts) {
			if (a.getUserName().equals(username)) {
				if (a.getPIN() == pin) {
					System.out.println("Welcome " + a.getName() + " to the Bank of Nikhil.");
					a.setLogInStatus(true);
					myAccount = a;
					break;
				} else {
					System.out.println("Incorrect pin. Try again.");
					logIn(accounts);
					return;
				}
			}
		}
		
		if (myAccount == null) {
			System.out.println("There is no username: " + username);
			System.out.println("Try again. ");
			logIn(accounts);
		} else {
			myAccount.chooseAction();
		}
		
		
	}

	// this will be used to create a new account for someone
	public void signUp(List<Account> accounts) {
		sortNames(accounts); /////////////////////////////////////////////////////////////
		updateTxtFile(accounts);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Bank of Nikhil.");
		boolean isOriginal = true;
		// checks whether the username has already been taken or not
		while (isOriginal == true) {
			System.out.println("Enter a username");
			String username = scanner.nextLine();
			for (Account a : accounts) {
				if ((a.getUserName().equals(username))) {
					isOriginal = false;
					System.out.println("Please choose another username. This username has already been taken. ");
					signUp(accounts);
					break;

				}
			}

		}
		// it will come here only if the username hasnt been taken yet
		int pin = createPin(); 


		
		
		//createAccount(); 
		

	}
	
	//HELPER METHODS FOR WHEN SIGNING UP. 
	
	private int createPin() {//this is going to be a helper method, where the pin will be created. 
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter your pin. ");
		int pin1 = scanner.nextInt();
		int pin = 0; 
		if(is4Digits(pin1))
		{
			System.out.println("Enter your pin again. ");
			int pin2 = scanner.nextInt(); 
			if(is4Digits(pin2))
			{
				if(pin1 == pin2)
				{
					pin = pin1;
					System.out.println("Both PINs match. ");
				}
				else 
				{
					System.out.println("The pins do not match. Try again. "); 
					createPin(); 
				}
			}
			else {
				System.out.println("This is not a valid pin code, it needs to be 4 digits. ");
				createPin();
			}
			
		}
		else {
			System.out.println("This is not a valid pin code, it needs to be 4 digits. ");
			createPin();
		}
		
		return pin; 
	}

	// check whether the pin is 4 digits or no.
	private boolean is4Digits(int pin) {
		if(pin > 999 && pin < 10000)
			return true; 
		else 
			return false; 
	}

	// whenever signing up, the list will be sorted based on the account numbers, so
	// that we know what account numbers are free.
	
	//CHECK WHEN WE CAN SORT BASED ON NAMES
	public void sortAccountNumbers(List<Account> nums) {
		int smallest = 1000; //the largest Integer possible. GENIUS.
	    int index = 0;

	    for(int i = 0; i < nums.size(); i++)
	    {
	      smallest = 1000; 

	      for(int j = i; j < nums.size(); j++)
	      {
	        //this will run through the whole array
	        if(nums.get(j).getAccountNumber() < smallest)
	        {
	          smallest = nums.get(j).getAccountNumber();
	          index = j;
	        }



	      }

	      //swapping the smallest with the one most recently not sorted.
	      nums.set(index, nums.get(i));
	      nums.set(smallest,nums.get(i));

	      System.out.println();
	      for(int iter = 0; iter < nums.size(); iter++)
	      {
	        System.out.println(nums.get(iter));
	      }

	    }

	}

	// whenever logging in, so that there will be more efficiency whenever trying to
	// access an element.
	public void sortNames(List<Account> accountNames) {

		//myBank.getAccountList().add(myBank.createAccount(line));
	    String smallest = "zzzzzzzzzzz";
	    int index = 0;
	    Account small = null; 
	    for(int i = 0; i < accountNames.size(); i++)
	    {
	      smallest = "zzzzzzzzzzz";
	      for(int j = i; j < accountNames.size(); j++)
	      {
	          // Anderson is going to be less than Bob, as A is less than B in ascii
	          if(accountNames.get(j).getName().compareTo(smallest) < 0)
	          {
	            smallest = accountNames.get(j).getName();
	            small = accountNames.get(j); 
	            index = j;
	          }
	      }
	      
	      Account temp = accountNames.get(i); 
	      accountNames.set(i, small); 
	      accountNames.set(index, temp);
	    }
	    
	    updateTxtFile(accountNames);
	}

	// this will be used to create the account from the String on accounts.txt by
	// parsing through the string version
	// in the accounts.txt file.
	public Account createAccount(String account) {
		// Nikhil Kulkarni | MacAndPC | 382.5 | 9823 | 1
		String name = account.substring(0, account.indexOf(" | "));
		account = account.substring(account.indexOf(" | ") + 3);
		String username = account.substring(0, account.indexOf(" | "));
		account = account.substring(account.indexOf(" | ") + 3);
		String bal = account.substring(0, account.indexOf(" | "));
		double balance = Double.parseDouble(bal);
		account = account.substring(account.indexOf(" | ") + 3);
		String pin = account.substring(0, account.indexOf(" | "));
		int pinCode = Integer.parseInt(pin);
		account = account.substring(account.indexOf(" | ") + 3);
		int accountNumber = Integer.parseInt(account);

		return new Account(name, username, balance, pinCode, accountNumber);

	}

}
