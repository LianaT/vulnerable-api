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
<h1>Register Membership</h1>
<div>
    <form action="register" method="post">
        <table>
            <caption>
                <h2>
                    Register Membership
                </h2>
            </caption>
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45"
                           value="<c:out value='${membership.username}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${membership.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Surname: </th>
                <td>
                    <input type="text" name="surname" size="45"
                           value="<c:out value='${membership.surname}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                           value="<c:out value='${membership.address}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Phone Number: </th>
                <td>
                    <input type="text" name="phone_number" size="45"
                           value="<c:out value='${membership.phone_number}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                           value="<c:out value='${membership.email}' />"
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
                <th>Credit Card: </th>
                <td>
                    <input type="text" name="credit_card" size="45"
                           value="<c:out value='${card.credit_card}' />"
                    />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                <td colspan="2" >
                    <input type="submit" value="Save" />
                </td>

                <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>