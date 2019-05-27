package payment.ibm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TimeZone;

import payment.ibm.bean.AccountUser;
import payment.ibm.ui.MainUi;

public class daoClass implements daoInterface{
	Connection dbCon=null;
	public daoClass() {
		String url = "jdbc:mysql://localhost:3306/payment?serverTimezone=" + TimeZone.getDefault().getID();
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbCon= DriverManager.getConnection(url,"root","");


			if(dbCon!=null) {
				System.out.println("Working");
				
				}
		}
		catch (ClassNotFoundException | SQLException  e) {
			System.out.println("Not working"+e.getMessage());
			// TODO: handle exception
		}

	}

	@Override
	public boolean createAcount(AccountUser au) {
		// TODO Auto-generated method stub
		String getQry="Insert into user_account values(?,?,?,?)";
		String getQry2="Insert into amount_table values(?,?)";
		String getQry3="Insert into log_table values(?,?,?)";
		PreparedStatement statement=null;
		long timeSeed = System.nanoTime(); 
        double randSeed = Math.random() * 1000; 

        long midSeed = (long) (timeSeed * randSeed); 

        String s = midSeed + "";
        String subStr = s.substring(0, 9);

        int finalSeed = Integer.parseInt(subStr); 
		try {
			
		 statement=dbCon.prepareStatement(getQry);
			statement.setLong(1,finalSeed);
			statement.setString(2,au.getUser_name());
			statement.setLong(3,au.getUser_phone());
			statement.setString(4,au.getUser_Add());
			int rs=statement.executeUpdate();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println("Not working 1"+e);
		MainUi.main(new String[] {"sda","asd"} );
		}
		
		try {
			 statement=dbCon.prepareStatement(getQry2);
				statement.setLong(1,finalSeed);
				statement.setLong(2,0);
				int rs=statement.executeUpdate();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println("Not working 2"+e);
			MainUi.main(new String[] {"sda","asd"} );
			}
		
		try {
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now(); 
			 statement=dbCon.prepareStatement(getQry3);
			 String message="C Account Created"+" "+dtf.format(now);
				statement.setLong(1,finalSeed);
				statement.setLong(2,0);
				statement.setString(3,message);
				int rs=statement.executeUpdate();
				if(rs>0) {
					System.out.println("Account created");
					MainUi.main(new String[] {"sda","asd"} );
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println("Not working 3");
			}
		
		
		return false;
		
		
	}

	@Override
	public boolean depositMoney(long accountno,long amount) {
		
		String updateQry="UPDATE amount_table SET amount=? where account_no=?";
		String checkString="Select * from amount_table where account_no=?";
		String logString="Insert into log_table values(?,?,?)";
		try {
			
				
			PreparedStatement statement=dbCon.prepareStatement(checkString);
			statement.setLong(1,accountno);
			ResultSet  rs= statement.executeQuery();
	
			if(rs.first()) {
				long newamount=rs.getLong("amount")+amount;
				 statement=dbCon.prepareStatement(updateQry);
				statement.setLong(1,newamount);
			statement.setLong(2,accountno);
			int executeans=statement.executeUpdate();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now(); 
			 statement=dbCon.prepareStatement(logString);
			 String message="D Money Deposited"+" "+amount+" "+" "+dtf.format(now);
				statement.setLong(1,accountno);
				statement.setLong(2,0);
				statement.setString(3,message);
				int isdone=statement.executeUpdate();
			
			}
			else {
				System.out.println("Enter a valid Account number");
				MainUi mu= new MainUi();
				mu.main(new String[]{"asd","asd"});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println("Not working"+e);
		MainUi.main(new String[] {"sda","asd"} );
		}

		
		return true;
	}

	@Override
	public boolean lowBalance(AccountUser au) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdraw(long accountno,long amount) {
		// TODO Auto-generated method stub
		String updateQry="UPDATE amount_table SET amount=? where account_no=?";
		String checkString="Select * from amount_table where account_no=?";
		String logString="Insert into log_table values(?,?,?)";
		try {
			
				
			PreparedStatement statement=dbCon.prepareStatement(checkString);
			statement.setLong(1,accountno);
			ResultSet  rs= statement.executeQuery();
	
			if(rs.first()) {
				if(rs.getLong("amount")>amount) {
					
					
				
				long newamount=rs.getLong("amount")-amount;
				 statement=dbCon.prepareStatement(updateQry);
				statement.setLong(1,newamount);
			statement.setLong(2,accountno);
			int executeans=statement.executeUpdate();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now(); 
			 statement=dbCon.prepareStatement(logString);
			 String message="W Money Withddrawn"+" "+amount+" "+" "+dtf.format(now);
				statement.setLong(1,accountno);
				statement.setLong(2,0);
				statement.setString(3,message);
				int isdone=statement.executeUpdate();
				}
				else {
		System.out.println("Cant withdraw amount is 0");	
		MainUi.main(new String[] {"sda","asd"} );
				}
				
			
			}
				
			else {
				System.out.println("Enter a valid Account number");
				MainUi mu= new MainUi();
				mu.main(new String[]{"asd","asd"});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println("Not working"+e);
		MainUi.main(new String[] {"sda","asd"} );
		}

		
		return true;
	}

	@Override
	public boolean fundTransfer(long useraccountno,long getteraccount,long amount) {
		// TODO Auto-generated method stub
		
		String updateQry="UPDATE amount_table SET amount=? where account_no=?";
		String updateQry2="UPDATE amount_table SET amount=? where account_no=?";
		String checkString="Select * from amount_table where account_no=?";
		String logString="Insert into log_table values(?,?,?)";
		try {
			
				
			PreparedStatement statement=dbCon.prepareStatement(checkString);
			statement.setLong(1,useraccountno);
			ResultSet  rs= statement.executeQuery();
	
			if(rs.first()) {
				if(rs.getLong("amount")>amount) {
				long newamount=rs.getLong("amount")-amount;
				 statement=dbCon.prepareStatement(updateQry);
				statement.setLong(1,newamount);
			statement.setLong(2,useraccountno);
			int executeans=statement.executeUpdate();
		
			PreparedStatement statement2=dbCon.prepareStatement(checkString);
			statement2.setLong(1,getteraccount);
			  rs= statement2.executeQuery();
		
			rs.next();
			long addamount=rs.getLong("amount")+amount;
			 statement=dbCon.prepareStatement(updateQry2);
			statement.setLong(1,addamount);
		statement.setLong(2,getteraccount);
		int executeans2=statement.executeUpdate();
		
			
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now(); 
			 statement=dbCon.prepareStatement(logString);
			 String message="F Money Transfer"+" "+amount+" "+dtf.format(now);
				statement.setLong(1,useraccountno);
				statement.setLong(2,getteraccount);
				statement.setString(3,message);
				int isdone=statement.executeUpdate();
			
				
				
			}
				else{
					System.out.println("Not sufficient amount");
					MainUi.main(new String[] {"sda","asd"} );
			}
		}
			else {
				System.out.println("Enter a valid Account number");
				MainUi mu= new MainUi();
				mu.main(new String[]{"asd","asd"});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println("Not working"+e);
		MainUi.main(new String[] {"sda","asd"} );
		}
		
		
		
		return false;
	}

	@Override
	public void printTransactions(Long accountno,Long option,Long option2) {
		if(option==1) {
		String checkString="Select * from log_table where account_no=?";
		try {
		PreparedStatement statement=dbCon.prepareStatement(checkString);
		statement.setLong(1,accountno);
		ResultSet  rs= statement.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("message"));
		}
		}
		
		catch (Exception e) {
			// TODO: handle exception
			MainUi.main(new String[] {"sda","asd"} );
		}
		MainUi.main(new String[] {"sda","asd"} );
		
	}
		if(option==2) {
			String checkString="Select * from log_table where account_no=?";
			try {
			PreparedStatement statement=dbCon.prepareStatement(checkString);
			statement.setLong(1,accountno);
			ResultSet  rs= statement.executeQuery();
			boolean flag=false;
			while(rs.next()) {
				String message=rs.getString("message");
				String[] messagearr=message.split(" ");
				if(messagearr[0].equalsIgnoreCase("C")){
				}
				
				else {
				if(Integer.parseInt(messagearr[3])>option2) {
					System.out.println(message);
					flag=true;
				}
				}
			}
			if(flag) {
				MainUi.main(new String[] {"sda","asd"} );
			}
			else {
				System.out.println("No such Transaction");
				MainUi.main(new String[] {"sda","asd"} );
			}
			}
			
			catch (Exception e) {
				// TODO: handle exception
			}	
		}

}
	 
	public void printTransactions(Long accountno,Long option,String option2) {
		String checkString="Select * from log_table where account_no=?";
		try {
		PreparedStatement statement=dbCon.prepareStatement(checkString);
		statement.setLong(1,accountno);
		ResultSet  rs= statement.executeQuery();
		boolean flag=false;
		while(rs.next()) {
			String message=rs.getString("message");
			String[] messagearr=message.split(" ");
			if(messagearr[0].equalsIgnoreCase(option2)) {
				System.out.println(message);
				flag=true;
				
			}
			
		}
		if(flag) {
			MainUi.main(new String[] {"sda","asd"} );	
		}
		else {
			System.out.println("No such Transaction");
			MainUi.main(new String[] {"sda","asd"} );
		}
		}
		
		catch (Exception e) {
			// TODO: handle exception
			MainUi.main(new String[] {"sda","asd"} );
		}	
				
	}
	}
