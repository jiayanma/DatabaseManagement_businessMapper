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
        <h1>Create New Account</h1>
    </div>
    <form action="usercreate" method="post">
        <div class="mb-3">
            <label for="UserName" class="form-label">UserName</label>
            <input id="UserName" class="form-control" name="UserName" value="">
        </div>
        <div class="mb-3">
            <label for="Password" class="form-label">Password</label>
            <input id="Password" class="form-control" name="Password" value="">
        </div>
        <div class="mb-3">
            <label for="FirstName" class="form-label">FirstName</label>
            <input id="FirstName" class="form-control" name="FirstName" value="">
        </div>
        <div class="mb-3">
            <label for="LastName" class="form-label">LastName</label>
            <input id="LastName" class="form-control" name="LastName" value="">
        </div>
        <div class="mb-3">
            <label for=Email class="form-label">Email</label>
            <input id="Email" class="form-control" name="Email" value="">
        </div>
        <div class="mb-3">
            <label for=PhoneNumber class="form-label">PhoneNumber</label>
            <input id="PhoneNumber" class="form-control" name="PhoneNumber" value="">
        </div>
        <div>
            <button type="submit" class="btn btn-primary form-control">Create</button>
        </div>
    </form>
</div>
</body>
</html>