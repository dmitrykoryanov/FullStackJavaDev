<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="adminapp.DBLayer" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Administration of teachers</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">	
        <meta charset="utf-8">        	
	</head>
	<body>
	
		<h2>Administration of teachers</h2>
		[1] &nbsp;<a href="index.jsp"><b>Back to the main page</b></a><br><br><br>
	
		<!-- Printing the messages, if any -->
		<%= DBLayer.getErrorMessage() %>
	
		<%= DBLayer.getResultMessage() %>
				
    	<h3>Add/Modify a teacher</h3>
    	Select option "Add a new teacher" for adding a new teacher or choose an existing teacher to modify his/her name.
    	<form action="AddModifyServlet" method="post">
			<br><select name="Id">
			<option value="0">Add a new teacher..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("teachers").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>
			&nbsp;&nbsp;&nbsp;Name of the class: <input type="text" placeholder="Type name of a teacher" name="Name">
			<br><br><br><input type="submit" value="Add/Modify teacher">
			<input type="hidden" name="table" value="teachers">
		</form>
	</body>
</html>