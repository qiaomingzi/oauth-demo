<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>User Overview</title>
</head>
<body>
<a href="../">Home</a>

<h2>User Overview</h2>

<div class="pull-right">
    <a href="form/plus" class="btn btn-success btn-sm">Add User</a>
</div>
<form action="" class="form-inline">
    <div class="form-group">
        <input type="text" class="form-control" placeholder="Type account" name="account" value=""/>
    </div>
    <button type="submit" class="btn btn-default">Search</button>
    &nbsp;<span class="text-info">Total: ${overviewDto.size()}</span>
</form>
<br/>
<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>account</th>
        <th>roles</th>
        <th>tel</th>
        <th>Email</th>
        <th>CreateTime</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${overviewDto}" var="user">
        <tr>
            <td>${user.account}</td>
            <td>${user.roleIds}</td>
            <td>${user.tel}</td>
            <td>${user.email}</td>
            <td>${user.createTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>