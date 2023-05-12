package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.*;
import dao.ConnectionProperty;
import domain.FormEducation;
import java.util.ArrayList;


@WebServlet("/editFormeducation")
public class EditFormEducationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;
	
	String SELECT_ALL_FormEducation = "SELECT id, nameform FROM formeducation ORDER BY id ASC";
	String SELECT_FormEducation_BY_ID = "SELECT id, nameform FROM formeducation WHERE id = ?";
	String EDIT_FormEducation = "UPDATE formeducation SET nameform = ? WHERE id = ?";
			
	ArrayList<FormEducation> formeducation = new ArrayList<>();
	ArrayList<FormEducation> editFormeducation = new ArrayList<>();
	
	public EditFormEducationServlet()  {
		super();
		prop = new ConnectionProperty();
	}	

	  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
			String strId = request.getParameter("id");
            Long id = null; 
            if (strId != null) {
                id = Long.parseLong(strId);
            }
            
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
			} else {
                System.out.println("Îøèáêà çàãðóçêè formeducation");
            }
			
			 try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_FormEducation_BY_ID)) {
	                preparedStatement.setLong(1, id);
	                rs = preparedStatement.executeQuery();
	                if (rs != null) {
	                	editFormeducation.clear();
	                    while (rs.next()) {
	                    	editFormeducation.add(new FormEducation(rs.getLong("id"),
	                                rs.getString("nameform")));
	                    }
	                    rs.close();
	                    request.setAttribute("formeducationEdit",
	                    		editFormeducation);
	                    
	                } else {
	                    System.out.println("Îøèáêà çàãðóçêè formeducation");
	                }
				
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		userPath = request.getServletPath();
		if("/editFormeducation".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/editFormeducation.jsp")
					.forward(request, response);
		}
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()){
			String strId  = request.getParameter("id");
			Long id = null;
            if (strId != null) {
                id = Long.parseLong(strId);
            }
            
            String nameform = request.getParameter("nameform");
           
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(EDIT_FormEducation)){
				preparedStatement.setString(1, nameform);
				preparedStatement.setLong(2, id);
				
				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		doGet(request, response);
	}
}
    