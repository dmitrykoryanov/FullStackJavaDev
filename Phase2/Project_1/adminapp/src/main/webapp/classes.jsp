<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="adminapp.DBLayer" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Administration of classes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">	
        <meta charset="utf-8">        	
	</head>
	<body>
	
		<h2>Administration of students' classes</h2>
		[1] &nbsp;<a href="index.jsp"><b>Back to the main page</b></a><br><br><br>
	
		<!-- Printing the messages, if any -->
		<%= DBLayer.getErrorMessage() %>
	
		<%= DBLayer.getResultMessage() %>
				
    	<h3>Add/Modify a class</h3>
    	Select option "Add a new class" for adding a new class or choose an existing class to modify the name of it.
    	<form action="AddModifyServlet" method="post">
			<br><select name="Id">
			<option value="0">Add a new class..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("classes").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>
			&nbsp;&nbsp;&nbsp;Name of the class: <input type="text" placeholder="Type name of a class" name="Name">
			<br><br><br><input type="submit" value="Add/Modify class">
			<input type="hidden" name="table" value="classes">
		</form>
	</body>
</html>