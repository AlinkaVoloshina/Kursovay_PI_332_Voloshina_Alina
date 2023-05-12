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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import dao.ConnectionProperty;
import domain.Speciality;
import java.util.ArrayList;

/**
 * Servlet implementation class SpecialityServlet
 */
@WebServlet("/speciality")
public class SpecialityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;

	String SELECT_ALL_Speciality = "SELECT id, namespeciality, profile FROM speciality";
	String INSERT_Speciality = "INSERT INTO speciality (namespeciality, profile) VALUES (?, ?)";
	
	ArrayList<Speciality> speciality = new ArrayList<>();
	
	public SpecialityServlet() {
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
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
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userPath = request.getServletPath();
		if("/speciality".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/speciality.jsp")
					.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()){
			String namespeciality = request.getParameter("namespeciality");
			String profile = request.getParameter("profile");
			Speciality newSpeciality = new Speciality(namespeciality, profile);
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_Speciality)){
				preparedStatement.setString(1, newSpeciality.getNamespeciality());
				preparedStatement.setString(2, newSpeciality.getProfile());
				
				int result = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/speciality.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}
