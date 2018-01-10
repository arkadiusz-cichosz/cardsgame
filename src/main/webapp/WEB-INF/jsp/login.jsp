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
  <div class="modal">
    <div class="modal-content">
      <a href="cancel" class="modal-content-close">x</a>
      <h1>Dołącz do gry</h1>
      <form action="addPlayer" method="post">
        <p>
          Twój nick: <input type="text" name="name">
        </p>
        <p>
          Wybierz stół gry: <select id="gameList" name="table">
 
          </select>
        </p>
        <button type="submit">Dołącz</button>
      </form>
    </div>
  </div>
  <script src="js/jquery-3.2.1.min.js" type="application/javascript"></script>
  <script src="js/game.js" type="application/javascript"></script>
</body>
</html>