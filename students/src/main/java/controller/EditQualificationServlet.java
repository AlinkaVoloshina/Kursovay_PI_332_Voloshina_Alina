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

import java.util.List;

import dao.ConnectionProperty;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.sql.*;

@WebServlet("/editQualification")
public class EditQualificationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;
	
	String SELECT_ALL_Qualification = "SELECT id, namequalification  FROM qualification ORDER BY id ASC";
	String SELECT_Qualification_BY_ID = "SELECT id, namequalification FROM qualification  WHERE id = ?";
	String EDIT_Qualification = "UPDATE qualification SET namequalification = ? WHERE id = ?";
	
	ArrayList<Qualification> qualification = new ArrayList<>();
	ArrayList<Qualification> Editqualification = new ArrayList<>();
	
	public EditQualificationServlet() {
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
			ResultSet rs = stmt.executeQuery(SELECT_ALL_Qualification);
			if(rs != null) {
				qualification.clear();
				while (rs.next()) {
					qualification.add(new Qualification(rs.getLong("id"),
							rs.getString("namequalification")));
				}
				rs.close();
				request.setAttribute("qualification", qualification);
			} else {
                System.out.println("Îøèáêà çàãðóçêè visitor");
            }
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_Qualification_BY_ID)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                	Editqualification.clear();
                    while (rs.next()) {
                    	Editqualification.add(new Qualification(rs.getLong("id"),
                                rs.getString("namequalification")));
                    }
                    rs.close();
                    request.setAttribute("qualificationEdit",
                    		Editqualification);
                } else {
                    System.out.println("Îøèáêà çàãðóçêè visitor");
                }
				
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
            System.out.println(e);
        }
			
		userPath = request.getServletPath();
		if("/editQualification".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/editQualification.jsp")
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
	            
	            String namequalification = request.getParameter("namequalification");
			
	            try (PreparedStatement preparedStatement = conn.prepareStatement(EDIT_Qualification)){
				preparedStatement.setString(1, namequalification);
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