<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" >
    <title>Membership Detail</title>
</head>
<body>
<a href="/list">Home</a>
<h1> Member - <c:out value="${member.username}" /></h1>
<div>
    <form action="${id}" method="post">
        <table>
            <caption>
                <h2>
                    Update Membership Details
                </h2>
            </caption>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${member.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Surname: </th>
                <td>
                    <input type="text" name="surname" size="45"
                           value="<c:out value='${member.surname}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                           value="<c:out value='${member.address}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Phone Number: </th>
                <td>
                    <input type="text" name="phone_number" size="45"
                           value="<c:out value='${member.phone_number}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                           value="<c:out value='${member.email}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="password" size="45"
                           value="<c:out value='${member.password}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Existing Credit Card: </th>

                    <c:forEach var="cards" items="${card_list}">

                            <tr><td></td><td><c:out value="${cards.credit_card}" />    <a href="/updateMembershipDetail/removeCard/${cards.id}"/>[Remove]</td></tr>


                    </c:forEach>

                <th>Add Credit Card:</th>
                <td>
                    <input type="text" name="credit_card" size="45"
                           value="<c:out value='${new_card.credit_card}' />"
                    />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" value="Update" />
                </td>

                </td>
            </tr>
        </table>
    </form>
</div>
<div>

</div>
</body>
</html>