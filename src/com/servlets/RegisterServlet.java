package com.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;

import java.sql.*;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        
        //To fetch the data 
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("mobile");
        String password = request.getParameter("password");
        
        Connection con = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stuapp","root","user");
        	PreparedStatement ps = con.prepareStatement("insert into student(name,email,phone,password) values(?,?,?,?)");
        	ps.setString(1,name);
        	ps.setString(2,email);
        	ps.setString(3,phone);
        	ps.setString(4,password);
        	
        	int i=ps.executeUpdate(); 
        	
        	if(i>0) {
        		RequestDispatcher rd = request.getRequestDispatcher("success.html");
        		rd.forward(request, response);
        	}else {
        		RequestDispatcher rd = request.getRequestDispatcher("Failed.html");
        		rd.forward(request, response);
        	}
        	con.close();	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
    
	}

}
