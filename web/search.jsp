<%-- 
    Document   : search
    Created on : Jun 8, 2022, 7:45:28 AM
    Author     : tranq
--%>

<%--
<%@page import="an.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.username}
        </font>
        <h1>Search Username</h1>
        <form action="MainServlet">
            Search Value <input type="text" name="txtSearchValue"
                                value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>

            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.username}
                                </td>
                                <td>
                                    ${dto.password}
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    ${dto.role}
                                </td>
                            </tr>
                        </c:forEach>                            
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty result}">
                <h2>
                    No matched record!!!
                </h2>
            </c:if>

        </c:if>


        <%--        <% 
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            String username = "";
            for(Cookie cookie : cookies){
                String temp = cookie.getName();
                if(!temp.equals("JSESSIONID")){
                    username = temp;
                }
            }
            %>
        <font color="red">
        Welcome, <%= username %>
        
        
        </font>
        
        <br/>
        <form action="MainServlet">
        <input type="submit" value="Logout" name="btAction"/>
        <input type="hidden" name="txtusername" value="<%= username %>" />
        </form>
        
                <%
            }//end if
            %>
            
        <h1>Search Username</h1>
        <form action="MainServlet">
            Search Value <input type="text" name="txtSearchValue"
                                value="<%= request.getParameter("txtSearchValue") %>"/><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        
        
        
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>

                <tr>
                    <%
                        int count = 0;
                        for (RegistrationDTO dto : result) {
                            String urlRewriting = "MainServlet"
                                                + "?btAction=Delete"
                                                + "&username=" + dto.getUsername()
                                                + "&lastSearchValue=" + searchValue;
                    %>
            <form action="MainServlet">
                
                <tr>
                    <td>
                        <%= ++count%>.
                    </td>
                    <td>
                        <%= dto.getUsername() %>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword() %>" />
                    </td>
                    <td>
                        <%= dto.getLastname() %>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <% 
                               if(dto.isRole()){
                                   %>
                                   checked="checked"
                                   <%
                               }//end user is an admin
                               %>
                               />
                    </td>
                    
                    <td>
                        <a href="<%= urlRewriting %>">Delete</a> 
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue %>" />
                        
                    </td>
                </tr>
            
            </form>
                

                <%
                    }//end traverse search result
                %>
                </tr>
            </tbody>
        </table>

        <%
        } else { //no result
        %>
        
        <h2>
            No result matched
        </h2>
        
        <%
                }
            }//end search Value has proceeded
%>
        --%>  
    </body>
</html>
