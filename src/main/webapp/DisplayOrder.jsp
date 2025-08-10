<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Cafe.Orderdata" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Orders</title>
    <link href="DisplayOrder.css" rel="stylesheet">  <!-- Add your CSS here -->
</head>
<body>
<h1>Your Orders</h1>
    <div class="main">
        
        <% 
            // Retrieve order data from the request
            Object obj = request.getAttribute("Order_data");

            if (obj instanceof ArrayList<?>) {
            	@SuppressWarnings("unchecked")
                ArrayList<Orderdata> orderData = (ArrayList<Orderdata>) obj;

                // Check if the orders list is empty
                if (orderData.isEmpty()) {
        %>
                    <p>Your orders list is empty.</p>
        <% 
                } else {
<<<<<<< HEAD
                   
                    for (Orderdata order : orderData) {
        %>
                        <div class="order-item">
                            <p><strong>Ordered Item Name:</strong> <%= order.getItemName() %></p>
=======
                    // Loop through each order and display it
                    for (Orderdata order : orderData) {
        %>
                        <div class="order-item">
                            <p><strong>Order Number:</strong> <%= order.getItemId() %></p>
                            <p><strong>Order Name:</strong> <%= order.getOrderName() %></p>
                            <p><strong>Orderer Name:</strong> <%= order.getUsername() %></p>
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b
                            <p><strong>Order Price:</strong>  &#8377; <%= order.getOrderPrice()%></p>
                        </div>
        <% 
                    }
                }
            } else {
        %>
                <p>Your orders list is empty.</p>
        <% 
            }
        %>
    </div>
</body>
</html>
