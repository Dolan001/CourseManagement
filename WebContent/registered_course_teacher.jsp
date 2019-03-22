<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %> 


<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<%@ page import="schedule.New" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


 
<jsp:useBean id="New" class="schedule.New"   scope="application" />  
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Registered Courses</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>

	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">

	<link rel="stylesheet" type="text/css" href="vendor/select2/select3.min.css">

	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">

	<link rel="stylesheet" type="text/css" href="css/showinfoutil.css">
	<link rel="stylesheet" type="text/css" href="css/showinfo.css">

</head>
<body>
	
	<div class="limiter">
	
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<table data-vertable="ver1">
						<thead>
							<tr class="row100 head">
								<th class="column100 column2" data-column="column2">Course Code</th>
								<th class="column100 column3" data-column="column3">Course Name</th>
								<th class="column100 column4" data-column="column4">Teacher</th>
								<th class="column100 column4" data-column="column4">Semester</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items}" var="item">
								<tr class="row100">
									<td class="column100 column1" data-column="column1">${item.code}</td>
									<td class="column100 column2" data-column="column2">${item.name}</td>
									<td class="column100 column3" data-column="column3">${item.sem}</td>
									<td class="column100 column4" data-column="column4">${item.current}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	

<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select3.min.js"></script>
<!--===============================================================================================-->
	<script src="js/showinfo.js"></script>

</body>
</html>