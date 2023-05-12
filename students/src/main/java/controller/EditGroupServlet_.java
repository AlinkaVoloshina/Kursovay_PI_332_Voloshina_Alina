package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.util.List;
import domain.FormEducation;
import domain.Group;
import domain.Qualification;
import domain.Speciality;
import dao.ConnectionProperty;
import java.util.ArrayList;
import java.sql.*;

import domain.Speciality;
import domain.Qualification;
import domain.FormEducation;
import domain.Group;

@WebServlet("/editGroup")
public class EditGroupServlet_ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	
	String SELECT_ALL_Speciality = "SELECT * FROM speciality";
	String SELECT_ALL_Qualification = "SELECT * FROM qualification";
	String SELECT_ALL_FormEducation = "SELECT * FROM formeducation";
	String SELECT_ALL_Group = "SELECT * FROM public.group";
	String SELECT_Group_BY_ID = "SELECT id, faculty, name, course, countstudent, countsubgrroup, idspeciality, idqualification, idformeducation FROM public.group WHERE id = ?";
	String EDIT_Group = "UPDATE public.group SET idspeciality = ?, idqualification = ?, idformeducation = ?, faculty = ?, name = ?, course = ?, countstudent = ?, countsubgrroup = ? WHERE id = ?";
	
	ArrayList<Speciality> speciality = new ArrayList<>();
	ArrayList<Qualification> qualification = new ArrayList<>();
	ArrayList<FormEducation> formeducation = new ArrayList<>();
	ArrayList<Group> group = new ArrayList<>();
	ArrayList<Group> editGroup = new ArrayList<>();
	
	//String userPath;
	public EditGroupServlet_() {
		 super();
		prop = new ConnectionProperty();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();
		
		try (Connection conn = builder.getConnection()) {
			String strId = request.getParameter("id");
            Long idGroupSelected = null;
            if (strId != null) {
                idGroupSelected = Long.parseLong(strId);
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
			long idspeciality;
			
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_Qualification);
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
			long idqualification;
			
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_FormEducation);
			if(rs != null) {
				formeducation.clear();
				while (rs.next()) {
					formeducation.add(new FormEducation(rs.getLong("id"),
							rs.getString("nameform")));
				}
				rs.close();
				request.setAttribute("formeducation", formeducation);
			}
			long idformeducation;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_Group);
			if(rs != null) {
				group.clear();
				while (rs.next()) {
					idspeciality = rs.getLong("idspeciality");
					idqualification = rs.getLong("idqualification");
					idformeducation = rs.getLong("Idformeducation");
					group.add(new Group(
							rs.getLong("id"),
							rs.getString("faculty"),
							rs.getString("name"),
							rs.getInt("course"),
							rs.getInt("countstudent"),
							rs.getInt("countsubgrroup"),
							idspeciality,
							findByIdSpeciality(idspeciality, speciality),
							idqualification,
							findByIdQualification(idqualification, qualification),
							idformeducation,
							findByIdFormEducation(idformeducation, formeducation)));
				}
				rs.close();
				request.setAttribute("group", group);
			 } else {
	                System.out.println("Îøèáêà çàãðóçêè group");
	            }
		    try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_Group_BY_ID)) {
                preparedStatement.setLong(1, idGroupSelected);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    editGroup.clear();
                    while (rs.next()) {
                    	editGroup.add(new Group(rs.getLong("id"),
                                rs.getString("faculty"),
                                rs.getString("name"),
                                rs.getInt("course"),
                                rs.getInt("countstudent"),
                                rs.getInt("countsubgrroup"),
                                rs.getLong("idspeciality"),
                                rs.getLong("idspeciality"),
                                rs.getLong("idformeducation")));
                    }
                    rs.close();
                    request.setAttribute("groupEdit", editGroup);
                } else {
                    System.out.println("Îøèáêà çàãðóçêè group");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
			
		} catch (Exception e) {
			System.out.println(e);
		}
		String userPath = request.getServletPath();
		if("/editGroup".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/editGroup.jsp")
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
			
			
			String faculty = request.getParameter("faculty");
			String name = request.getParameter("name");
			String course = request.getParameter("course");
			String countstudent = request.getParameter("countstudent");
			String countsubgrroup = request.getParameter("countsubgrroup");
			
			String speciality = request.getParameter("speciality");
			int index1 = speciality.indexOf('=');
			int index2 = speciality.indexOf(",");
			String r1 = speciality.substring(index1+1, index2);
			Long idspeciality = Long.parseLong(r1.trim());
			
			String qualification = request.getParameter("qualification");
			int index3 = qualification.indexOf('=');
			int index4 = qualification.indexOf(",");
			String r2 = qualification.substring(index3+1, index4);
			Long idqualification = Long.parseLong(r2.trim());
			
			String formeducation = request.getParameter("formeducation");
			int index5 = formeducation.indexOf('=');
			int index6 = formeducation.indexOf(",");
			String r3 = formeducation.substring(index5+1, index6);
			Long idformeducation = Long.parseLong(r3.trim());
			
			PreparedStatement preparedStatement = conn.prepareStatement(EDIT_Group);
				preparedStatement.setLong(1, idspeciality);
				preparedStatement.setLong(2, idqualification);
				preparedStatement.setLong(3, idformeducation);
				preparedStatement.setString(4, faculty);
				preparedStatement.setString(5, name);
				preparedStatement.setInt(6, Integer.parseInt(course));
				preparedStatement.setInt(7, Integer.parseInt(countstudent));
				preparedStatement.setInt(8, Integer.parseInt(countsubgrroup));
				preparedStatement.setLong(9, id);
				
				int rows = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
	
		doGet(request, response);
	}
	
	private Speciality findByIdSpeciality(Long id, ArrayList<Speciality> speciality) {
		if (speciality != null) {
			for (Speciality r: speciality) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
	
	private Qualification findByIdQualification(Long id, ArrayList<Qualification> qualification) {
		if (qualification != null) {
			for (Qualification r: qualification) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
	private FormEducation findByIdFormEducation(Long id, ArrayList<FormEducation> formeducation) {
		if (formeducation != null) {
			for (FormEducation r: formeducation) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
}
	
