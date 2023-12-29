<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add a Book</title>
	<link href="css/default.css" rel="stylesheet">
</head>
<body>
<jsp:include page="jspf/head.jspf"></jsp:include>
<div>
	<form action="add" method="post">
		<label for="title">Title:</label>
		<input type="text" name="title" id="title" required/>
		<br/>

		<label for="bookBody">Body:</label>
		<textarea name="bookBody" id="bookBody" required></textarea>
		<br/>

		<h3>Author</h3>
		<label for="authorName">Name:</label>
		<input type="text" id="authorName" name="authorName" required/>
		<br/>

		<label for="placeOfBirth">Place of Birth:</label>
		<input type="text" id="placeOfBirth" name="placeOfBirth" required/>
		<br/>

		<label for="biography">Biography:</label>
		<textarea name="biography" id="biography" required></textarea>
		<br/>

		<label for="majorWorks">Major Works:</label>
		<textarea name="majorWorks" id="majorWorks" required></textarea>
		<br/>

		<h3>Genre</h3>
		<label for="genreName">Genre Name:</label>
		<input type="text" id="genreName" name="genreName" required/>
		<br/>

		<input type="submit" value="AddBook" />
	</form>
</div>
</body>
</html>
