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
  <div class="container">
    <div class="canvas-container">
      <canvas id="canvas" width="920" height="650">
            <p>Canvas nie jest wspierany przez twoją przeglądarkę</p>
      </canvas>
    </div>
    <div class="gamebuttons">
      <!-- <button id="ok">OK</button>
      <button id="take">WEŹ KARTY</button> -->
      <!-- <button id="cancel" >ZREZYGNUJ Z GRY</button> -->
    </div>
    <div class="myDeck">
      <ul>

      </ul>
    </div>
    <a href="cancel" class="close">&times</a>
  </div>
  <script src="js/jquery-3.2.1.min.js" type="application/javascript"></script>
  <script src="js/game.js" type="application/javascript"></script>
</body>
</html>