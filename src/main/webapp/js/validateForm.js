

function notNull(text, error) {
  var result = document.getElementById(error);
  result.style.display = "block";
  if(text < 0) {
    result.innerHTML = "Invalid!";
    return false;
  }
  else if(text.trim() == "") {
    result.innerHTML = "Must be filled out!";
    return false;
  }
  result.style.display = "none";
  return true;
}

function validateName(name, error) {
  var result = document.getElementById(error);
  result.style.display = "block";
  if(name.trim() == "") {
    result.innerHTML = "Must be filled out!";
    return false;
  }
  result.innerHTML = "Must contain only letters!";
  for(var i = 0; i < name.length; i++)
    if(!((name[i] >= 'a' && name[i] <= 'z') || (name[i] >= 'A' && name[i] <= 'Z') || name[i] == ' '))
      return false;
  result.style.display = "none";
  return true;
}


function validateEmail(email, error) {
    var result = document.getElementById(error);
    result.style.display = "block";

    // Regular expression pattern to validate email addresses
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    if (!email.match(emailPattern)) {
        result.innerHTML = "Invalid email address!";
        return false;
    }

    result.style.display = "none";
    return true;
}


function validateContactNumber(contact_number, error) {
  var result = document.getElementById(error);
  console.log(result);
  result.style.display = "block";
  if(contact_number.length != 10) {
    result.innerHTML = "Must contain 10 digits!";
    return false;
  }
  else
    result.style.display = "none";
  return true;
}

function validateAddress(address, error) {
  var result = document.getElementById(error);
  result.style.display = "block";
  if(address.trim().length < 10) {
    result.innerHTML = "Please enter more specific address!";
    return false;
  }
  else
    result.style.display = "none";
  return true;
}

function checkExpirydate(date, error) {
  var result = document.getElementById(error);
  result.style.display = "block";

  // Define a regular expression pattern for "d-m-Y" format
  var datePattern = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/;

  if (!date.match(datePattern)) {
    result.innerHTML = "Please enter a valid date in d-m-Y format!";
  } else {
    // Split the date string into day, month, and year
    var dateParts = date.split('-+  ');
    var day = parseInt(dateParts[0], 10);
    var month = parseInt(dateParts[1], 10) - 1; // Subtract 1 as months are zero-based
    var year = parseInt(dateParts[2], 10);

    // Create a new Date object and compare it with the current date
    var inputDate = new Date(day, month, year);
    var currentDate = new Date();

    if (inputDate < currentDate) {
      result.innerHTML = "Expired Medicine!";
      return false;
    } else {
      result.style.display = "none";
      return true;
    }
  }

  return false;
}






function checkDate(date, error) {
  var result = document.getElementById(error);
  result.style.display = "block";
  if(date == "")
    result.innerHTML = "Mustn't be empty!!";
  else if(new Date(date) > new Date())
    result.innerHTML = "Mustn't be future date!";
  else {
    result.style.display = "none";
    return true;
  }
  return false;
}

 function validatePasswordMatch() {
        var password = document.getElementById("password").value;
        var confirmPass = document.getElementById("confirm_pass").value;
        var errorSpan = document.getElementById("passwordError");

        if (password !== confirmPass) {
            errorSpan.textContent = "Passwords do not match";
        } else {
            errorSpan.textContent = "";
        }
        } 
    
    function checkJoiningDate(date, error) {
  var result = document.getElementById(error);
  result.style.display = "block";

  // Define a regular expression pattern for "YYYY-MM-DD" format
  var datePattern = /^\d{4}-\d{2}-\d{2}$/;

  if (!date.match(datePattern)) {
    result.innerHTML = "Please enter a valid date in YYYY-MM-DD format!";
  } else {
    // Split the date string into year, month, and day
    var dateParts = date.split('-');
    var year = parseInt(dateParts[0], 10);
    var month = parseInt(dateParts[1], 10) - 1; // Subtract 1 as months are zero-based
    var day = parseInt(dateParts[2], 10);

    // Create a new Date object and compare it with the current date
    var inputDate = new Date(year, month, day);
    var currentDate = new Date();
     console.log(inputDate);
    if (inputDate > currentDate) {
      result.innerHTML = "Joining date can't be a future date!";
      return false;
    } else {
      result.style.display = "none";
      return true;
    }
  }

  return false;
}
 