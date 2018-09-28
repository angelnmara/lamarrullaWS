package com.mx.lamarrulla.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import com.mx.lamarrulla.interfaces.iAPI;

public class implementAPI implements iAPI {
	
	//private JSONObject jsonObject;
	
	private String consulta;
	
	private String stJS="";
	
//	public JSONObject getJsonObject() {
//		return jsonObject;
//	}
//
//	public void setJsonObject(JSONObject jsonObject) {
//		this.jsonObject = jsonObject;
//	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	public String getstJS() {
		return stJS;
	}
	
	public void setstJS(String stJS) {
		this.stJS = stJS;
	}

	public void ejecutaAPI() {
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/lamarrulladb", "postgres",
					"maradr");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");			
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			try {				
				System.out.println("You made it, take control your database now!");			
				Statement st = connection.createStatement();
				//ResultSet rs = st.executeQuery("select fnAPI('tbcatobj');");
				//ResultSet rs = st.executeQuery("select * from fntablaamortcs(20000, 10, 24);");
				ResultSet rs = st.executeQuery(consulta);
				while(rs.next()) {
					System.out.print("columna uno regresada ");				
					stJS+=rs.getString(1);				
				}
				System.out.println(stJS);
				//jsonObject = new JSONObject(stJS);					
				rs.close();
				st.close();
			}catch(Exception ex) {
				System.out.println("ocurrio un error: " +ex.getMessage());
			}			
		} else {
			System.out.println("Failed to make connection!");
		}
	}	
}
