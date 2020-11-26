<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="adminapp.DBLayer" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Class Report</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">	
        <meta charset="utf-8">        	
	</head>
	<body>
	
		<h2>Class Report</h2>
		[1] &nbsp;<a href="index.jsp"><b>Back to the main page</b></a><br><br><br>
	
		<!-- Printing the messages, if any -->
		<%= DBLayer.getErrorMessage() %>

		<table>
			<thead style="border: 1px;">
				<tr>
					<th>Class Name</th><th>Teacher & Subject</th><th>Students in Class</th>
				</tr>
				</thead>
				<tbody>
				<% 
					for(Map.Entry<Integer, String> e : DBLayer.getListByTable("classes").entrySet()) {
						out.println("<tr><td align='center'>"+e.getValue()+"</td><td>");
						
						for (Map.Entry<String, String> ts : DBLayer.getTeacherSubjectsListByClass(e.getKey()).entrySet())
							out.println(ts.getKey()+" : "+ts.getValue()+"<br>");						
						out.println("</td><td>");
						for(Map.Entry<Integer, String> s : DBLayer.getListByTable("students").entrySet())
							out.println(s.getKey()+" : "+s.getValue()+"<br>");													
						out.println("</td></tr>");
						
					}	
				%>
				</tbody>
		</table>
	</body>
</html>