<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Create a User</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>${messages.title}</h1>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>FavoriteId</th>
            <th>UserName</th>
            <th>BusinessId</th>
            <th>Delete</th>
        </tr>
        </thead>
        <c:forEach items="${favoriteBusinessList}" var="f">
            <tbody>
            <tr>
                <td><c:out value="${f.getFavoriteId()}"/></td>
                <td><c:out value="${f.getUserName()}"/></td>
                <td><c:out value="${f.getBusinessId()}"/></td>
                <td>
                    <a href="deletefavoritebusiness?favoriteid=<c:out value="${f.getFavoriteId()}"/>&userName=<c:out value="${f.getUserName()}"/>">Delete</a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>

    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</div>
</body>
</html>