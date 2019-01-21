package no.gnome.graphql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

	public static final String postgres_url = "jdbc:postgresql://localhost:5432/graphql";
	public static final String postgres_user = "claus";
	public static final String postgres_pwd = "";

	Connection conn;

	DBConnection() {
	    try {
	    	conn = DriverManager.getConnection(postgres_url, postgres_user, postgres_pwd);
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT VERSION()");

	        if (rs.next()) {
	            System.out.println(rs.getString(1));
	        }
	    } catch (SQLException ex) {
	    }
	}

	public Connection getDBConnection() {
		return conn;
	}
}
