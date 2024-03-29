<header>
	<!-- Header using navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-background">
        <img class="header_img" src="images/logo.png" alt="logo">
        <a class="navbar-brand" href="#">SHOP DIRECT AIMS Access</a>
          <!-- Menu Items -->
          <ul class="navbar-nav ml-auto navbar-margin">
            <li class="nav-item active">
              <a class="nav-link" href="${pageContext.request.contextPath}/welcome">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
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