<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin-Add Items</title>
</head>
<body>
<h1 style="margin-left:450px;">Admin-Add Items</h1>
<form style="margin-left:450px;"action="AddItems" method="post" enctype="multipart/form-data">
<label for="item">Item Name:</label>
<input style="margin-top:10px;" type="text" name="item" placeholder="Please Enter the Item name"></input><br>
<label for="list">Item types:</label><br>
<input  style="margin-top:10px;" type="radio" id="coffee" name="list"></input>
<label for="coffee">Coffee</label><br>
<input style="margin-top:10px;" type="radio" id="cookie" name="list"></input>
<label for="cookie">Cookie</label><br>
<label for="upload_image">Image: </label>
<input style="margin-top:10px;" type="file" name="upload_image"></input><br>
<label for="description">Item Description</label>
<input type="text" style="margin-top:10px;"  name="description"></input><br>
<label for="price">Price: </label>
<input type="text" style="margin-top:10px;" name="price"></input><br>
<button type="submit">Submit</button>
</form>
</body>
</html>
