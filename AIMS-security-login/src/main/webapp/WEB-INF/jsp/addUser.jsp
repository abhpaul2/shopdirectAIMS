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
		            <li class="nav-item active">
		              <a class="nav-link" href="${pageContext.request.contextPath}/addNewUser">New</a>
		            </li>
		            <li class="nav-item">
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
			<div id="addUser" class="container">
				<h3 class="ml-0 mt-3" style="color: #25408F;">Add New User</h3>				
				<form:form action="/addNewUser" method="post"
					modelAttribute="user" cssClass="form-horizontal">
					<div class="form-group row">
					    <label class="control-label col-sm-2">Enter User ID</label>
					    <div class="col-sm-3">
					    	<form:input cssClass="form-control" path="userId" autofocus="true" required="required" />
					    </div>	
					    <span class="ml-3 control-label" style="color: red;">${errorMsg}</span>			    
					 </div>	
					 <div class="form-group row">
					    <label class="control-label col-sm-2">Enter User Name</label>
					    <div class="col-sm-3">
					    	<form:input cssClass="form-control" path="userName" required="required" />
					    </div>				    
					 </div>
					 <div class="form-group row">
					    <label class="control-label col-sm-2">Enter Clone ID</label>
					    <div class="col-sm-3">
					    	<form:input cssClass="form-control" path="cloneId" required="required" />
					    </div>	
					    <span class="ml-3 control-label" style="color: red;">${errorMsgClone}</span>				    
					 </div>
					<button type="submit" class="ml-3 btn btn-primary ">Submit</button>
				</form:form>
			</div>
		</section>
		<jsp:include page="footer.jsp"/>
	</body>
</html>