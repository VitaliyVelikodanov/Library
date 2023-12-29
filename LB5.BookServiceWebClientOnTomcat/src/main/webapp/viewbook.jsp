<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book Details</title>
    <link href="css/default.css" rel="stylesheet">
</head>
<body>
<jsp:include page="jspf/head.jspf"></jsp:include>
<c:choose>
    <c:when test="${not empty errorMsg}">
        <h2>${errorMsg}</h2>
    </c:when>
    <c:otherwise>
        <ul>
            <li>Title: ${book.title}</li>
            <li>Body: ${book.body}</li>
            <li>Author Name: ${book.author.name}</li>
            <li>Place of Birth: ${book.author.placeOfBirth}</li>
            <li>Biography: ${book.author.biography}</li>
            <li>Major Works: ${book.author.majorWorks}</li>
            <li>Genre: ${book.genre.genreName}</li>
        </ul>
        <!-- You can add more book-related information here -->
    </c:otherwise>
</c:choose>

<!-- Remove error message and book from session after displaying -->
<% session.removeAttribute("errorMsg"); %>
<% session.removeAttribute("book"); %>
</body>
</html>
