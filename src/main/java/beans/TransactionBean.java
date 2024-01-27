package beans;

public class TransactionBean {

	private String userID;
	private String openamount;
	private String closingamount;
	private String transactiondate;
	private String status;
	private String Approvedby;
	private String Transactiontype;
	private String TransactionID;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getOpenamount() {
		return openamount;
	}
	public void setOpenamount(String openamount) {
		this.openamount = openamount;
	}
	public String getClosingamount() {
		return closingamount;
	}
	public void setClosingamount(String closingamount) {
		this.closingamount = closingamount;
	}
	public String getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprovedby() {
		return Approvedby;
	}
	public void setApprovedby(String approvedby) {
		Approvedby = approvedby;
	}
	public String getTransactiontype() {
		return Transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		Transactiontype = transactiontype;
	}
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	
	
	
}
