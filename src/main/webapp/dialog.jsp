<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrylat
  Date: 06.09.2022
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dialog</title>
</head>
<body>

<h1>Dialog</h1>
<h3>Text: ${question.getText()}</h3>
<ul>
    <c:forEach items="${question.getAnswers()}" var="answer">
        <%--<c:out value="${answer.getText()}"/>--%>

        <li>
            <c:choose>
                <c:when test="${answer.getNextQuestion() == null && answer.getQuestId() == null}">
               <%-- <c:when test="${answer.getNextQuestion() == null}">--%>
                    <a href="${pageContext.request.contextPath}/dialog?finish=true">${answer.getText()}</a>
                </c:when>
                <c:when test="${answer.getNextQuestion() != null}">
                    <a href="${pageContext.request.contextPath}/dialog?question=${answer.getNextQuestion()}">${answer.getText()}</a>
                    <%--<a href="${pageContext.request.contextPath}/dialog?message=${answer.getNextQuestion()}&lt;%&ndash;&npcName=${npcName}&ndash;%&gt;">${answer.getText()}</a>--%>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/dialog?quest=${answer.getQuestId()}">${answer.getText()}</a>
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
</body>
</html>
