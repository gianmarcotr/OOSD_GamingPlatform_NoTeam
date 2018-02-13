<%-- 
    Document   : gameView
    Created on : 9-feb-2018, 12.44.40
    Author     : gimmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>GamingPlatform &mdash; project OOSD </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<meta name="author" content="FREEHTML5.CO" />


  <!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<!-- Google Webfonts -->
	<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">
	<!-- Salvattore -->
	<link rel="stylesheet" href="css/salvattore.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
        <jsp:include page="_header.jsp"></jsp:include>
	<!-- END .header -->
	<div id="fh5co-main">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h2>${game.nome}</h2>
                        <h3>Simulazione sessione di gioco: clicca su play per completare gli obiettivi </h3>
                        <hr>
                        <ul>
                            <li>Descrizione <b>${game.descr}</b></li>
                            <li>Genere <b>${game.genere}</b></li>
                        </ul>
                            <c:if test="${successAchi}!= n">
                                <div class="alert alert-success">
                                    <strong>Success!</strong> ${successAchi}
                                </div>
                            </c:if>
                                
                        
                    </div>
                </div><hr>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <form action="play" method="post">
                            <input type="hidden" value="${game.idG}" name="idG">
                            <input type="submit" class="btn btn-block btn-sm btn-primary" value="play">
                        </form>
                    <c:forEach items="${userAchi}" var="userAchi">
                        Obiettivo <b> ${userAchi.achievements.nome}</b> Punteggio richiesto <b>${userAchi.achievements.punti} </b> Punteggio attuale <b>${userAchi.punteggio}</b>
                            <div class="progress">
                                <div class="progress-bar progress-bar-success" style="width: ${(userAchi.punteggio/userAchi.achievements.punti)*100}%;"><p>${(userAchi.punteggio/userAchi.achievements.punti)*100}%</p></div>
                            </div>
                    </c:forEach>
                         <form action="game" method="GET">
                            <input type="hidden" value="${game.idG}" name="idG">
                            <input type="submit" class="btn btn-block btn-sm" value="Chiudi">
                        </form>
                    </div>
                        
                </div>
            <hr>
            
            </div>
            
            </div>
        </div>
	       
        
        
        
        
        
        <jsp:include page="_footer.jsp"></jsp:include>
        <!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<!-- Salvattore -->
	<script src="js/salvattore.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>

	

	
	</body>
</html>
