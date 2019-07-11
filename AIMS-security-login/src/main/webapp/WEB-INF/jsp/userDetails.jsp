<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
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
		            <li class="nav-item">
		              <a class="nav-link" href="/alterUser">Alter</a>
		            </li>
		            <li class="nav-item active">
		              <a class="nav-link" href="/revoke">Revoke</a>
		            </li>            
		            <li class="nav-item">
		              <a class="nav-link " onclick="document.forms['logoutForm'].submit()" tabindex="-1" aria-disabled="true">Logout</a>
		            </li>
		          </ul>
		        <form id="logoutForm" method="POST" action="${contextPath}/logout"></form>
		      </nav>
		</header>
		<section class="container-fluid body_section">
		<h3 class="m-3" style="color: #25408F;">User Details</h3>
		<span style="color: red;">${errorMsg}</span>
	    <span class="ml-3 pb-3 text-success">${msg}</span>		
		<table class="table table-bordered">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col">User ID</th>
		      <th scope="col">User Name</th>
		      <c:if test="${user.cloneId}"><th scope="col">Clone ID</th></c:if>		      
		      <th scope="col">Access Status</th>
		    </tr>
		  </thead>
		  <tbody>			
			<tr>
		      <td>${user.userId}</td>
		      <td>${user.userName}</td>
		      <c:if test="${user.cloneId}"><th scope="col"><td>${user.cloneId}</td></th></c:if>		      
		      <td>
		      	<c:if test="${user.status}">Y</c:if>
		      	<c:if test="${!user.status}">N</c:if>
		      </td>
		      <c:set var = "userId" scope = "request" value = "${user.userId}"/>
		      <c:set var = "userStatus" scope = "request" value = "${user.status}"/>
		    </tr>
		  </tbody>
		</table>
		<c:if test="${userStatus&&!addFunction}">
			 <div class="form-inline">		
			    <form id="updateUser" method="GET" action="${contextPath}/alterUser" class="form-inline">			    	
			    	<button class="btn btn-primary" type="submit"> Alter </button>
			    </form>
			 </div>
			 <div class="form-inline">
			    <form id="updateUser" method="GET" action="${contextPath}/revokeUser" class="form-inline">
			    	<input name="userId" type="hidden" value="${userId}"/>
			    	<button class="btn btn-primary" type="submit"> Revoke </button>
			    </form>	
		    </div>
	    </c:if>		    	
		</section>
		<jsp:include page="footer.jsp" />
	</body>
</html>