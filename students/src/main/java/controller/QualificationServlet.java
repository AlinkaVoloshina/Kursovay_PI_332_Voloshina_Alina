package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import domain.Qualification;
import java.sql.SQLException;
import java.util.List;

import dao.ConnectionProperty;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.sql.*;

/**
 * Servlet implementation class QualificationServlet
 */
@WebServlet("/qualification")
public class QualificationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;
	
	String SELECT_ALL_Qualification = "SELECT id, namequalification  FROM qualification";
	String INSERT_Qualification = "INSERT INTO qualification (namequalification) VALUES (?)";
	ArrayList<Qualification> qualification = new ArrayList<>();
	
	public QualificationServlet() {
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_Qualification);
			if(rs != null) {
				qualification.clear();
				while (rs.next()) {
					qualification.add(new Qualification(rs.getLong("id"),
							rs.getString("namequalification")));
				}
				rs.close();
				request.setAttribute("qualification", qualification);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		userPath = request.getServletPath();
		if("/qualification".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/qualification.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()){
			String namequalification = request.getParameter("namequalification");
			Qualification newQualification = new Qualification(namequalification);
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_Qualification)){
				preparedStatement.setString(1, newQualification.getNamequalification());
				
				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/qualification.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}