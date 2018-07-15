<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>商品资料维护</title>
</head>
<body>

    <%@ include file="head.jspf" %>

    <form method="post" action="FrmPartInfo">
        <label id="searchText">条件</label>
        <input id="searchText" name="searchText" value="${param.searchText}" />
        <button>查询</button>
    </form>

    <div><a href="FrmPartInfo.append">增加</a></div>

    <table>
        <tr>
            <th>公司别</th>
            <th>物料编码</th>
            <th>物料名称</th>
            <th>单位</th>
        </tr>
        <c:forEach items="${dataSet.records}" var="record">
        <tr>
            <td>${record.items.corpNo_}</td>
            <td>${record.items.code_}</td>
            <td>${record.items.name_}</td>
            <td>${record.items.unit_}</td>
            <td><a href="FrmPartInfo.modify?uid=${record.items.UID_}">修改</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>