<%-- 
    Document   : shopping
    Created on : Jun 22, 2022, 6:53:29 AM
    Author     : tranq
--%>

<%@page import="an.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="an.product.ProductDAO"%>
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
            ProductDAO dao = new ProductDAO();
            dao.getItemList();
            List<ProductDTO> list = (List<ProductDTO>) dao.getItem();
            if (list != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>
                        No.
                    </th>
                    <th>
                        Product
                    </th>
                    <th>
                        Price
                    </th>
                    <th>
                        Description
                    </th>
                    <th>

                    </th>
                </tr>
            </thead>

            <tbody>
                <%
                    int count = 0;
                    for (ProductDTO dto : list) {
                %>
                <tr>
                    <td>
                        <%= ++count%>.
                    </td>
                    <td>
                        <%= dto.getName()%>
                    </td>
                    <td>
                        <%= (double) Math.round(dto.getPrice())%>$
                    </td>
                    <td>
                        <%= dto.getDescription()%>
                    </td>
                    <td>
                        <form action="MainServlet">
                        <input type="submit" name="btAction" value="Add to cart"/>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>


        </table>
        <%
            }//end if list exist
        %>
    </tbody>
    <a href="viewcart.jsp">
        View your cart
    </a>
</body>
</html>
