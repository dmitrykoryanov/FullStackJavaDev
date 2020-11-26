package adminapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBLayer {

	static Connection dbCon;
	static Statement stmt;

	public static String errorMessage, resultMessage;
	
	//map of tables with fields
	static Map<String, String[]> actions = new HashMap<>();
	
	//Initializing block
	static {
		
		actions.put("classes", new String[] {"classId", "className"});
		actions.put("students", new String[] {"stdId", "stdName"});
		actions.put("subjects", new String[] {"subjId", "subjName"});
		actions.put("teachers", new String[] {"teacherId", "teacherName"});
		
		try {
				//Establishing the connection, obtaining the statement
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/academyadmin","root","1111");			
				stmt = dbCon.createStatement();
				
			} catch (ClassNotFoundException | SQLException e) {
				//Setting the error message
				errorMessage = "Error while trying to the connection with the database: "+e.getMessage();
		}	
	}
	
	//returns the current message
	public static String getResultMessage() {
		String m;
		if (resultMessage != null) {
			m = "<h3 style='color:red;'>"+resultMessage+"</h3>";
			resultMessage = null;
		}	
		else m = "";
	
		return m;
	}

	//returns the error message
	public static String getErrorMessage() {
		if (errorMessage != null)
			return "<h3 style='color:red;'>"+errorMessage+"</h3>";
		else 
			return "";
	}

    public static void AddOrModify(String Id, String Name, String table, String classId) {
    	
    	String query;
    	int result;
    	
    	//it is mandatory to choose a class for a student
    	if (table.equals("students") && classId.equals("0")) {
    		resultMessage = "Please choose a class for a student. A student can't be added without a class!";
    		return;
    	}
    	
    	//create the query for tables classes, teachers, subjects
    	if (!table.equals("students")) {
	    	if(Id.equals("0")) {
	    		query = "insert into "+table+"("+actions.get(table)[1]+") values('"+Name+"')";
	    		resultMessage = " inserted!";
	    	}	
	    	else {
	    		query = "update "+table+" set "+actions.get(table)[1]+" = '"+Name+"' where "+actions.get(table)[0]+" = "+Id;
				resultMessage = " updated!";
	    	}
	    //create the query for students table	
    	} else {
	    	if(Id.equals("0")) {
	    		query = "insert into "+table+"("+actions.get(table)[1]+", stdClassId) values('"+Name+"', '"+classId+"')";
	    		resultMessage = " inserted!";
	    	}	
	    	else {
	    		query = "update "+table+" set "+actions.get(table)[1]+" = '"+Name+"', stdClassId = "+classId+" where "+actions.get(table)[0]+" = "+Id;
				resultMessage = " updated!";
	    	}    		
    	}
    	
    	try {
			result = stmt.executeUpdate(query);
			resultMessage = result+" row has been successfully "+resultMessage;
		} catch (SQLException e) {
			resultMessage = "Error during DML operation on table "+table+": "+e.getMessage();
		}
    	
    }
 
    public static void AssignTeacherToClassSubj(String teacherId, String classId, String subjectId) {
    	
    	String query;
    	int result;
    	ResultSet rs;
    	
    	//it is mandatory to choose a class for a student
    	if (teacherId.equals("0") || classId.equals("0") || subjectId.equals("0")) {
    		resultMessage = "While assigning teacher to a class and subjects it is mandatory to select the value for all the fields in the form!";
    		return;
    	}
    	
    	//firstly we'll check if the corresponding combination already exists
    	query = "select * from teacherclasssubj where teacherId = "+teacherId+" and classId = "+classId+" and subjId = "+subjectId;
    	
		try {
			rs = stmt.executeQuery(query);
			
			//if the suggested combination already exists - exit from the function with a corresponding message
			if(rs.next()) {
	    		resultMessage = "The corresponding teacher has been already assigned to chosen class and subject!";
	    		rs.close();
	    		return;
			}				
		} catch (SQLException e) {
			resultMessage = "Error while trying to fetch a row from the table teacherclasssubj: "+e.getMessage();
		}

		//query for insertion
		query = "insert into teacherclasssubj(teacherId, classId, subjId) values("+teacherId+", "+classId+", "+subjectId+")";
    	
    	try {
			result = stmt.executeUpdate(query);
			resultMessage = result+" row has been successfully inserted!";
		} catch (SQLException e) {
			resultMessage = "Error during DML operation on table teacherclasssubj: "+e.getMessage();
		}
	
    	
    }
    
    public static Map<Integer, String> getListByTable(String tableName){
    	
    	Map<Integer, String> r = new LinkedHashMap<>();
    	String query;
    	ResultSet rs;
    	
    	query = "select * from "+tableName+" order by "+actions.get(tableName)[0];
    	
    	try {
    		rs = stmt.executeQuery(query);
    		
			while(rs.next()) 
				r.put(rs.getInt(1), rs.getString(2));
			
			rs.close();
			
		} catch (SQLException e) {
			resultMessage = "Error while trying to fetch the list of rows from the table "+tableName+": "+e.getMessage();
		}
    	
    	return r;
    }

    public static Map<String, String> getTeacherSubjectsListByClass(Integer classId){
    	
    	Map<String, String> r = new LinkedHashMap<>();
    	
    	String query;
    	ResultSet rs;
    	
    	query = "select t.teacherName, s.subjName from teacherclasssubj tcs, classes c, teachers t, subjects s where tcs.classId = c.classId and tcs.subjId = s.subjId and tcs.teacherId = t.teacherId and c.classId = "+classId+ " order by t.teacherName, s.subjName";
    	
    	try {
    		rs = stmt.executeQuery(query);
    		
			while(rs.next()) 
				r.put(rs.getString(1), rs.getString(2));
			
			rs.close();
			
		} catch (SQLException e) {
			resultMessage = "Error while trying to fetch the list of rows from the table teacherclasssubj: "+e.getMessage();
		}
    	
    	return r;
    }
}
