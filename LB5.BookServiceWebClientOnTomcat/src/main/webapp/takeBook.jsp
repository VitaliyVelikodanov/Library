<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Select Book</title>
    <link href="css/default.css" rel="stylesheet">
</head>
<body>
<jsp:include page="jspf/head.jspf"></jsp:include>
<div>
    <h2>Select a Book</h2>
    <form action="takeBook" method="post">
        <label for="bookId">Select a Book:</label>
        <select name="bookId" id="bookId" required>
            <c:forEach items="${bookIds}" var="bookId">
                <option value="${bookId}">${bookId}</option>
            </c:forEach>
        </select>
        <br/>
        <input type="submit" value="Take Book" />
    </form>
</div>
</body>
</html>
