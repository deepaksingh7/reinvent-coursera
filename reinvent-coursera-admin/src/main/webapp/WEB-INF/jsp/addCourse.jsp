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
<title>Coursera Admin: Add Course</title>
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
					  <div class="login100-form-title p-b-20 p-t-20" style="padding-bottom:-20px">Add Course</div>
					  <form action="<c:url value="/add_course_form" />" method="post" style="margin: 10px;">
							<div class="input100 p-t-20"> 							
									<p class="wrap-input100 m-b-20">
									<input type="text" id="courseName" name="courseName" class="input100" placeholder="courseName">
									  </p> 
									   <p class="wrap-input100 m-b-20">
									<input type="text" id="title" name="title" class="input100" placeholder="title">
									  </p> 
									  <p class="wrap-input100 m-b-20">
									<input type="text" id="description" name="description" class="input100" placeholder="description">
									  </p> 
									  <p>
											  <div class="form-group row">
												<select class="input100 m-b-20" name="instructorId" id="instructorId" class="input100">
							        				<c:forEach var="listInstructorValue" items="${instructor}">
							        					<option value="${listInstructorValue.instructorId}">${listInstructorValue.firstName} ${listInstructorValue.lastName} </option>
							        				</c:forEach>
												</select>
											</div> 
									  </p>
									 
									  
									  
									    <div class="form-group row">
										<select class="input100 m-b-20" name="categoryId" id="categoryId" class="input100">
					        				<c:forEach var="listCategoryValue" items="${category}">
					        					<option value="${listCategoryValue.categoryId}">${listCategoryValue.categoryName}</option>
					        				</c:forEach>
										</select>
									</div> 
									
									 <p class="m-b-20"> Content 1: </p>
									  <p class="wrap-input100 m-b-20">
									<input type="text" id="contenttitle_0" name="contenttitle_0" class="input100" placeholder="content title">
									  </p> 
									  
									    <p class="wrap-input100 m-b-20">
									<input type="text" id="content_0" name="content_0" class="input100" placeholder="content">
									  </p> 
									  
									  <hr>
									   <p class="m-b-20"> Content 2: </p>
									     <p class="wrap-input100 m-b-20">
									<input type="text" id="contenttitle_1" name="contenttitle_1" class="input100" placeholder="content title">
									  </p> 
									    <p class="wrap-input100 m-b-20">
									<input type="text" id="content_1" name="content_1" class="input100" placeholder="content">
									  </p> 
									  
									  <hr>
									  
									   <p class="m-b-20"> Content 3: </p>
									     <p class="wrap-input100 m-b-20">
									<input type="text" id="contenttitle_2" name="contenttitle_2" class="input100" placeholder="content title">
									  </p> 
									    <p class="wrap-input100 m-b-20">
									<input type="text" id="content_2" name="content_2" class="input100" placeholder="content">
									  </p> 
									
							</div>
							
							<div class="p-t-10">
								<span>
									<button type="submit" class="login100-form-btn">Add</button>
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