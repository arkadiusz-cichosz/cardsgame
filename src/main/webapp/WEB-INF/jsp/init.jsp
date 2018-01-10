<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gra w karty</title>
<link rel="stylesheet" type="text/css" href="css/game.css">
</head>
<body>
  <div class="modal">
    <div class="modal-content">
    <a href="cancel" class="modal-content-close">x</a>
      <h1>Witaj ${user}</h1>
          <p>Oto lista twoich graczy:</p>
          <ul id="gamerList"></ul>
          <button disabled>START</button>
        
          <p>Oczekujesz na uruchomienie gry przez innego gracza...</p>
          <img alt="" src="images/klepsydra.png">
        
    </div>
  </div>
</body>
</html>