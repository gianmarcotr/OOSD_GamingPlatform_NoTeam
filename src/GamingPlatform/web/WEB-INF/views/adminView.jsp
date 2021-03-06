<%-- 
    Document   : loginView
    Created on : 30-gen-2018, 11.24.20
    Author     : gimmi
--%>

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
                                <form method="POST" action="admin">
                                <div class="col-md-8">
                                        <h2>Inserisci Gioco</h2>
                                        
                                        <div class="fh5co-pricing-table">
                                        <ul class="fh5co-pricing-table">
                                            <input type="hidden" name="check" value="game">
                                            <li class="fh5co-include"><input type="text" class="form-control" name="nome" placeholder="Nome" required></li>
                                            <br>
                                            <li class="fh5co-include"><input type="text" class="form-control" name="descr" placeholder="Descrizione" required></li>
                                            <br>
                                            <li class="fh5co-include"><input type="text" class="form-control" name="genere" placeholder="Genere" required></li>
                                        </ul>
                                        <input type="submit" value= "Invia" class="btn btn-block btn-sm btn-primary">
                                        </div>
                                </div>
                                </form>
                            </div>
                            <hr>
                            <div class="col-md-8 col-md-offset-2">
                                <form method="POST" action="admin">
                                <div class="col-md-8">
                                        <h2>Inserisci Achievement</h2>
                                        <div class="fh5co-pricing-table">
                                        <ul class="fh5co-pricing-table">
                                            <input type="hidden" name="check" value="achi">
                                            <li class="fh5co-include"><input type="text" class="form-control" name="nome" placeholder="Nome"></li>
                                            <br>
                                            <li class="fh5co-include"><input type="text" class="form-control" name="descr" placeholder="Descrizione"></li>
                                            <br>
                                            <li class="fh5co-include"><input type="number" class="form-control" name="punti" placeholder="Punti necessari"></li>
                                            <br>
                                            <li class="fh5co-include"><input type="number" class="form-control" name="premioXP" placeholder="Premio XP"></li>
                                            <br>
                                            <li class="fh5co-include">
                                                <select class="form-control" name="idG">
                                                    <option value=""> scegli gioco...</option>
                                                    <c:forEach items="${games}" var="game">
                                                        <option value="${game.idG}"> ${game.nome} </option>
                                                    </c:forEach>
                                                </select>
                                            </li>
                                        </ul>
                                    
                                        <input type="submit" value= "Invia" class="btn btn-block btn-sm btn-primary">
                                        </div>
                                </div>
                                </form>
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

