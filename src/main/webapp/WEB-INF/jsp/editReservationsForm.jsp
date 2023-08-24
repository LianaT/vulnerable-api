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
<h1>Confirm Room Reservation</h1>
<h2>
    <a href="/findReservation">Back to Reservation Search</a>
</h2>


<div>
    <table>
        <tr>
            <th>Reservation ID: </th>
            <td><c:out value="${reservation.id}" /></td>
        </tr>
        <tr>
            <th>Name: </th>
            <td><c:out value="${reservation.first_name} ${reservation.surname}" /></td>
        </tr>
        <tr>
            <th>Status: </th>
            <td><c:out value="${reservation.status}" /></td>
        </tr>
        <tr>
            <th>Dates: </th>
            <td><c:out value="${start} to ${end}" /></td>
        </tr>
    </table>
</div>
</body>
    <form action="/reservations/delete/${reservation.id}" method="post">
        <table>
            <caption>Cancel Reservation</caption>
            <tr>
                <td colspan="2">To cancel your booking, input the credit card number used to make the booking, and click Cancel Reservation.</td>
            </tr>

            <tr>
                <th>Reservation ID: </th>
                <td><c:out value="${reservation.id}" /></td>
            </tr>

            <tr>
                <th>Credit Card Number: </th>
                <td><input type="Integer" name="credit_card_number"/></td>
            </tr>
            <tr>
                <td colspan="=2">
                    <input type="submit" value="Cancel" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:out value="${errorMessage}" />
                </td>
            </tr>

        </table>
    </form>
</div>