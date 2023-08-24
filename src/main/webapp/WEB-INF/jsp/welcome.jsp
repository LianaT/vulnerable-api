<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Marriott Reservations</title>
</head>
<body>
<h2>
    <a href="/list">Search for a Room</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/findReservation">Retrieve my Reservation</a>

</h2>
Member:
<a href="/login">Login</a>

<a href="/newMember">Register</a>

<h2>Search For Available Rooms</h2>

<div>
    <form action="search" method="post">
        <table>
            <tr>
                <th>Number of Guests: </th>
                <td>
                    <input type="Number" name="occupants" size="1" />
                </td>
            </tr>
            <tr>
                <th>Check In Date: </th>
                <td>
                    <input type="Date" name="start" />
                </td>
            </tr>

            <tr>
                <th>Check Out Date: </th>
                <td>
                    <input type="Date" name="end" />
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <input type="submit" value="Search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div>
    <b><c:out value="${informationMessage}" /></b>
    <br><br>
</div>

<div>
    <table >
        <tr>
            <th>Room Number</th>
            <th>Occupancy</th>

            <th>Price per night</th>
            <th></th>
        </tr>
        <c:forEach var="room" items="${listRooms}">
            <tr>
                <td><c:out value="${room.number}" /></td>
                <td><c:out value="${room.occupancy}" /></td>

                <td><c:out value="${room.price}" /></td>
                <td>
                    <a href="/bookRoom/${room.id}/${start}/${end}"/>Book</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--<a href="/delete/"  />Delete</a>-->
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>