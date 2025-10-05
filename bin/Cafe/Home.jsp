<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Blob, java.util.ArrayList, Cafe.items, Cafe.searchOperation" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cafe Order Management</title>
    <link href="Home.css" rel="stylesheet">
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String username = (String) session.getAttribute("username");
    String mail = (String) session.getAttribute("email");
%>

<!-- Header / Navbar -->
<div class="main">
    <img id="logo" src="./Images/Cafe logo.png">
    <h4 style="margin-top: 0;">Cafe</h4>
    <button id="menu" style="margin-top: 0;">Cafe menu</button>

    <div class="menu-block">
        <a id="searchlink" href="Search?searchname=Coffee">Coffee</a>
        <a id="searchlink" href="Search?searchname=Tea">Tea</a>
        <a id="searchlink" href="Search?searchname=Salads">Salads</a>
        <a id="searchlink" href="Search?searchname=Brownies">Brownies</a>
        <a id="searchlink" href="Search?searchname=Pastries">Pastries</a>
    </div>

    <form action="SearchResult" method="post" accept-charset="UTF-8">
        <input type="text" name="searchName" placeholder="Please enter search" required>
        <button type="submit">Search</button>
    </form>

    <button id="profile">
        <img id="user_image" src="user-regular.svg">
    </button>

    <div class="profile-container">
        <p>Welcome <%= username %></p>
        <a href="Profile.jsp"><button id="your-profile">Edit Your Profile</button></a>
        <a href="DisplayOrder"><button id="your-orders">Your Orders</button></a>
        <a href="DisplayCart"><button id="your-cart">Your Cart</button></a>
        <a href="Login.jsp"><button id="logout">Logout</button></a>
    </div>
</div>

<!-- Search Results -->
<%
Object searchObj = request.getAttribute("search_result");
if (searchObj instanceof ArrayList<?>) {
    ArrayList<searchOperation> searchItems = (ArrayList<searchOperation>) searchObj;
    if (searchItems.isEmpty()) {
%>
        <h3>No search items available at the moment.</h3>
<%
    } else {
        for (searchOperation search : searchItems) {
            Blob imageBlob = search.getItemImage();
            String base64Image = "";
            if (imageBlob != null) {
                try {
                    byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                    base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                } catch (Exception e) {
                    out.print("<p>Error loading image</p>");
                }
            }
%>
<div class="body-container">
    <div class="search-container">
        <div class="searches">
            <% if (!base64Image.isEmpty()) { %>
                <img id="Image" src="data:image/jpeg;base64,<%= base64Image %>" alt="Item Image">
            <% } else { %>
                <p>No image available</p>
            <% } %>
        </div>
        <div class="description">
            <h3>Food name: <%= search.getItemName() %></h3>
            <p>Category: <%= search.getItemType() %></p>
            <p>Price: &#8377; <%= search.getPrice() %></p>
        </div>
    </div>
</div>
<%
        }
    }
}
%>

<!-- Item List -->
<%
Object obj = request.getAttribute("Itemdata");
if (obj instanceof ArrayList<?>) {
    ArrayList<items> itemData = (ArrayList<items>) obj;
    if (itemData.isEmpty()) {
        out.print("<h3>No items available at the moment.</h3>");
    } else {
        for (items s : itemData) {
            Blob imageBlob = s.getImage();
            String base64Image = "";
            if (imageBlob != null) {
                try {
                    byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                    base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                } catch (Exception e) {
                    out.print("<p>Error loading image</p>");
                }
            }
%>
<div class="body-container">
    <div class="Item-container">
        <div class="images">
            <% if (!base64Image.isEmpty()) { %>
                <img id="Image" src="data:image/jpeg;base64,<%= base64Image %>" alt="Item Image">
            <% } else { %>
                <p>No image available</p>
            <% } %>
        </div>
        <div class="description">
            <h3>Food name: <%= s.getName() %></h3>
            <p>Category: <%= s.getItem() %></p>
            <p>Price: &#8377; <%= s.getPrice() %></p>
        </div>

        <% if (mail != null) { %>
        <!-- Buy Now -->
        <button type="button" onclick="showBuyMenu(<%= s.getId() %>)">Buy Now</button>

        <!-- Add to Cart -->
        <form action="AddtoCart" method="post">
            <input type="hidden" name="email" value="<%= mail %>">
            <input type="hidden" name="itemId" value="<%= s.getId() %>">
            <input type="hidden" name="itemName" value="<%= s.getName() %>">
            <input type="hidden" name="itemPrice" value="<%= s.getPrice() %>">
            <button id="cart" type="submit">Add to Cart</button>
        </form>

        <!-- Buy Menu -->
        <div class="BuyMenu" id="buyMenu_<%= s.getId() %>" style="display: none;">
            <button type="button" onclick="closeBuyMenu(<%= s.getId() %>)" style="margin-left: 250px;">Close</button>
            <form action="Order" method="post">
                <input type="hidden" name="name" value="<%= username %>">
                <input type="hidden" name="item_name" value="<%= s.getName() %>">
                <input type="hidden" name="item_price" value="<%= s.getPrice() %>">
                <p>Item Name: <%= s.getName() %></p>
                <p>Item Price: <%= s.getPrice() %></p>
                <label for="qty_<%= s.getId() %>">Quantity:</label>
                <input type="number" name="qty" id="qty_<%= s.getId() %>" placeholder="Enter Quantity" min="1" required>
                <input type="hidden" id="totalprice_<%= s.getId() %>" name="Totalprice">
                <button type="submit" onclick="return calculatePrice(<%= s.getId() %>, <%= s.getPrice() %>)">Buy</button>
            </form>
        </div>
        <% } %>
    </div>
</div>
<%
        }
    }
}
%>

<!-- Footer -->
<div class="footer">
    <h2 style="margin-left: 45%;">
        © <script>document.write(new Date().getFullYear());</script> All Rights Reserved
    </h2>
</div>

<script src="Home.js"></script>
</body>
</html>
