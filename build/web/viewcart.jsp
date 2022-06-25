<%-- 
    Document   : shopping
    Created on : Jun 17, 2022, 8:01:13 AM
    Author     : tranq
--%>

<%@page import="an.cart.CartObject"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping online</title>
    </head>
    <body>
        <h1>Online Shopping</h1>

        <%
            //1. cust goes to his/ her cart place
            if (session != null) {
                //2. cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");

                if (cart != null) {
                    Map<String, Integer> items = cart.getitems();
                    if (items != null) {

        %>
        <h1>Your cart include</h1>
        
        <form action="MainServlet">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>
                <form action="MainServlet">
                    <%                    
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                </form>

                <tr>
                    <td>
                        <%= ++count%>.
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkItems" 
                               value="<%= key%>" />
                    </td>
                </tr>
                <%
                    }//end for
                %>
                <tr>
                    <td colspan="3">
                        <a href="shopping.html">Add more items to your cart</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove Selectd Items" name="btAction"/>
                    </td>
                </tr>

                </tbody>
            </table>
                <br/>
                <input type="submit" value="Check Out" name="btAction"/>
                
        </form>
        <%
                        return;
                    }//end items
                }//end cart has existed
            }//end session(end cart place has existed)
%>

        <h2>No cart is existed</h2>
    </body>
</html>
