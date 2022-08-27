<%@ page import="ru.servlettextquest_.Questions" %>
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

<h2>
    ${currentQuestion.getCommentCurrentStep()}
    <br>
    ${currentQuestion.getQuestion()}
</h2>

<c:if test="${currentQuestion.getQuestion() != ''}">

    <label>
        <input type="radio" name="question" value=${currentQuestion.getAccept()}>
            ${currentQuestion.getAcceptText()}
    </label>
    <br>
    <label>
        <input type="radio" name="question" value=${currentQuestion.getReject()}>
            ${currentQuestion.getRejectText()}
    </label>


    <p><input type="button" value="Ответить" onclick="window.location='/logic?click='+getValueCheckedRadioButton() ">
    </p>

</c:if>

<br>

<c:if test="${currentQuestion.getQuestion() == ''}">
    <button onclick="window.location='/start'">Повторить игру</button>
</c:if>

<script>
    function getValueCheckedRadioButton() {
        let val = $('input[name="question"]:checked').val();
        if (val === undefined) {
            alert('Необходимо выбрать действие!')
        } else
            return val;
    }


</script>


</body>
</html>
