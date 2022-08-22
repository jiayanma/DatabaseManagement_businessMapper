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
    <title>Search Business</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Search Business</h1>
    </div>

    <div class="form-outline">
        <form action="searchbusiness">
            <div class="input-group mb-3">
                <input id="search-input" type="search" class="form-control" name="zipcode"
                       value="${zipcode}"
                       placeholder="Enter ZipCode"/>
                <button id="search-button" type="search submit" class="btn btn-primary">Search
                </button>
            </div>
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ZIPCODE</th>
            <th>NAME</th>
            <th>ADDRESS</th>
            <th>RATING</th>
            <th>IS OPEN</th>
            <th style="width:100px;">FAVORITE</th>
            <th style="width:100px;">REVIEW</th>
        </tr>
        </thead>
        <c:forEach items="${yelpBusinessList}" var="f">
            <tbody>
            <tr>
                <td>
                    <a href="search_region?zipcode=<c:out value="${f.getZipCode()}"/>">
                        <c:out value="${f.getZipCode()}"/>
                    </a>
                </td>
                <td><c:out value="${f.getName()}"/></td>
                <td><c:out value="${f.getAddress()}"/></td>
                <td><c:out value="${f.getRating()}"/></td>
                <td><c:out value="${f.getOpen()}"/></td>
                <td>
                    <a href="addfavoritebusiness?businessid=<c:out value="${f.getBusinessId()}"/>"
                       class="btn btn-primary form-control h-75"> Add
                    </a>
                </td>
                <td>
                    <a href="reviewcreate?businessid=<c:out value="${f.getBusinessId()}"/>"
                       class="btn btn-primary form-control h-75" > Add
                    </a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>

    <div class="row justify-content-center">
        <div>
            <nav>
                <ul class="pagination">
                    <li class="page-item ${page==1?'disabled':''}">
                        <a class="page-link"
                           href="<c:url value="/searchbusiness?zipcode=${zipcode}&page=${page-1>1?page-1:1}"/>">
                            &laquo;
                        </a>
                    </li>
                    <c:if test="${page!=1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/searchbusiness?zipcode=${zipcode}&page=1"/>">1</a>
                        </li>
                    </c:if>
                    <c:if test="${page>2}">
                        <li class="page-item disabled">
                            <a class="page-link">...</a>
                        </li>
                    </c:if>
                    <li class="page-item active">
                        <a class="page-link"
                           href="<c:url value="/searchbusiness?zipcode=${zipcode}&page=${page}"/>">
                            ${page}
                        </a>
                    </li>
                    <c:if test="${totalPages-page>1}">
                        <li class="page-item disabled">
                            <a class="page-link">...</a>
                        </li>
                    </c:if>
                    <c:if test="${page!=totalPages}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/searchbusiness?zipcode=${zipcode}&page=${totalPages}"/>">
                                    ${totalPages}
                            </a>
                        </li>
                    </c:if>
                    <li class="page-item ${page==totalPages?'disabled':''}">
                        <a class="page-link"
                           href="<c:url value="/searchbusiness?zipcode=${zipcode}&page=${page+1<totalPages?page+1:totalPages}"/>">&raquo;</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</div>
</body>
</html>