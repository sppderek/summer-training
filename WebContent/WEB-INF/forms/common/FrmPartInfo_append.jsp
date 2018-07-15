<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>物料编码增加</title>
</head>
<body>

    <%@ include file="head.jspf" %>

    <form method="post" action="FrmPartInfo.append">
        <div>
            <label>物料编号</label>
            <input id="code" name="code" value="${param.code}" />
        </div>
        <div>
            <label>物料名称</label>
            <input id="name" name="name" value="${param.name}" />
        </div>
        <div>
            <label>单位</label>
            <input id="unit" name="unit" value="${param.unit}" />
        </div>
        <button name="submit" value="append">保存</button>
    </form>
</body>
</html>