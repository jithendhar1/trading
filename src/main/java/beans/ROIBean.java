package beans;

public class ROIBean {

	private String TransactionID;
	private String userID;
	private String ROIAmount;
	private String ModifiedDate;
	private String OpenAmount;
	private String ClosingAmount;
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getROIAmount() {
		return ROIAmount;
	}
	public void setROIAmount(String rOIAmount) {
		ROIAmount = rOIAmount;
	}
	public String getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	public String getOpenAmount() {
		return OpenAmount;
	}
	public void setOpenAmount(String openAmount) {
		OpenAmount = openAmount;
	}
	public String getClosingAmount() {
		return ClosingAmount;
	}
	public void setClosingAmount(String closingAmount) {
		ClosingAmount = closingAmount;
	}
	
}
