package beans;

public class DepositBean {

	private String DepositID;
	private String DepositTransactionID;
	private String DepositDate;
	private String Amount;
	private String userID;
	
	
	public String getDepositID() {
		return DepositID;
	}
	public void setDepositID(String depositID) {
		DepositID = depositID;
	}
	public String getDepositTransactionID() {
		return DepositTransactionID;
	}
	public void setDepositTransactionID(String depositTransactionID) {
		DepositTransactionID = depositTransactionID;
	}
	public String getDepositDate() {
		return DepositDate;
	}
	public void setDepositDate(String depositDate) {
		DepositDate = depositDate;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
