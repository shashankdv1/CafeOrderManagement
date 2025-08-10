
let clickcount1=0;
<<<<<<< HEAD

=======
let clickcount2=0;
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
document.addEventListener("DOMContentLoaded",function()
{
	
	document.getElementById("change-password").addEventListener("click",function(){
		if(clickcount1%2!=0)
			{
	document.getElementsByClassName("form-section1")[0].style.display='block';
	clickcount1+=1;
		}
		else{
			document.getElementsByClassName("form-section1")[0].style.display='none';
			clickcount1+=1;
			}
	});
});

<<<<<<< HEAD
document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.getElementById('changeUsername');
    const formSection = document.getElementsByClassName("formsection2")[0];

    toggleButton.addEventListener("click", () => {
        if (formSection.style.display === 'block') {
            formSection.style.display = 'none';
        } else {
            formSection.style.display = 'block';
        }
    });
});

=======
document.addEventListener("DOMContentLoaded",function()
{
	document.getElementById('change-username').addEventListener("click",function(){
		if(clickcount2%2!=0)
			{
		document.getElementsByClassName("form-section2")[0].style.display='block';
		clickcount2+=1;
		}
		else{
			document.getElementsByClassName("form-section2")[0].style.display='none';
			clickcount2+=1;
		}
	});
});
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
document.addEventListener("DOMContentLoaded",function()
{
	document.getElementById("submitPassword").addEventListener("click",function(event){
		event.preventDefault();
		let x = document.getElementById("newpassword").value;
		let y = document.getElementById("Retype").value;
		if(x===y)
			{
				document.getElementById("Changepass").submit();
				
			}
			else{
				alert("The Password you entered do not match");
			}
	});
});