<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="adminapp.DBLayer" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Administration of subjects</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">	
        <meta charset="utf-8">        	
	</head>
	<body>
	
		<h2>Administration of subjects</h2>
		[1] &nbsp;<a href="index.jsp"><b>Back to the main page</b></a><br><br><br>
	
		<!-- Printing the messages, if any -->
		<%= DBLayer.getErrorMessage() %>
	
		<%= DBLayer.getResultMessage() %>
				
    	<h3>Add/Modify a subjects</h3>
    	Select option "Add a new subject" for adding a new subject or choose an existing subject to modify the name of it.
    	<form action="AddModifyServlet" method="post">
			<br><select name="Id">
			<option value="0">Add a new subject..</option>
			<% 
				for(Map.Entry<Integer, String> e : DBLayer.getListByTable("subjects").entrySet())
				out.println("<option value=\""+e.getKey()+"\">"+e.getValue()+"</option>");
		
			%>
			</select>
			&nbsp;&nbsp;&nbsp;Name of the subject: <input type="text" placeholder="Type name of a subject" name="Name">
			<br><br><br><input type="submit" value="Add/Modify subject">
			<input type="hidden" name="table" value="subjects">
		</form>
	</body>
</html>