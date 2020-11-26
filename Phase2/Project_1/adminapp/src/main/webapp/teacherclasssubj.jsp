<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="adminapp.DBLayer" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Assigning of teachers to classes and subjects</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">	
        <meta charset="utf-8">        	
	</head>
	<body>
	
		<h2>Assigning of teachers to classes and subjects</h2>
		[1] &nbsp;<a href="index.jsp"><b>Back to the main page</b></a><br><br><br>
	
		<!-- Printing the messages, if any -->
		<%= DBLayer.getErrorMessage() %>
	
		<%= DBLayer.getResultMessage() %>
				
    	<h3>Assign teacher to a class or subject</h3>
    	Select teacher, class and subject to make a corresponding assignment.
    	<br>All fields are mandatory in this form. If some fields values are missing, add firstly corresponding values at the corresponding administrative page.
    	<form action="AddModifyServlet" method="post">
			<br>&nbsp;&nbsp;&nbsp;Teacher: <select name="teacherId">
			<option value="0">Choose a teacher..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("teachers").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>
			&nbsp;&nbsp;&nbsp;Class: <select name="classId">
			<option value="0">Choose a class for a teacher (mandatory)..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("classes").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>	
			&nbsp;&nbsp;&nbsp;Subject: <select name="subjectId">
			<option value="0">Choose a subject (mandatory)..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("subjects").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>						
			<br><br><br><input type="submit" value="Assign teacher to a class or subject">
			<input type="hidden" name="table" value="teacherclasssubj">
		</form>
	</body>
</html>