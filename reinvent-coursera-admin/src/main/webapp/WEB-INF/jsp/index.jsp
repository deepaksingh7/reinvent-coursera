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
<title>Coursera Admin: Application Login</title>
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">

				<form class="login100-form validate-form"
					action="<c:url value="login_form" />" method="post">

					<!-- error display block -->

					<c:if test="${login_error != null && login_error.length() > 0}">
						<span class="login100-form-title p-b-34 inputError">${login_error}</span>
					</c:if>
					<c:if test="${login_message != null && login_message.length() > 0}">
						<span class="login100-form-title p-b-34">${login_message}</span>
					</c:if>

					<!-- User Name -->
					<c:if
						test="${user_name_error != null && user_name_error.length() > 0}">
						<span class="ilogin100-form-title p-b-34 inputError">${user_name_error}</span>
					</c:if>

					<!-- Password -->
					<c:if
						test="${password_error != null && password_error.length() > 0}">
						<span class="ilogin100-form-title p-b-34 inputError">${password_error}</span>
					</c:if>

					<span class="login100-form-title p-b-34">Re-invent Coursera
						Admin Login V_2.0.0</span>

					<div class="wrap-input100 rs1-wrap-input100 validate-input m-b-20"
						data-validate="Type user name">

						<c:choose>
							<c:when
								test="${user_name_val != null && user_name_val.length() > 0}">
								<input type="text" id="user_name" name="user_name"
									class="input100" value="${user_name_val}">
								<span class="focus-input100"></span>
							</c:when>
							<c:otherwise>
								<input type="text" id="user_name" class="input100"
									name="user_name" placeholder="User name">
								<span class="focus-input100"></span>
							</c:otherwise>
						</c:choose>

					</div>

					<div class="wrap-input100 rs2-wrap-input100 validate-input m-b-20"
						data-validate="Type password">
						<input class="input100" type="password" id="password"
							name="password" placeholder="Password"> <span
							class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Sign in</button>
					</div>

					<div class="w-full text-center p-t-27 p-b-239">
						<span class="txt1"> Forgot </span>
						<%-- <a href="<c:url value="/username_lookup" />" class="txt2"> Username </a> 
						<span class="txt2"> / </span> --%>
						<a href="<c:url value="/forgot_password" />" class="txt2">
							password? </a> <span class="txt2"> &nbsp;&nbsp;&nbsp;&nbsp; </span> <span
							class="txt1"> Reset </span> <a
							href="<c:url value="/reset_password" />" class="txt2">
							password </a>
					</div>

					<%-- <div class="w-full text-center">
						<a href="<c:url value="/create_user" />" class="txt3"> Sign Up
						</a> --%>
			</div>
			</form>


		</div>
	</div>
	</div>







</body>
</html>