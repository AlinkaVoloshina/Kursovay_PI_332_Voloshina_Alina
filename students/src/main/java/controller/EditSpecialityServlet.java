package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.sql.*;
import dao.ConnectionProperty;
import domain.Speciality;
import java.util.ArrayList;


@WebServlet("/editSpeciality")
public class EditSpecialityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;

	String SELECT_ALL_Speciality = "SELECT id, namespeciality, profile FROM speciality ORDER BY id ASC";
	String SELECT_Speciality_BY_ID = "SELECT id, namespeciality, profile  FROM speciality WHERE id = ?";
	String EDIT_Speciality = "UPDATE speciality SET namespeciality = ?, profile = ?  WHERE id = ?";
	
	ArrayList<Speciality> speciality = new ArrayList<>();
	ArrayList<Speciality> editSpeciality = new ArrayList<>();

	public EditSpecialityServlet() {
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
			ResultSet rs = stmt.executeQuery(SELECT_ALL_Speciality);
			if(rs != null) {
				speciality.clear();
				while (rs.next()) {
					speciality.add(new Speciality(rs.getLong("id"),
							rs.getString("namespeciality"),
							rs.getString("profile")));
				}
				rs.close();
				request.setAttribute("speciality", speciality);
				
			} else {
                System.out.println("Îøèáêà çàãðóçêè speciality");
            }
			try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_Speciality_BY_ID)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    editSpeciality.clear();
                    while (rs.next()) {
                    	editSpeciality.add(new Speciality(rs.getLong("id"),
                                rs.getString("namespeciality"),
                                rs.getString("profile")));
                    }
                    rs.close();
                    request.setAttribute("specialityEdit",
                    		editSpeciality);
                } else {
                    System.out.println("Îøèáêà çàãðóçêè speciality");
                }
            } catch (Exception e) {
                System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userPath = request.getServletPath();
		if("/editSpeciality".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/editSpeciality.jsp")
					.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()){
			String strId = request.getParameter("id");
            Long id = null;
            if (strId != null) {
                id = Long.parseLong(strId);
            }

			String namespeciality = request.getParameter("namespeciality");
			String profile = request.getParameter("profile");
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(EDIT_Speciality)){
				preparedStatement.setString(1, namespeciality);
				preparedStatement.setString(2, profile);
				preparedStatement.setLong(3, id);
				
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
