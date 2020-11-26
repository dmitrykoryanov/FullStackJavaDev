<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="adminapp.DBLayer" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Administration of students</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">	
        <meta charset="utf-8">        	
	</head>
	<body>
	
		<h2>Administration of students' classes</h2>
		[1] &nbsp;<a href="index.jsp"><b>Back to the main page</b></a><br><br><br>
	
		<!-- Printing the messages, if any -->
		<%= DBLayer.getErrorMessage() %>
	
		<%= DBLayer.getResultMessage() %>
				
    	<h3>Add/Modify a student</h3>
    	Select option "Add a new student" for adding a new student or choose an existing student to modify his/her name or class.
    	<br>Choosing a class for a student is mandatory. If there are no classes available, add firstly at least one class.
    	<form action="AddModifyServlet" method="post">
			<br><select name="Id">
			<option value="0">Add a new student..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("students").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>
			&nbsp;&nbsp;&nbsp;Name of a student: <input type="text" placeholder="Type name of a student" name="Name">
			&nbsp;&nbsp;&nbsp;Class: <select name="classId">
			<option value="0">Choose a class for a student (mandatory)..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("classes").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>			
			<br><br><br><input type="submit" value="Add/Modify student">
			<input type="hidden" name="table" value="students">
		</form>
	</body>
</html>