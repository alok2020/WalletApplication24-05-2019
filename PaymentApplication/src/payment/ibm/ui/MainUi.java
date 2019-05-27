package payment.ibm.ui;

import java.util.Scanner;
import java.util.regex.Pattern;

import payment.ibm.service.serviceClass;

public class MainUi {

	public static void main(String[] args) {
		System.out.println("Press 1 To create User Account");
		System.out.println("Press 2. To Deposit Money");
		System.out.println("Press 3.To withdraw money from account");
		System.out.println("Press 4.To Transfer money from account");
		System.out.println("Press 5.To check statment of any account");
		Scanner scan = new Scanner(System.in);
		serviceClass service= new serviceClass();
				try {
							int choice=scan.nextInt();
							if(choice==1) {
								scan.nextLine();
									while(true)
										{System.out.println("Enter the name of the user");	
										String nameofuser=scan.nextLine();
										//System.out.println(checkusername(nameofuser));
										if(checkusername(nameofuser)==false)
										{	
											System.out.println("ENTER ONLY ALPHABETS");
											break;
											
										}
										System.out.println("Enter Phone no of user");
										long phoneno=scan.nextLong();
										System.out.println("Enter address of user");
										scan.nextLine();
										String address=scan.nextLine();
										
										service.createAcount(nameofuser,phoneno,address);
			
						    }
									
							}
							if(choice==2) {
								scan.nextLine();
								System.out.println("Enter the account number you wan t to deposit into");
								long accountno=scan.nextLong();
								System.out.println("Enter the amount you want to deposit into");
								long amount = scan.nextLong();
								service.depositMoney(accountno,amount);
							}
							if(choice==3) {
								scan.nextLine();
								System.out.println("Enter the account number you want to withdraw from");
								long accountno=scan.nextLong();
								System.out.println("Enter the amount you want to withdraw");
								long amount = scan.nextLong();
								service.withdraw(accountno,amount);
							}
							if(choice==4) {
								scan.nextLine();
								System.out.println("Enter the account number you want to transfer from");
								long accountno=scan.nextLong();
								System.out.println("Enter the account number you want to transfer into");
								long getterno=scan.nextLong();
								
								System.out.println("Enter the amount you want to withdraw");
								long amount = scan.nextLong();
								service.fundTransfer(accountno, getterno, amount);
							}
							
							
							if(choice==5) {
								scan.nextLine();
								System.out.println("Enter the account number you want to check transaction for");
								long accountno=scan.nextLong();
								
								
								System.out.println("Enter the type of statment you want");
								System.out.println("Press 1. to check the mini statement");
								System.out.println("Press 2. to check the  statement according to money transfer");
								System.out.println("Press 3. to check the  statement according to type of money transfer");
								
								long statementchoice=scan.nextLong();
								if(statementchoice==1) {
									service.printTransactions(accountno,statementchoice,0L);	
								}
								else if(statementchoice==2) {
									System.out.println("Enter the minimum transaction amount");
									long transactionlimit=scan.nextLong();
									service.printTransactions(accountno,statementchoice,transactionlimit);
								}
								else if(statementchoice==3) {
									System.out.println("Enter d for money deposited");
									System.out.println("Enter w for money withdrawl");
									System.out.println("Enter f for money transfer");
								String charchoice=scan.nextLine();
								service.printTransactions(accountno,statementchoice,charchoice);
								}
								
								
								
							}
							
							
							
							else {
								main(new String[] {"sda","asd"} );
							}
				}
				catch (Exception e) {
				System.out.println("Enter options from above");
				main(new String[] {"sda","asd"} );
			
			// TODO: handle exception
				}
		
		
	}
	
	static boolean checkusername(String name)
	{
		Pattern pattern = Pattern.compile("[A-Za-z]+");
	

		if( (name != null) && pattern.matcher(name).matches())
		return true;
		else
		return false;
	
		
	}
	
}