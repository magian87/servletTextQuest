<%@ page import="ru.servlettextquest_.classes.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Text Quest</title>

    <link href="static/main.css" rel="stylesheet">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link href="/static/bootstrap.min.css" rel="stylesheet">
    <script src="/static/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="header">

</div>

<div class="statistic">
    <h5>Статистика</h5>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
        Вывести доп. инф.
    </button>
</div>

<div class="currentRoom">
    <h5>
        Текущая комната: ${currentRoom.getName()} <br>
        Игра окончена: ${user.getIsGameOver()}
        <c:if test="${user.getIsGameOver()}">
            <form action="${pageContext.request.contextPath}/" method="post">
              <button type="submit">Начать с начала!</button>
            </form>
        </c:if>

        <%--Current room: ${hero.getCurrentRoom().getName()}--%>
    </h5>
</div>

<div class="health">
    <%--<h1>User: ${user.getUsername()} </h1>--%>
    <h5>
        Пользователь: <br>
        ${user.getUsername()}<br>
    </h5>


</div>

<div class="characters">
    <h5>Пероснаж для диалога</h5>

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
    <h5>Возьмите инвентарь</h5>

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


<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Пользователь: ${user.getUsername()}</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                Сила: ${user.getStrength()} <br>
                Ловкость: ${user.getDexterity()} <br>
                Жизнь: ${user.getLife()}
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>


<script>


</script>


</body>
</html>
