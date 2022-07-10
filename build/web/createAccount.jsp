<%-- 
    Document   : createAccount
    Created on : Jul 7, 2022, 6:17:55 PM
    Author     : AN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="MainServlet" method="POST">
            <c:set var="errors" value="${requestScope.INSERT_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 20 chars) <br/>
            <c:if test="${not empty errors.usernameErr}">
                <font color="red">
                ${errors.usernameErr}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 20 chars) <br/>
            <c:if test="${not empty errors.passwordErr}">
                <font color="red">
                ${errors.passwordErr}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br/>
            </c:if>
            Full name <input type="text" name="txtFullname" value="${param.txtFullname}" /> (2 - 50 chars) <br/>   
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
