<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.servlettextquest_.classes.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="questRepository" value="${applicationScope.get('questRepository')}"/>
<c:set var="itemRepository" value="${applicationScope.get('itemRepository')}"/>

<h3>Квесты:</h3>
<ul>
    <c:forEach items="${sessionScope.user.getQuests()}" var="questId">
        <c:set var="quest" value="${questRepository.getById(questId)}"/>
                <p>Наименование квеста: ${quest.getText()}</p>
                <p>Квест завершен: ${quest.isFinished(sessionScope.user)}</p>
    </c:forEach>
</ul>


<h3>Инвентарь:</h3>
<ul>
    <c:forEach items="${sessionScope.user.getItems()}" var="itemId">
        <c:set var="item" value="${itemRepository.getById(itemId)}"/>
              ${item.getName()}<br>
    </c:forEach>
</ul>

<br>
<hr>