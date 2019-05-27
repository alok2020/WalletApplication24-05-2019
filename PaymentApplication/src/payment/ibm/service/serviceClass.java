package payment.ibm.service;

import payment.ibm.bean.AccountUser;
import payment.ibm.dao.daoClass;

public class serviceClass implements serviceInterface {
AccountUser user;
daoClass dao= null;
	@Override
	public void createAcount( String userName, long userphone, String userAdd) {
	user = new AccountUser();
	user.setUser_name(userName);
	user.setUser_phone(userphone);
	user.setUser_Add(userAdd);
	dao=new daoClass();
	dao.createAcount(user);	
	}

	@Override
	public void depositMoney(long accountno, long amount) {
		dao=new daoClass();
		dao.depositMoney(accountno,amount);
		
	}

	@Override
	public void lowBalance(long accountno) {
		
		
	}

	@Override
	public void withdraw(long accountno, long amount) {
	
		dao=new daoClass();
		dao.withdraw(accountno,amount);
	
	
	
	}

	@Override
	public void fundTransfer(long toaccountno,long getteraccount, long amount) {
		
		dao=new daoClass();
		dao.fundTransfer(toaccountno, getteraccount, amount);
	

	
	}

	@Override
	public void printTransactions(Long accountno,Long option,long option2) {
		dao=new daoClass();
		dao.printTransactions(accountno,option,option2);
		
	
	}

	public void printTransactions(Long accountno,Long option,String option2) {
		dao=new daoClass();
		dao.printTransactions(accountno,option,option2);
		
	
	}


}
