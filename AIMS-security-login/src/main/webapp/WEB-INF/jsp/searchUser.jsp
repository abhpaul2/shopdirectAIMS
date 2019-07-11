<div id="searchEmployee">
	<form id="searchForm" action="${contextPath}/searchUser" method="GET">
		<p>
			<label class="ml-3 control-label">Enter User Id to Search/Revoke</label>
			<br/>
			<span class="ml-3 control-label" style="color: red;">${errorMsg}</span>
			<div class="col-sm-3">
				<input name="userId" type="text" class="form-control" placeholder="User ID" autofocus="true" required="required"/>
			</div>
		</p>
		<button class="ml-3 btn btn-primary" type="submit">Search</button>
		<a class="ml-3 btn btn-primary" href="${pageContext.request.contextPath}/addNewUser">Add New User</a>
	</form>
</div>