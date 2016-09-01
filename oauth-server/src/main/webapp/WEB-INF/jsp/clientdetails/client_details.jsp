<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>client_details</title>

    <style>
        .list-group li:hover {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<a href="./">Home</a>

<div class="row">
    <div class="col-md-10">
        <h3>client_details</h3>
    </div>
    <div class="col-md-2">
        <div class="pull-right">
            <a href="register_client" class="btn btn-success btn-sm">注册client</a>
        </div>
    </div>
</div>

<hr/>

<div>
    <ul class="list-group">
        <c:forEach items="${clientDetailsDtoList}" var="cli">
            <li class="list-group-item">
                <div class="pull-right">
                    <c:if test="${not cli.status}">
                        <a href="test_client/${cli.appKey}">test</a>
                        <a href="archive_client/${cli.appKey}" class="text-danger"
                           onclick="return confirm('Are you sure archive \'${cli.appKey}\'?')">status</a>
                    </c:if>
                    <c:if test="${cli.status==1}"><strong class="text-muted">status</strong></c:if>
                </div>
                <h3 class="list-group-item-heading">
                        ${cli.appKey}
                    <small>${cli.authorizedGrantTypes}</small>
                </h3>

                <div class="list-group-item-text text-muted">
                    appKey: <span class="text-danger">${cli.appKey}</span>&nbsp;
                    appSecret: <span class="text-primary">${cli.appSecret}</span>&nbsp;
                    <br/>
                    authorized_grant_types: <span class="text-primary">${cli.authorizedGrantTypes}</span>&nbsp;
                    resource_ids: <span class="text-primary">${cli.resourceIds}</span>&nbsp;
                    <br/>
                    scope: <span class="text-primary">${cli.scope}</span>&nbsp;
                    redirectUri: <span class="text-primary">${cli.redirectUri}</span>&nbsp;
                    <br/>
                    authorities: <span class="text-primary">${cli.authorities}</span>&nbsp;
                    access_token_validity: <span class="text-primary">${cli.accessTokenValidity}</span>&nbsp;
                    refresh_token_validity: <span class="text-primary">${cli.refreshTokenValidity}</span>&nbsp;
                    <br/>
                    create_time: <span class="text-primary">${cli.createTime}</span>&nbsp;
                    status: <strong class="${cli.status?'text-warning':'text-primary'}">${cli.status}</strong>&nbsp;
                    trusted: <span class="text-primary">${cli.trusted}</span>&nbsp;
                    additional_information: <span class="text-primary">${cli.additionalInformation}</span>&nbsp;
                </div>
            </li>
        </c:forEach>

    </ul>
    <p class="help-block">
        每一个item对应<code>oauth_client_details</code>表中的一条数据; 共<strong>${fun:length(clientDetailsDtoList)}</strong>条数据.
        <br/>
        对spring-oauth-server数据库表的详细说明请访问
        <a href="resources/api/db_table_description.html" target="_blank">db_table_description.html</a>
        (或访问项目others目录的db_table_description.html文件)
    </p>
</div>
</body>
</html>