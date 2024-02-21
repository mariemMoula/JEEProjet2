package com.project.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.databaseconnection.DatabaseConnection;
import com.project.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Database query to retrieve list of users
        List<User> userList = getUsersFromDatabase();

        // Forward list of users to JSP for display
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/views/userList.jsp").forward(request, response);

	}
	 // Method to retrieve users from the database
	 private List<User> getUsersFromDatabase() {
		 List<User> userList = new ArrayList<>();
		 	//Connecting to the database and Performing database query to retrieve list of users
		 try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                User user = new User();
	                user.setId(resultSet.getInt("id"));
	                user.setUsername(resultSet.getString("username"));
	                // Set other user attributes as needed
	                userList.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle database access error
	        }
	        // Return the list of users
	        return userList; // Placeholder for database query
	    }

}
//serListServlet is responsible for displaying a list of users,
//we only handle  HTTP GET requests.