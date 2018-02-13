<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

        <!-- MENU -->
        <div id="fh5co-offcanvass">
		<a href="#" class="fh5co-offcanvass-close js-fh5co-offcanvass-close">Menu ${user.userName} <i class="icon-cross"></i> </a>
		<h1 class="fh5co-logo"><a class="navbar-brand" href="home">Gaming Platform</a></h1>
		<ul>
			<li class="active"><a href="home">Home</a></li>
                        <c:choose>
                            <c:when test = "${user != null}">
                                 <li><a href="profile"> ${user.userName}</a></li>
                                 <c:if test="${user.moderatore == 1}">
                                    <form method="post" action="review" id="rr">
                                        <input type="hidden" value="rev" name="check">
                                        <li><a href="javascript:document.getElementById('rr').submit()">Gestisci Recensioni</a></li>
                                    </form>
                                    </c:if>
                                <c:if test="${user.admin == 1}">
                                    <form method="post" action="admin" id="aa">
                                        <input type="hidden" value="aa" name="check">
                                        <li><a href="javascript:document.getElementById('aa').submit()">Admin</a></li>    
                                    </form>
                                </c:if>
                                 
                                 <form method="POST" action="logout" id="ff">
                                    <li><a href="javascript:document.getElementById('ff').submit()">LogOut</a></li>
                                </form>
                            </c:when>
                            <c:when test = "${user == null}">
                                 <li><a href="login">Login</a></li>  
                                 <li><a href="signup">Sign-up</a></li>  
                            </c:when>
                            
                        </c:choose>
		</ul>
		<h3 class="fh5co-lead">Connect with us</h3>
		<p class="fh5co-social-icons">
			<a href="#"><i class="icon-twitter"></i></a>
			<a href="#"><i class="icon-facebook"></i></a>
			<a href="#"><i class="icon-instagram"></i></a>
			<a href="#"><i class="icon-dribbble"></i></a>
			<a href="#"><i class="icon-youtube"></i></a>
		</p>
	</div>
        <!-- END MENU -->
        <!-- HEADER -->
	<header id="fh5co-header" role="banner">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<a href="#" class="fh5co-menu-btn js-fh5co-menu-btn">Menu ${user.userName}<i class="icon-menu"></i></a>
					<a class="navbar-brand" href="home">UniGame</a>		
				</div>
			</div>
		</div>
	</header>
        <!-- END HEADER -->