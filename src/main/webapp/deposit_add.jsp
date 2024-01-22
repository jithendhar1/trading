<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<form action="./AddDepositSrv" method="post">
<div id="addvechicle" class="modal custom-modal fade" role="dialog">
    <div class="col-md-6">
        <div class="form-group">
            <label for="AccountID">Account ID <span class="text-danger">*</span></label>
            <input id="AccountID" name="AccountID" class="form-control" type="text">    
        </div>
    </div>

    <div class="col-sm-6">
        <div class="form-group">
            <label for="DepositDate">DepositDate <span class="text-danger">*</span></label>
            <input id="DepositDate" name="DepositDate" class="form-control" type="text">
        </div>
    </div>

    <div class="col-md-6">
        <div class="form-group">
            <label class="col-form-label">Amount <span class="text-danger">*</span></label>
            <input name="Amount" required class="form-control" type="text">
        </div>
    </div>

    <div class="col-md-6">
        <div class="form-group">
            <label class="col-form-label">userID <span class="text-danger">*</span></label>
            <input name="userID" required class="form-control" type="text">
        </div>
    </div>
</div>

 <div class="submit-section">
    <button type="submit" class="btn btn-primary submit-btn">Submit</button>
  </div>
</form>
