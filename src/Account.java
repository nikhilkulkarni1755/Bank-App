
public class Account
{
	private double balance;
	private String name; //of the person with the account
	private int pin; // 4 digit pin code
	private int accountNumber;//use random number, check if has been used yet


	public Account(String name, double money, int pin, int accountNumber)
	{
		this.name = name;
		this.balance = money;
		this.pin = pin;
		this.accountNumber = accountNumber;
	}

	public String getName()
	{
		return this.name;
	}

	public double getBalance()
	{
		return this.balance;
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

	public void changeBalance(double num, boolean add)//if add is false, we need to take out the money
	{
		if(add == true)
			this.balance += num;
		else
			this.balance -= num;
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

	//i hope people cannot change account numbers.



}
