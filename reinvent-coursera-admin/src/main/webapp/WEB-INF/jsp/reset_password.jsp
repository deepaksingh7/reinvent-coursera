<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/kube-6.5.2.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<link rel="icon" type="image/png" href="resources/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Coursera Admin: Reset Password</title>
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
						<div class="login100-form-title p-t-20">Reset Forgotten Password</div>
						<div class="login100-form-txt p-t-20">This form is used to reset a user's password with an emailed code. You can use the 
							<a href="<c:url value="/forgot_password" />">forgotten password</a> form to request a reset code.
						</div>
					
						<c:if test="${reset_password_error != null && reset_password_error.length() > 0}">
						   <span class="login100-form-title p-b-34 inputError">${reset_password_error}</span>
						</c:if>
			
						<!-- Post to login form -->
						<form action="<c:url value="/reset_password_form" />" method="post" style="margin:10px;">
						
						<!-- User Name -->
						<c:if test="${user_name_error != null && user_name_error.length() > 0}">
						   <span class="login100-form-title p-b-34 inputError">${user_name_error}</span>
						</c:if>
						
						<!--  if the user name is OK, fill it in so the user doesn't have to keep retyping it if, for
			      example, the password and the duplicate password don't match. -->
				  
						<div class="wrap-input100  validate-input m-b-20" align="center" >
						
								<c:choose>
									   <c:when test="${user_name_val != null && user_name_val.length() > 0}">
											<input type="text" id="user_name" name="user_name" class="input100" value="${user_name_val}" placeholder="User name" >
									   </c:when>
									   <c:otherwise>
											 <input type="text" id="user_name" name="user_name" class="input100" placeholder="User name" >
									   </c:otherwise>
									</c:choose>
						
						</div>
						
						<!-- Reset code -->
						<c:if test="${reset_code_error != null && reset_code_error.length() > 0}">
							  <span class="login100-form-title p-b-34 inputError">${reset_code_error}</span>
						</c:if>
							
						<div class="wrap-input100  validate-input m-b-20" align="center" >
							 <input type="text" id="reset_code" name="reset_code" class="input100" placeholder="Password Reset Code">
						</div>
						
						<!-- New Password -->
						<c:if test="${new_password_error != null && new_password_error.length() > 0}">
						     <span class="login100-form-title p-b-34 inputError">${new_password_error}</span>
						</c:if>
						
						<div class="wrap-input100  validate-input m-b-20" align="center" >
							 <input type="password" id="new_password" name="new_password" class="input100" placeholder="New Password">
						</div>
						
						<!-- Confirm Password -->
						<c:if test="${verify_password_error != null && verify_password_error.length() > 0}">
						   <span class="login100-form-title p-b-34 inputError">${verify_password_error}</span>
						</c:if>
						
						<div class="wrap-input100  validate-input m-b-20" align="center" >
							 <input type="password" id="verify_password" name="verify_password" class="input100" placeholder="Verify Password">
						</div>
						
						<div class="p-t-10">
								<span>
									<button type="submit" class="login100-form-btn">Reset Password</button>
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