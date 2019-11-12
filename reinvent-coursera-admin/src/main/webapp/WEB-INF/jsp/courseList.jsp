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
<title>Coursera Admin: Courses</title>
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
					  <div class="login100-form-title p-b-20 p-t-20" style="padding-bottom:-20px"> Here are the list of courses:</div>
					  
					  <c:if test="${errorMessage != null && errorMessage.length() > 0}">
							<span class="login100-form-title p-b-34 inputError"> ${errorMessage}</span>
						</c:if>
					  
					  <form action="<c:url value="/addcourse" />" style="margin:10px;">
							<div class="p-t-10">
								<span>
									<button type="submit" class="login100-form-btn">Add Course</button>
								</span>
						</div>
						</form>
							<div class="input100 p-t-20"> 							
								<c:forEach var="listValue" items="${courses}">
								<p class="p-t-10"><h1>${listValue.title}</h1></p>
									<h3>${listValue.courseName}</h3>
										<c:forEach var="instructorList" items="${instructor}">
												<c:if test="${instructorList.instructorId eq listValue.instructorId}">
															By: <c:out value="${instructorList.firstName} ${instructorList.lastName}"/>
										</c:if>
									</c:forEach>
									<p class="p-t-10">${listValue.description}</p>
									<h2>Content:</h2>
									<c:forEach var="listValuecontent" items="${listValue.content}">
									<p class="p-t-10" style="margin-left:10px;">${listValuecontent.title}</p>
									<p class="p-t-10" style="margin-left:10px;">${listValuecontent.content}</p>
									<hr>
								</c:forEach>
								
								<p class="p-t-10">
										<form action="<c:url value="/delete_course_form" />" method="post" style="display: inline">
										<input type="hidden" value="${listValue.courseId}" name="courseId" id="courseId" />
										<button class="formButton">Delete</button></form>
										<form action="<c:url value="/course/${listValue.courseId}" />"  style="display: inline">
										<button class="formButton">Update</button>
										</form>
									</p>
									<hr><hr>
								</c:forEach>
							</div>
							
							<div class="p-b-20">
						</div>
							
					 </div>
				 </div>

			</div>
		</div>
	</div>
</body>
</html>