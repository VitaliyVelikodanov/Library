<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>List of books</title>
	<link href="css/default.css" rel="stylesheet">
</head>
<body>
<jsp:include page="jspf/head.jspf"></jsp:include>

<c:choose>
	<c:when test="${empty books}">
		<h2>No books found</h2>
	</c:when>
	<c:otherwise>
		<table class="brd">
			<thead class="tabhead">
			<colgroup class="left"/>
			<colgroup class="left"/>
			<colgroup class="left"/>
			<colgroup class="left"/>
			<tr>
				<th class="brd">Book id</th>
				<th class="brd">Title</th>
				<th class="brd">Body</th>
				<th class="brd">Author</th>
				<th class="brd">Genre</th>
				<th class="brd">Edit</th>
				<th class="brd">Remove</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="book" items="${books}">
				<tr>
					<td class="brd">${book.id}</td>
					<td class="brd">${book.title}</td>
					<td class="brd">${book.body}</td>
					<td class="brd">${book.author.name}</td>
					<td class="brd">${book.genre.genreName}</td>
					<td class="brd">
						<form action="editBook" method="get">
							<input type="hidden" name="editBookId" id = "editBookId" value="${book.id}" />
							<input type="submit" value="Edit" />
						</form>
					</td>
					<td class="brd">
						<form action="deleteBook" method="post">
							<input type="hidden" name="bookId" id = "bookId" value="${book.id}" />
							<input type="submit" value="Delete" />
						</form>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>
