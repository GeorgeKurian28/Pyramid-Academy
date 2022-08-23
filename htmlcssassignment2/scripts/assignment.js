function validate(){

    var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    var email_Value = document.getElementById("email").value;
    var phone_value = document.getElementById("phone").value;
    var phone_RGEX = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
    var msg = "";
    var ph_valid = phone_RGEX.test(phone_value);
    var em_valid = email_regex.test(email_Value);
    
    if (!em_valid){
        msg = "The email address is invalid \n";
    }

    if(!ph_valid){
        msg = msg + "The phone number is invalid";
    }
    if(!em_valid | !ph_valid){
        alert(msg);
    }

}
