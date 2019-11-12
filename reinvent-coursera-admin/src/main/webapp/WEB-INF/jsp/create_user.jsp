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
<title>Coursera Admin: Create User</title>
</head>
<body>
	<div class="limiter">

		<div class="container-login100">
			<div class="wrap-login100">
			
			<!-- Navigation Menu -->

				<div class="w3-bar w3-blue">
					<form action="<c:url value="/application" />" style="display: inline"><button class="w3-bar-item w3-button">Home</button></form>
				</div>

			
				<div class="login100_form validate-form">
					<div class="wrap-input100 ">
						<div class="login100-form-title p-t-20">Create a new user account</div>
						<div class="login100-form-txt p-t-40 p-b-40">An email with an account creation code will be sent to your email address.</div>
						
						<c:if test="${createUserError != null && createUserError.length() > 0}">
							<span class="login100-form-title p-b-34 inputError">${createUserError}</span>
						</c:if>
						
						<!-- Post to the login controller -->
						<form action="<c:url value="/create_user_form" />" method="post" style="margin:10px;">
						<!--
						<c:if test="${userNameError != null && userNameError.length() > 0}">
						   <span class="login100-form-title p-b-34 inputError">${userNameError}</span>
						</c:if>
						
						
						  if the user name is OK, fill it in so the user doesn't have to keep retyping it if, for
					      example, the password and the duplicate password don't match. 
						<div class="wrap-input100  validate-input m-b-20" align="center" >
						<c:choose>
						   <c:when test="${userNameVal != null && userNameVal.length() > 0}">
								<input type="text" id="user_name" name="user_name" class="input100" value="${userNameVal}" placeholder="User name" >
						   </c:when>
						   <c:otherwise>
							  	<input type="text" id="user_name" name="user_name" class="input100" placeholder="User name" >
						   </c:otherwise>
						</c:choose>
						</div>
						-->
						<c:if test="${emailError != null && emailError.length() > 0}">
						   <span class="login100-form-title p-b-34 inputError">${emailError}</span>
						</c:if>
						
						<div class="wrap-input100  validate-input m-b-20" align="center" >
								<input type="text" id="email" name="email" class="input100" placeholder="Email" >
						</div>
						
						
						<div class="p-t-10">
								<span>
									<button type="submit" class="login100-form-btn">Create user</button>
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