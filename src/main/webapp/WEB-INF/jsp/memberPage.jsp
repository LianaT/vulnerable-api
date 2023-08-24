<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Membership Room Reservations</title>
</head>
<body>
<a href="/list">Home</a>
<div>
<h1>Welcome <c:out value="${member.username}" /></h1>  <a href="/updateMembershipDetail/${id}">Edit member</a>
</div>

<div>
    <table >
        <tr><td><h2>Bookings</h2></td></tr>
        <tr>
            <th>Reservation ID</th>
            <td></td>
            <th>Name</th>
            <td></td>
            <th>Card</th>
            <td></td>
            <th>Status</th>
            <td></td>
            <td></td>
            <th>Dates</th>
            <td></td>
            <td></td>
            <td>Action</td>
        </tr>
        <c:forEach var="rev" items="${rev_list}">
            <tr>
                <td><c:out value="${rev.id}" /></td>
                <td></td>
                <td><c:out value="${rev.first_name}" /></td>
                <td></td>
                <td><c:out value="${rev.card_number}" /></td>
                <td></td>
                <td><c:out value="${rev.status}" /></td>
                <td>    </td>
                <td></td>
                <td>
                    <c:out value="${rev.start}" />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--<a href="/delete/"  />Delete</a>-->
                </td>
                <td>
                    <c:out value="${rev.end}" />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--<a href="/delete/"  />Delete</a>-->
                </td>
                <td></td>
                <td>
                    <a href="/memberPage/cancel/${rev.id}">Cancel</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <form action="/memberPage/${id}" method="post">
        <table>
            <tr><td><h2>Search for available rooms</h2></td></tr>
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
    <table >
        <tr><td><h5>10% Discount applied to all rooms below</h5></td></tr>
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

                <td><c:out value="${room.price * 0.9}" /></td>
                <td>
                    <a href="/memberBooking/${room.id}/${id}/${start}/${end}"/>Book</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--<a href="/delete/"  />Delete</a>-->
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <a href="/memberPage/removeAccount/${id}"/>Remove Member</a>
</div>

</body>
</html>