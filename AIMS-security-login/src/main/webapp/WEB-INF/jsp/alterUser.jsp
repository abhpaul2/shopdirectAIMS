<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <!-- favicon added -->
	    <link rel="icon" type="image/x-icon" href="images/favicon.png">
	    <!-- Font CSS -->
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
	    <!-- Custom CSS -->
	    <link rel="stylesheet" href="css/styles.css">
	
	    <title>AIMS Access</title>
	</head>
	<body>
		<header>
			<!-- Header using navbar -->
		    <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-background">
		        <img class="header_img" src="images/logo.png" alt="logo">
		        <a class="navbar-brand" href="#">SHOP DIRECT AIMS Access</a>
		          <!-- Menu Items -->
		          <ul class="navbar-nav ml-auto navbar-margin">
		            <li class="nav-item">
		              <a class="nav-link" href="${pageContext.request.contextPath}/welcome">Home <span class="sr-only">(current)</span></a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="${pageContext.request.contextPath}/addNewUser">New</a>
		            </li>
		            <li class="nav-item active">
		              <a class="nav-link" href="/alterUser">Alter</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="/revoke">Revoke</a>
		            </li>            
		            <li class="nav-item">
		              <a class="nav-link " onclick="document.forms['logoutForm'].submit()" tabindex="-1" aria-disabled="true">Logout</a>
		            </li>
		          </ul>
		        <form id="logoutForm" method="POST" action="${contextPath}/logout"></form>
		      </nav>
		</header>
		<section class="body_section">			
			<div class="jumbotron text-center">
		       <h3 class="display-5">Please LOG IN to New AIMS application (option A->6->1) to alter the user access</h3>		       
		     </div>
		</section>
		<jsp:include page="footer.jsp" />		
	</body>
</html>