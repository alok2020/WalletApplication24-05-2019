package payment.ibm.service;

import payment.ibm.bean.AccountUser;

public interface serviceInterface {

	public abstract void  createAcount(String userName,long userphone,String userAdd);
	public abstract void depositMoney(long accountno,long amount );
	public abstract void lowBalance(long accountno);
	public abstract void withdraw(long accountno,long amount);
	public abstract void fundTransfer(long toaccountno,long getteraccount,long amount );
	public abstract void printTransactions(Long accountno,Long option,long option2);
	public abstract void printTransactions(Long accountno,Long option,String option2);
	
	
}
