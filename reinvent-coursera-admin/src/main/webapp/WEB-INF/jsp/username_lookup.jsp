<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/kube-6.5.2.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
<link rel="icon" type="../image/png" href="<c:url value="/resources/icons/favicon.ico" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/util.css" />">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Coursera Admin: User Name Lookup</title>
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
			<!-- Navigation Menu -->
				<div class="w3-bar w3-blue">
					<form action="<c:url value="/application" />" style="display: inline"><button class="w3-bar-item w3-button">Home</button></form>
					<form action="<c:url value="/courses" />" style="display:inline"><button class="w3-bar-item w3-button" >Courses</button></form>
					<form action="<c:url value="/category" />" style="display:inline"><button class="w3-bar-item w3-button" >Category</button></form>
					<form action="<c:url value="/instructors" />" style="display:inline"><button class="w3-bar-item w3-button" >Instructors</button></form>
					<form action="<c:url value="/logout_form" />" method="post"> <button class="w3-bar-item w3-button" style="float: right;">Logout</button></form>
				</div>
				<div class="login100_form validate-form">
					<div class="wrap-input100 ">
						<div class="login100-form-title p-t-20">User Name Lookup</div>
						<!-- Post to lookup controller -->
						<form action="<c:url value="/username_lookup_form" />" method="post" style="margin:10px;">
						<c:if test="${email_error != null && email_error.length() > 0}">
						   <span class="login100-form-title p-b-34 inputError">${email_error}</span>
						</c:if>
						<div class="wrap-input100  validate-input m-b-20" align="center" >
								<input type="email" id="email_address" name="email_address" class="input100" placeholder="Your Email Address" >
						</div>
						<div class="p-t-10">
								<span>
									<button type="submit" class="login100-form-btn">Lookup User Name</button>
								</span>
						</div>
						</form>
					 </div>
				 </div>
			</div>
		</div>
	</div>
</body>
</html>