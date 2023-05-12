package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import dao.ConnectionProperty;
import domain.FormEducation;
import java.util.ArrayList;

/**
 * Servlet implementation class FormEducationServlet
 */
@WebServlet("/formeducation")
public class FormEducationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;
	
	String SELECT_ALL_FormEducation = "SELECT id, nameform FROM formeducation";
	String INSERT_FormEducation = "INSERT INTO formeducation (nameform) VALUES (?)";
	
	ArrayList<FormEducation> formeducation = new ArrayList<>();
	
	public FormEducationServlet()  {
		prop = new ConnectionProperty();
	}	

	  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_FormEducation);
			if(rs != null) {
				formeducation.clear();
				while (rs.next()) {
					formeducation.add(new FormEducation(rs.getLong("id"),
							rs.getString("nameform")));
				}
				rs.close();
				request.setAttribute("formeducation", formeducation);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userPath = request.getServletPath();
		if("/formeducation".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/formeducation.jsp")
					.forward(request, response);
		}
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()){
			String nameform = request.getParameter("nameform");
			FormEducation newFormEducation = new FormEducation(nameform);
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_FormEducation)){
				preparedStatement.setString(1, newFormEducation.getNameform());
				
				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/formeducation.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}
    
//    private void listFormEducation(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//    	
//		List<FormEducation> listFormEducation = daoFormEducation.selectAllFormEducation();
//		request.setAttribute("listFormEducation", listFormEducation);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/formeducation.jsp");
//		dispatcher.forward(request, response);
//	}
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//}

          
//        String path = "/WEB-INF/view/formeducation.jsp";
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
//        requestDispatcher.forward(request, response);
//    }
//}