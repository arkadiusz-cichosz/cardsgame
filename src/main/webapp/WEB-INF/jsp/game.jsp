<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="refresh" content="10"> -->
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
      <button type="submit" name="CANCEL">ZREZYGNUJ Z GRY</button>
    </div>
    <div class="myDeck">
      <ul>

      </ul>
    </div>
  </div>
  <%--  <c:set var="user" value=""/>
  <c:set var="players" value=""/> --%>
  <div class="modal">
    <div class="modal-content">
      <c:choose>
        <c:when test="${user == null}">
          <a href="cancel" class="modal-content-close">x</a>
          <h1>Dołącz do gry</h1>
          <form action="addPlayer" method="post">
            <p>
              Twój nick: <input type="text" name="name">
            </p>
            <p>
              Wybierz stół gry: <select name="table">
                <option value="new" selected>Nowy</option>
                <c:if test="${games != null}">
                  <c:forEach items="${games}" var="item">
                    <option value="${item.key}">${item.value.name}</option>
                  </c:forEach>
                </c:if>
              </select>
            </p>
            <button type="submit">Dołącz</button>
          </form>
        </c:when>
        <c:otherwise>
          <h1>Stolik gracza ${user.name}"</h1>
          <c:if test="${games != null}">
            <c:forEach items="${games}" var="item">
              <option value="${item.key}">${item.value.name}</option>
            </c:forEach>
          </c:if>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
  <script src="js/jquery-3.2.1.min.js" type="application/javascript"></script>
  <script src="js/game.js" type="application/javascript"></script>
</body>
</html>