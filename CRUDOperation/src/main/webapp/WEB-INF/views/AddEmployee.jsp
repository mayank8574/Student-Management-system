<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<!DOCTYPE html>
<html lang="eng" >
<head>
<meta charset="ISO-8859-1">
<%@include file="./Header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
	position: fixed;
	text-align: left;
}
</style>
</head>
<body>
	<div class="container mt-3">
		<h1>Add Employee Form</h1> 
		<c:if test="${not empty msg }">
			<h5 class="text-danger">${msg}</h5>
			<c:remove var="msg"/>
		</c:if>
		<form:form action="insertEmployee" method="post" modelAttribute="employee" enctype="multipart/form-data">

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="name">Name</label> 
						<form:input 
							class="form-control"  path="name" 
							placeholder="Enter Name" />
						<div><form:errors path="name"  cssClass="error"/></div>
						
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="designation">Email-Id</label> <form:input 
							class="form-control" id="email" path="email"
							placeholder="Enter Email-Id" />
						<form:errors path="email"  cssClass="error"/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Department</label> 
							    <form:select path="department" class="form-control">
							    	<form:option value="" label="Select Department"></form:option>
							        <form:option value="Computer" label="Computer"></form:option>
							        <form:option value="Accounts" label="Accounts"></form:option>
							        <form:option value="Marketing" label="Marketing"></form:option>
							        <form:option value="Sales" label="Sales"></form:option>
							    </form:select>
							<form:errors path="department"  cssClass="error"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Salary</label> <form:input 
							class="form-control" id="salary" path="salary"
							placeholder="Enter Salary" />
							<form:errors path="salary"  cssClass="error"/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="address">Address</label>
						<form:textarea class="form-control" id="address" path="address"
							rows="5" placeholder="Enter Address"/>
						<div><form:errors path="address"  cssClass="error"/></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="address">Profile Image</label>
						<input type="file" class="form-control" accept="image/*" 
						name="profile" id="profile" />
					</div>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col">
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Submit</button>
						<a href="${pageContext.request.contextPath }/"
							class="btn btn-warning"> Back </a>
					</div>
				</div>
			</div>
		</form:form>

	</div>

</body>
</html>