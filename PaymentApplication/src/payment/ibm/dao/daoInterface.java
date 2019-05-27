package payment.ibm.dao;

import payment.ibm.bean.AccountUser;

public interface daoInterface {

	
	
	
	public abstract boolean createAcount(AccountUser au);
	public abstract boolean depositMoney(long accountno,long amount);
	public abstract boolean lowBalance(AccountUser au);
	public abstract boolean withdraw(long accountno,long amount);
	public abstract boolean fundTransfer(long useraccountno,long getteraccount,long amount);
	public abstract void printTransactions(Long accountno,Long option,Long option2);
	public abstract void printTransactions(Long accountno,Long option,String option2);
	
	
	
}
