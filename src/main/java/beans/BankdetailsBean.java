package beans;

public class BankdetailsBean {
	private String userID;
	private String userName;
	private String Amount;
	private String AcountNumber;
	private String BankName;
	
	
	
	public String getAcountNumber() {
		return AcountNumber;
	}
	public void setAcountNumber(String acountNumber) {
		AcountNumber = acountNumber;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}


}
