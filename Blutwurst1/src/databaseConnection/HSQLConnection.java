package databaseConnection;

import java.sql.*;

public class HSQLConnection {
	public HSQLConnection(){
		try{
			Class.forName("org.hsqldb.jdbcDriver");
		}catch(ClassNotFoundException ex){
			System.out.println("Treiber nicht gefunden!");
			return;
		}
		
		Connection con = null;
		
		try{
			con = DriverManager.getConnection("jdbc:hsqldb:file:home/blutwurst/trash/hsql; shutdown=true", "root", "blutwurst1");
			Statement state = con.createStatement();
			
			String query = "SELECT * FROM customer";
			ResultSet rs = state.executeQuery(query);
			
			while(rs.next()){
				String id = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				System.out.println(id + ", " + firstName + ", " + lastName);
			}
			
			rs.close();
			state.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args){
		new HSQLConnection();
	}
}
