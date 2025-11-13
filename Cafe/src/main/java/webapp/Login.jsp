<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="Login.css" rel="stylesheet"></link>
<title>Login-CafeOrderManagement</title>
</head>
<body>
<div class="Main">
<h1>Login</h1>
<div class="Container">
<form name="Login-form" action="Login" method="post">
<label for="mail">Email: </label><input id="mail" type="email" name="mail" placeholder="Please Enter your Email address" required></input><br>
<label  for="password">Password: </label><input id="pwd" name="password" type="password" placeholder="Please Enter your Password" required></input><br>
New User?&nbsp;<a href="Register.jsp">Register</a>
<button id="submit" type="submit">Submit</button>
</form>
</div>
</div>
<script src="Login.js"></script>
</body>
</html>
