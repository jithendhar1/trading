package beans;

public class WithdrawalBean {
	private String WithdrawalID;
	private String userID;
	private String WithdrawalTransactionID;
	private String WithdrawalDate;
	private String Amount;
	private String statsu;
	
	
	
	public String getStatsu() {
		return statsu;
	}
	public void setStatsu(String statsu) {
		this.statsu = statsu;
	}
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
	public String getWithdrawalTransactionID() {
		return WithdrawalTransactionID;
	}
	public void setWithdrawalTransactionID(String withdrawalTransactionID) {
		WithdrawalTransactionID = withdrawalTransactionID;
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
