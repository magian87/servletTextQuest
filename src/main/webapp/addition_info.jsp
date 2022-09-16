<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.servlettextquest_.classes.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="questRepository" value="${applicationScope.get('questRepository')}"/>
<c:set var="itemRepository" value="${applicationScope.get('itemRepository')}"/>

<h3>Quests:</h3>
<ul>
    <c:forEach items="${sessionScope.user.getQuests()}" var="questId">
        <c:set var="quest" value="${questRepository.getById(questId)}"/>
        <li>
                <p>Quest name: ${quest.getText()}</p>
                <p>Is finished: ${quest.isFinished(sessionScope.user)}</p>
        </li>
    </c:forEach>
</ul>


<h3>Items:</h3>
<ul>
    <c:forEach items="${sessionScope.user.getItems()}" var="itemId">
        <c:set var="item" value="${itemRepository.getById(itemId)}"/>
        <li>
                <p>item name: ${item.getName()}</p>
        </li>
    </c:forEach>
</ul>

<br>
<hr>