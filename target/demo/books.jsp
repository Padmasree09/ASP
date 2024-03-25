<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
</head>
<body>
    <h1>Book List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/books">
                        <input type="hidden" name="id" value="${book.id}">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add New Book</h2>
    <form method="post" action="${pageContext.request.contextPath}/books">
        <input type="hidden" name="action" value="add">
        Title: <input type="text" name="title"><br>
        Author: <input type="text" name="author"><br>
        Price: <input type="text" name="price"><br>
        <button type="submit">Add Book</button>
    </form>
</body>
</html>
