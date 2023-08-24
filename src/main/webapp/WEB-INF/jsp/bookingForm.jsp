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
    <a href="/list">Back to Room Search</a>
</h2>
<div>
    <p><b><c:out value='${reservationID}' /></b></p>
</div>

<div>
    <form action="/bookRoom/save" method="post">
        <table>
            <caption>
                <h2>
                    Confirm Reservation
                </h2>
            </caption>
            <input type="hidden"  name="room_id" value="<c:out value='${room.id}' />"  />
            <input type="hidden"  name="starwood_user_id" />
            <input type="hidden"  name="status" value="Active"/>
            <input type="hidden"  name="start" value="<c:out value="${start}" />"/>
            <input type="hidden"  name="end" value="<c:out value="${end}" />"/>
            <tr>
                <th>Room ID: </th>
                <td>
                    <c:out value="${room.id}" />
                </td>
            </tr>
            <tr>
                <th>Price per night: </th>
                <td>
                    <c:out value="${room.price}" />
                </td>
            </tr>
            <tr>
                <th>Maximum Occupancy: </th>
                <td>
                    <c:out value="${room.occupancy}" />
                </td>
            </tr>
            <tr>
                <th>Check In Date: </th>
                <td>
                    <c:out value="${start}" />
                </td>
            </tr>

            <tr>
                <th>Check Out Date: </th>
                <td>
                    <c:out value="${end}" />
                </td>
            </tr>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="first_name" size="45" id="first_name" value="<c:out value="${reservation.first_name}" />" />
                </td>
            </tr>
            <tr>
                <th>Surname: </th>
                <td>
                    <input type="text" name="surname" size="45" id="surname" value="<c:out value="${reservation.surname}"/>"/>
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45" id="address" value="<c:out value="${reservation.address}"/>"/>
                </td>
            </tr>
            <tr>
                <th>Phone Number: </th>
                <td>
                    <input type="text" name="phone_number" size="45" id="phone_number" value="<c:out value="${reservation.phone_number}"/>"/>
                </td>
            </tr>
            <tr>
                <th>Email Address: </th>
                <td>
                    <input type="text" name="email_address" size="45" id="email_address" value="<c:out value="${reservation.email_address}"/>"/>
                </td>
            </tr>
            <tr>
                <th>Credit Card Number: </th>
                <td>
                    <input type="text" name="card_number" id="card_number" size="45" value="<c:out value="${reservation.card_number}"/>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                   <b><c:out value="${error_message}" /></b>
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