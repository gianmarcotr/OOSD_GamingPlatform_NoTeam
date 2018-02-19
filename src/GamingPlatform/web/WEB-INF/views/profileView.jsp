<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Hydrogen &mdash; A free HTML5 Template by FREEHTML5.CO</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<meta name="author" content="FREEHTML5.CO" />

  <!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FREEHTML5.CO
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

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
                        <h2>${user.userName}</h2>
                    <ul>
                        <li>Nome <b>${user.nome}</b></li>
                        <li>Cognome <b>${user.cognome}</b></li>
                        <li>Livello <b>${user.lvl}</b></li>
                        <li>Punti XP <b>${user.xp}</b></li>
                    </ul>
                    <div class="progress">
                        <div class="progress-bar progress-bar-info" style="width: ${(user.xp%1000)/10}%;">${(user.xp%1000)/10}%</div>
                    </div>
                    <hr>
                    <h4>I Miei Progressi</h4>
                    <c:if test = "${incompletedEmpty == true}">
                            <div class="alert alert-warning">
                                Non sono presenti achievements in corso
                            </div>  
                        </c:if>
                    <ul>
                        <c:forEach items="${userAchiI}" var="userAchi">
                            <li> game <b>${userAchi.achievements.gioco.nome}</b> Obiettivo <b> ${userAchi.achievements.nome}</b></li> 
                            <div class="progress">
                                <div class="progress-bar progress-bar-warning" style="width: ${(userAchi.punteggio/userAchi.achievements.punti)*100}%;"><p>${(userAchi.punteggio/userAchi.achievements.punti)*100}%</p></div>
                            </div>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h4>Obiettivi Completati</h4>
                    <c:if test = "${completedEmpty == true}">
                            <div class="alert alert-warning">
                                Non sono presenti achievements completati
                            </div>  
                        </c:if>
                    <ul>
                        <c:forEach items="${userAchiC}" var="userAchi">
                            <li> game <b>${userAchi.achievements.gioco.nome}</b> Obiettivo <b> ${userAchi.achievements.nome}</b></li> 
                            <div class="progress">
                                <div class="progress-bar progress-bar-success" style="width: ${(userAchi.punteggio/userAchi.achievements.punti)*100}%;"><p>${(userAchi.punteggio/userAchi.achievements.punti)*100}%</p></div>
                            </div>
                        </c:forEach>
                    </ul>
                        
                    </div>
                    
                        
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

