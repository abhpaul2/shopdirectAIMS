<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
	    <link rel="stylesheet" href="<c:url value="/css/styles.css" />">
	
	    <title>AIMS Access</title>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<section class="container-fluid body_section">
		<div class="container">
			<h2 class="ml-3 mt-4 form-heading">Sign in</h2>
		    <form method="POST" action="${contextPath}/login" class="form-signin">        
		        <div class="form-group ${error != null ? 'has-error' : ''}">
		          	<span style="color: red;">${errorMsg}</span>
		            <span class="ml-3 text-success">${msg}</span>
		            <div class="col-sm-3 mt-3">
		            	<input name="username" type="text" class="form-control" placeholder="Username" autofocus="true" />
		            </div>		        
		  			<div class="form-group col-sm-3 mt-3">		  				
		            	<input name="password" type="password" class="form-control" placeholder="Password"/>          
					</div>
		            <button class="ml-3 btn btn-primary" type="submit">Sign in</button>
		        </div>
		    </form>
		</div>
		</section>
		<jsp:include page="footer.jsp" />
		<!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>
