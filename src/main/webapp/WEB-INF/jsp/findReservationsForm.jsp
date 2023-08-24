<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Marriott Reservations</title>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
</head>
<body>
<a href="/list">Home</a>
<h1>Confirm Room Reservation</h1>
<h2>
    <a href="/list">Back to Room Search</a>
</h2>


<div>
    <form action="/reservations/search" method="post">
        <table>
            <caption>
                <h2>
                    Find My Reservation
                </h2>
            </caption>

            <tr>
                <th>Reservation ID: </th>
                <td>
                    <input type="text" name="reservation_id" size="45" id="reservation_id" />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>