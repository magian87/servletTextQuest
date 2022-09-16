<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вход в игру</title>
    <link href="static/bootstrap.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<h1>Вход в игру</h1>
<form action="${pageContext.request.contextPath}/entrance" method="post">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="username">
    </div>
    <div class="button">
        <button type="submit" class="btn btn-success">Зайти в игру</button>
    </div>
</form>
</body>

<%--

https://getbootstrap.com/docs/5.2/components/modal/

--%>

</html>