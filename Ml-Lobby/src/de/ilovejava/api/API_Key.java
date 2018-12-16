package de.ilovejava.api;

import java.sql.ResultSet;

import de.ilovejava.utils.Utils;

public class API_Key {
	public API_Key() {}
	
	public boolean isKey(String key) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_AdminKey WHERE Admin_Key='"+key+"'");
		try {if(rs.next()) {return true;}}catch(Exception e) {}
		return false;
	}
	
	public boolean isKeyCreate() {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_AdminKey WHERE Key_Create='1'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {}
		return false;
	}
}
