<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register-CafeOrderManagement</title>
<link href="Register.css" rel="stylesheet"></link>
</head>
<body>
<div class="Main">
<h1>Register</h1>
<div class="Container">
<form name="Login-form" action="Registrations" method="post">
<label for="mail">Email: </label><input id="mail" type="email" name="mail" placeholder="Please Enter your Email address" required></input><br>
<label  for="Phone">Contact: </label><input id="number" name="Phone" type="text" placeholder="Please Enter your Phone number" required></input><br>
<label  for="password">Password: </label><input id="pwd" name="password" type="password" placeholder="Please Enter your Password" required></input><br>
<label  for="password">Confirm Password: </label><input id="cpwd" name="password" type="password" placeholder="Please Re-Enter your Password" required></input><br>
Already have an account?&nbsp;<a href="Login.jsp">Login</a><br>
<button id="submit" type="submit">Submit</button>
</form>
</div>
</div>
<script src="Register.js" defer></script> 
</body>
</html>