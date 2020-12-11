package com.elena.Warehouse1;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import javax.swing.JOptionPane;
	
	public class MySqlConnect {
	    Connection conn = null;
	    public static Connection ConnectDb(){
	    
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse1", "root", "");
	            JOptionPane.showMessageDialog(null, "Connection Established");
	            return conn;
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	            return null;
	        }  
	    }
	}

