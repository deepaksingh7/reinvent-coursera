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
<title>Coursera Admin: Category</title>
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
					  <div class="login100-form-title p-b-20 p-t-20" style="padding-bottom:-20px"> Here are the list of Category:</div>
					  <c:if test="${errorMessage != null && errorMessage.length() > 0}">
							<span class="login100-form-title p-b-34 inputError"> ${errorMessage}</span>
						</c:if>
							<div class="input100 p-t-20"> 	
								<c:forEach var="listValue" items="${category}">
								<hr>	
									<p class="p-t-10">${listValue.categoryName}</p>
									<p class="p-t-10">
										<form action="<c:url value="/delete_category_form" />" method="post" style="display: inline">
											<input type="hidden" value="${listValue.categoryId}" name="categoryId" id="categoryId" />
											<button class="formButton">Delete</button></form>
										<form action="<c:url value="/category/${listValue.categoryId}" />"  style="display: inline">
										<button class="formButton">Update</button></form>
									</p>
								</c:forEach>
							</div>
							<form action="<c:url value="/addcategory" />" style="margin:10px;">
							<div class="p-t-10">
								<span>
									<button type="submit" class="login100-form-btn">Add Category</button>
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