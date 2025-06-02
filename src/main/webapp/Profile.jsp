<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile - Cafe Order Management</title>
    <link href="Profile.css" rel="Stylesheet"></link>
</head>
<body>
<% 
%> 

<h1>Welcome to Your Profile</h1>
<p>Your email: <strong></strong></p> 
 <button type="submit" id="change-Password">Change Password</button>
 <button type="submit" id="change-username">Change Username</button>
<div class="form-section1">
    <form id="Changepass"  action="changePassword" method="post">
        <label for="contact">Enter your previous password</label>
        <input type="text" name="prevpass" id="contact" required><br>

        <label for="newpassword">Please enter your new password:</label>
        <input type="password" name="newpassword" id="newpassword" required><br>

        <label for="Retype">Please Retype your password:</label>
        <input type="password"  id="Retype" required><br>

        <button type="submit" id="submitPassword">Submit</button>
    </form>
</div>

<div class="form-section2">
   
    <form action="changeUsername" method="post">
        <label for="name">Please enter your new username:</label>
        <input type="text" name="name" id="name" required><br>

        <label for="RetypePassword">Please enter your password:</label>
        <input type="password" name="Password" id="Password" required><br>

        <button type="submit" id="submitUsername">Submit</button>
    </form>
</div>
<script src="profile.js"></script>
</body>
</html>