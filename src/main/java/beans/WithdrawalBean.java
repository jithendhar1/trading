package beans;

public class WithdrawalBean {
	private String WithdrawalID;
	private String userID;
	private String AccountID;
	private String WithdrawalDate;
	private String Amount;
	public String getWithdrawalID() {
		return WithdrawalID;
	}
	public void setWithdrawalID(String withdrawalID) {
		WithdrawalID = withdrawalID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAccountID() {
		return AccountID;
	}
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	public String getWithdrawalDate() {
		return WithdrawalDate;
	}
	public void setWithdrawalDate(String withdrawalDate) {
		WithdrawalDate = withdrawalDate;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	
	
	
}
