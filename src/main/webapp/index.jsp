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

<!-- Чтобы сделать ссылку в виде кнопки добавим к ней 2 класса: btn и btn-success -->

<nav>
    <div class="nav nav-tabs" id="tab" role="tablist">
        <!-- атрибут data-bs-target должен указывать на элемент, который необходимо показать -->
        <button class="nav-link active" id="tab-section-1" data-bs-toggle="tab" data-bs-target="#section-1" type="button" role="tab" aria-controls="nav-section-1" aria-selected="true">Секция 1</button>
        <button class="nav-link" id="tab-section-2" data-bs-toggle="tab" data-bs-target="#section-2" type="button" role="tab" aria-controls="nav-section-2" aria-selected="false">Секция 2</button>
        <button class="nav-link" id="tab-section-3" data-bs-toggle="tab" data-bs-target="#section-3" type="button" role="tab" aria-controls="nav-section-3" aria-selected="false">Секция 3</button>
    </div>
</nav>
<div class="tab-content p-3" id="nav-tabContent">
    <!-- атрибут id определяет вкладку -->
    <div class="tab-pane fade show active" id="section-1" role="tabpanel" aria-labelledby="nav-section-1-tab">
        ...
    </div>
    <div class="tab-pane" id="section-2" role="tabpanel" aria-labelledby="nav-section-2-tab">
        ...
    </div>
    <div class="tab-pane fade" id="section-3" role="tabpanel" aria-labelledby="nav-section-3-tab">
        ...
    </div>
</div>


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
