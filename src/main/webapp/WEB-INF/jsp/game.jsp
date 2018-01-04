<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="refresh" content="10">
<title>Gra w karty</title>
<link rel="stylesheet" type="text/css" href="css/game.css">
</head>
<body>
  <div class="container">
    <div class="canvas-container">
      <canvas id="canvas" width="920" height="650">
            <p>Canvas nie jest wspierany przez twoją przeglądarkę</p>
      </canvas>
    </div>
    <div class="gamebuttons">
      <button type="submit" name="OK">OK</button>
      <button type="submit" name="GET">WEŹ KARTY</button>
      <button type="submit" name="OK">ZREZYGNUJ Z GRY</button>
    </div>
    <div class="myDeck">
      <ul>

      </ul>
    </div>
  </div>
  <div class="modal">
    <div class="modal-content">
      <h1>Dołącz do gry</h1>
      <form action="addUser" method="post">
        <label>Twój nick: <input type="text" name="name"></label>
        <input type="submit" class="button" value="Dodaj mnie">
      </form>
      <a href="cancel" class="button">Opuść stronę</a>
      <a href="start" class="button">Start</a>
      <p>Zalogowani:</p>
      <ul>
        <c:choose>
          <c:when test="${not empty test}">
            <c:forEach items="${test}" var="item">
              <li><c:out value="${item.name}" /> <c:out value="${item.sessionId}" /></li>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <li>Brak zalogowanych użytkowników</li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
  <script src="js/jquery-3.2.1.min.js" type="application/javascript"></script>
  <script src="js/game.js" type="application/javascript"></script>
</body>
</html>