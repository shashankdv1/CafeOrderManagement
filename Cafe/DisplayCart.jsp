<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Cafe.AddItemsToCart" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link href="DisplayCart.css" rel="stylesheet">
</head>
<body>
    <h1>Your Cart</h1>
    <div class="main">   
        <%
            Object obj = request.getAttribute("Cart_data");
            if (obj != null && obj instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<AddItemsToCart> cartData = (ArrayList<AddItemsToCart>) obj;

                if (cartData.isEmpty()) {
        %>
                    <p>Your cart is empty.</p>
        <%
                } else {
                    for (AddItemsToCart item : cartData) {
        %>
                        <div class="cart-items"> 
                            <div class="item-description">
                                <p>Item No: <%= item.getId() %></p>
                                <p>Item Name: <strong><%= item.getName() %></strong></p>
                                <p>Price: &#8377; <%= item.getPrice() %></p>
                            </div>
                        </div>
        <%
                    }
                }
            } else {
        %>
                <p>Your cart is empty.</p>
        <%
            }
        %>
    </div>
</body>
</html>
