package com.project.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.databaseconnection.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//This servlet handles user authentication based on session existence
//and database verification.

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 // Check if the user has an active session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            // User is already authenticated, redirect to welcome page
            response.sendRedirect("/views/welcome.jsp");
            return;
        }

        // Retrieve login credentials from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Database verification - perform authentication check here
        // For demonstration, let's assume a method authenticateUser(username, password) returns true if user exists in the database
        boolean authenticated = authenticateUser(username, password);

        if (authenticated) {
            // Set username attribute in session to mark user as authenticated
            session = request.getSession(true);
            session.setAttribute("username", username);
            
         // Check if the user is an admin
            if (username.equals("admin")) {
                session.setAttribute("isAdmin", true);
            }
            
            // Redirect to welcome page
            response.sendRedirect("welcome.jsp");
        } else {
            // Authentication failed, redirect to error page
        	 request.setAttribute("errorMessage", "Invalid credentials. Please try again.");
             request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
	}
	 private boolean authenticateUser(String username, String password) {
		 boolean authenticated = false;
		 try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
	            statement.setString(1, username);
	            statement.setString(2, password);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                // User with provided username and password exists in the database
	                authenticated = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return authenticated;
	    }

}

//In the Authentication Servlet example provided, there is no doGet method because the servlet is primarily designed to handle 
//form submissions for user authentication.