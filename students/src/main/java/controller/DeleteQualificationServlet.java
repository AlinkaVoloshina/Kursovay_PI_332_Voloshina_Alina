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
import domain.Qualification;
import domain.Speciality;
import java.util.ArrayList;


@WebServlet("/deleteQualification")
public class DeleteQualificationServlet extends HttpServlet {
		
		private static final long serialVersionUID = 1L;
		ConnectionProperty prop;
		String userPath;
		
		String SELECT_ALL_Qualification = "SELECT id, namequalification  FROM qualification ORDER BY id ASC";
		String SELECT_Qualification_BY_ID = "SELECT id, namequalification FROM qualification  WHERE id = ?";
		String DELETE_Qualification = "DELETE FROM qualification  WHERE id = ?";
		
		ArrayList<Qualification> qualification = new ArrayList<>();
		ArrayList<Qualification> deleteQualification = new ArrayList<>();
		
		public DeleteQualificationServlet() {
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
	                System.out.println("Îøèáêà çàãðóçêè qualification");
	            }
				
				try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_Qualification_BY_ID)) {
	                preparedStatement.setLong(1, id);
	                rs = preparedStatement.executeQuery();
	                if (rs != null) {
	                	deleteQualification.clear();
	                    while (rs.next()) {
	                    	deleteQualification.add(new Qualification(rs.getLong("id"),
	                                rs.getString("namequalification")));
	                    }
	                    rs.close();
	                    request.setAttribute("qualificationDelete",
	                    		deleteQualification);
	                } else {
	                    System.out.println("Îøèáêà çàãðóçêè qualification");
	                }
					
				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
	            System.out.println(e);
	        }
				
			userPath = request.getServletPath();
			if("/deleteQualification".equals(userPath)){
				request.getRequestDispatcher("/WEB-INF/view/deleteQualification.jsp")
						.forward(request, response);
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ConnectionProperty builder = new ConnectionProperty();
			
			try (Connection conn = builder.getConnection()){
				 Long id = Long.parseLong(request.getParameter("id"));
		            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_Qualification)){
					preparedStatement.setLong(1, id);
					
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