package de.ilovejava.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQL {
	 private String HOST = "";
     private String DATABASE = "";
     private String USER = "";
     private String PASSWORD = "";
     private Integer Port = 0;
     private Connection con;
    
     public MySQL(String host, String database, String user, String password, Integer port) {
             this.HOST = host;
             this.DATABASE = database;
             this.USER = user;
             this.PASSWORD = password;
             this.Port = port;
             connect();
     }

     public void connect() {
             try {
                     con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":"+Port+"/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
                     System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
                     System.out.println("Loading mysql files");
                     System.out.println("Loading complet willkommen");
             } catch (SQLException e) {
                     System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
             }
     }
    
     public void close() {
             try {
                     if(con != null) {
                             con.close();
                             System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
                     }
             } catch (SQLException e) {
                     System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
             }
     }
    
     public void update(String qry) {
             try {
                     Statement st = con.createStatement();
                     st.executeUpdate(qry);
                     st.close();
             } catch (SQLException e) {
                     connect();
                     System.err.println(e);
             }
     }
    
     public ResultSet query(String qry) {
             ResultSet rs = null;
            
             try {
                     Statement st = con.createStatement();
                     rs = st.executeQuery(qry);
             } catch (SQLException e) {
                     connect();
                     System.err.println(e);
             }
             return rs;
     }
     
     public boolean isConnect() {
    	 try {
			return con.isClosed();
		} catch (SQLException e) {}
    	return false;
     }
}
