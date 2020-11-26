package adminapp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddModifyServlet")
public class AddModifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("table");
		
		if(action.equals("teacherclasssubj"))
			DBLayer.AssignTeacherToClassSubj(request.getParameter("teacherId"),request.getParameter("classId"), request.getParameter("subjectId"));			
		else	
			DBLayer.AddOrModify(request.getParameter("Id"),request.getParameter("Name"),action, request.getParameter("classId"));
		
		RequestDispatcher rd = request.getRequestDispatcher(action+".jsp");
		rd.forward(request, response);
			
	}

}
