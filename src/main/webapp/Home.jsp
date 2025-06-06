<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="Cafe.items" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cafe Order Management</title>
    <link href="Home.css" rel="stylesheet">
</head>
<body>
 <% String username = (String) session.getAttribute("username"); %> 
<div class="main">
   <img id="logo" src="./Images/Cafe logo.png"><h4 style='margin-top:0;'>Cafe</h4>
    <button style='margin-top:0' id="menu">Cafe menu</button>
    <div class="menu-block">
        <a id="searchlink" href="http://localhost:8080/CafeOrderManageme/Search?searchname=Coffee">Coffee</a>
        <a id="searchlink" href="http://localhost:8080/CafeOrderManageme/Search?searchname=Tea">Tea</a>
        <a id="searchlink" href="http://localhost:8080/CafeOrderManageme/Search?searchname=Salads">Salads</a>
        <a id="searchlink" href="http://localhost:8080/CafeOrderManageme/Search?searchname=Brownies">Brownies</a>
        <a id="searchlink" href="http://localhost:8080/CafeOrderManageme/Search?searchname=Pasteries">Pastries</a>
    </div>
    <input type="text" name="searchname"  id="search-block" placeholder="Please enter search">
    <button id="search" type="submit">
        <img id="search-img" src="magnifying-glass-solid.svg">
        <a id="searchlink" href="http://localhost:8080/CafeOrderManageme/Search?searchname=" style="display:none;"></a>
    </button>
    <button id="profile">
        <img id="user_image" src="user-regular.svg">
    </button>
    <div class="profile-container">
   
        <p>Welcome <%=username %>
        </p>
        <a href="Profile.jsp">
            <button id="your-profile">Edit Your profile</button>
        </a>
        <a href="http://localhost:8080/CafeOrderManageme/DisplayOrder">
        <button id="your-orders">Your Orders</button>
        </a>
        <a href="DisplayCart">
            <button id="your-cart">Your Cart</button>
        </a>
        <a href="http://localhost:8080/CafeOrderManageme/Login.jsp">
            <button id="logout">Logout</button>
        </a>
    </div>
</div>


    <% 
    Object obj = request.getAttribute("Itemdata");
    if (obj instanceof ArrayList<?>) {
        ArrayList<items> Itemdata = (ArrayList<items>) obj;
        if (Itemdata.isEmpty()) {
            out.print("<h3>No items available at the moment</h3>");
        } else {
            for (items s : Itemdata) {
    %>
    <div class="body-container">
    <div class="Item-container">
    <div class="images">
        <% 
        Blob imageBlob = s.getImage();
        if (imageBlob != null) {
            try {
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                out.print("<img id='Image' src='data:image/jpeg;base64," + base64Image + "' alt='Item Image' />");
            } catch (Exception e) {
                out.print("<p>Error converting image</p>");
            }
        } else {
            out.print("<p>No image available</p>");
        }
        %>
    </div>

    <div class="description">
        <br><h3>Food name: <%= s.getName() %></h3>
        <p>Category: <%= s.getItem() %></p>
        <p>Price: &#8377; <%= s.getPrice() %></p>
        </div>
        <!-- Buy Now Form -->
   <form action="" method="post">
    <input type="hidden" name="itemId" value="<%= s.getId() %>" />
    <input type="hidden" name="itemName" value="<%= s.getName() %>" />
    <input type="hidden" name="itemPrice" value="<%= s.getPrice() %>" />
    <button type="button" onclick="showBuyMenu(<%= s.getId() %>)">Buy Now</button>
</form>

<!-- Add to Cart Form -->

    <% 
    String mail = (String) request.getAttribute("username");
    if (mail != null) {
    %>
    <form action="AddtoCart" enctype="multipart/form-data" method="post">
    <input type="hidden" name="email" value="<%= mail %>"/>
    <input type="hidden" name="itemId" value="<%= s.getId() %>" />
    <input type="hidden" name="itemName" value="<%= s.getName() %>" />
    <input type="hidden" name="itemPrice" value="<%= s.getPrice() %>" />
    <button id="cart" type="submit">Add to Cart</button>
    </form>
    <% } %>

<!-- Buy Menu for each item -->
<div class="BuyMenu" id="buyMenu_<%= s.getId() %>" style="display: none;">
    <button type="button" onclick="closeBuyMenu(<%= s.getId() %>)" style="margin-left: 250px;">Close</button>
    <form action="Order" method="post">
    	<input name="name" type="hidden" value="<%=username %>"></input>
    	<input name="item_name" type="hidden" value="<%=s.getName() %>"></input>
     <input name="item_price" type="hidden" value="<%=s.getPrice() %>"></input>
    <p style="margin-right: 80px;">Item Name: <%= s.getName() %></p>
    <p style="margin-right: 100px;">Item Price: <%= s.getPrice() %></p>
    <label for="qty">Net Quantity: </label>
    <input  placeholder="Please Enter Quantity" style="width: 100px;" type="number" name="qty" id="qty_<%= s.getId() %>" min="0" /><br>
     <input type="hidden" id="totalprice_<%= s.getId() %>" name="Totalprice"/>
    <button style="margin-top: 50px;" type="submit" onclick="calculatePrice(<%=s.getId() %>,<%=s.getPrice()%>)">Buy</button>
     </form>
</div>
 </div>
    <% 
            }
        } 
    } 
    %>
</div>
<div class="footer">
<h2 style="margin-left: 45%;">©  <script>
            var CurrentYear = new Date().getFullYear()
            document.write(CurrentYear)
        </script> All Rights Reserved</h2>
</div>
<script src="Home.js"></script>
</body>
</html>
