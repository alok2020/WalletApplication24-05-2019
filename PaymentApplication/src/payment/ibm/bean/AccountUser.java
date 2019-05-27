package payment.ibm.bean;

public class AccountUser {
	
		private long account_no;
		private String user_name;
		private long user_phone;
		private String user_Add;
		
//		AccountUser(long accno,String username,long phone,String add){
//			this.account_no=accno;
//			this.user_name=username;
//			this.user_phone=phone;
//			this.user_Add=add;
//			
//		}
		
		public long getAccount_no() {
			return account_no;
		}
		
		public void setAccount_no(long account_no) {
			this.account_no = account_no;
		}
		
		public String getUser_name() {
			return user_name;
		}
		
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		
		public long getUser_phone() {
			return user_phone;
		}
		
		public void setUser_phone(long user_phone) {
			this.user_phone = user_phone;
		}
		
		public String getUser_Add() {
			return user_Add;
		}
		
		public void setUser_Add(String user_Add) {
			this.user_Add = user_Add;
		}

			
}
