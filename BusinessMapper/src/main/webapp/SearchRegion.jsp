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
    <title>Search Region</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Search Region</h1>
    </div>

    <div class="form-outline">
        <form action="search_region">
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
            <th>ZipCode</th>
            <th>Value2020</th>
            <th>Rental2020</th>
            <th>Value2021</th>
            <th>Rental2021</th>
            <th>Value2022</th>
            <th>Rental2022</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td> ${regionHouseValue.getZipCode()} </td>
                <td> ${regionHouseValue.getValue2020()} </td>
                <td> ${regionHouseRental.getRental2020()} </td>
                <td> ${regionHouseValue.getValue2021()} </td>
                <td> ${regionHouseRental.getRental2021()} </td>
                <td> ${regionHouseValue.getValue2022()} </td>
                <td> ${regionHouseRental.getRental2022()} </td>
            </tr>
        </tbody>
    </table>


    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</div>
</body>
</html>