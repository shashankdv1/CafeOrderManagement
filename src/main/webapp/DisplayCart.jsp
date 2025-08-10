<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Cafe.AddItemsToCart" %>
<%@ page import="java.util.ArrayList" %>
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
            // Retrieve cart data from the request
            Object obj = request.getAttribute("Cart_data");

            if (obj instanceof ArrayList<?>) {
            	@SuppressWarnings("unchecked")
                ArrayList<AddItemsToCart> cartData = (ArrayList<AddItemsToCart>) obj;

                // Check if the cart is empty
                if (cartData.isEmpty()) {
        %>
        <% 
                } else {
                    // Iterate over cart items
                    for (AddItemsToCart item : cartData) {
        %>
                        <div class="cart-items"> 
                        
                            <div class="item-description">
                            	<p>Item No:<%= item.getId() %><p>
                                <p>Item Name:<strong><%= item.getName() %></strong></p>
                                <p>Price: &#8377; <%= item.getPrice()%></p>
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
