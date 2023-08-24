<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<a href="/list">Home</a>
<h1>Login</h1>
<div>
    <form action="login" method="post">
        <table>
            <caption>
                <h2>
                    Login to Membership
                </h2>
            </caption>
            <tr>
                <th>User ID: </th>
                <td>
                    <input type="text" name="Username" size="45"
                           value="<c:out value='${membership.username}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="password" size="45"
                           value="<c:out value='${membership.password}' />"
                    />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" value="login" />
                </td>
            </tr>
        </table>
    </form>
</div>