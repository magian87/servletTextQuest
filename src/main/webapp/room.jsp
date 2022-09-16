<%@ page import="ru.servlettextquest_.classes.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Text Quest</title>

    <link href="static/main.css" rel="stylesheet">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>

<div class="header">

</div>

<div class="statistic">
    <h1>Статистика</h1>
</div>

<div class="currentRoom">
    <h1>
        ${currentRoom.getName()}
        <%--Current room: ${hero.getCurrentRoom().getName()}--%>
    </h1>
</div>

<div class="health">
    <%--<h1>User: ${user.getUsername()} </h1>--%>
    Пользователь: ${user.getUsername()}<br>
    Сила: ${user.getStrength()} <br>
    Ловкость: ${user.getDexterity()} <br>
    Жизнь: ${user.getLife()}
</div>

<div class="characters">
    <h1>Пероснаж для диалога</h1>

    <c:forEach items="${npcs}" var="npc">
            <li>
            <form action="${pageContext.request.contextPath}/dialog" method="post">
                <input type="hidden" name="questionId" value="${npc.getStartQuestionId()}">
                <button type="submit">${npc.getName()}</button>
            </form>
            </li>
        </c:forEach>

</div>

<div class="sidebar57">

    <div class="imageRoom">
       <%-- <h1>imageRoom</h1>--%>
    </div>

    <div class="nextRooms">
        <h3>Выберите комнату для перехода</h3>
        <c:forEach items="${doorInfo}" var="door">
                <form action="${pageContext.request.contextPath}/room" method="post">
                    <input type="hidden" name="nextRoomId" value="${door.getId()}">
                    <button type="submit">${door.getName()}</button>
                </form>
        </c:forEach>
    </div>


</div>
<div class="inventory">
    <h1>Возьмите инвентарь</h1>

    <c:forEach items="${items}" var="item">
        <form action="${pageContext.request.contextPath}/room" method="post">
            <input type="hidden" name="addItemId" value="${item.getId()}">
            <button type="submit">${item.getName()}</button>
        </form>
    </c:forEach>

</div>

<div class="foother">
    <jsp:include page="addition_info.jsp"/>
</div>

<script>


</script>


</body>
</html>
