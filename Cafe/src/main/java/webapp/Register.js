/**
 * 
 */

document.addEventListener("DOMContentLoaded",function(){
	document.getElementById("submit").addEventListener("click",function(){
		let password=document.getElementById("pwd").value;
		let confirmpassword=document.getElementById("cpwd").value;
		let patt = new RegExp("^.*(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]*$");
		if(patt.test(password))
			{
				
			}
		else{
		alert("Password must contain one special Character,one numerical value and one Uppercase Character");	
		}
		if(password.length<8)
			{
				alert("Password must contain 8 Characters");
			}
			if(password!==confirmpassword)
				{
					alert("Passwords do not match");
				}
	});
});
n 
