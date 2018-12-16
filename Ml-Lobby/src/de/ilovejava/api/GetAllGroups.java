package de.ilovejava.api;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.ilovejava.utils.Utils;

public class GetAllGroups {
	ArrayList<String> groups = new ArrayList<>();
	public GetAllGroups() {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group ORDER BY Group_ID");
		try {
			while (rs.next()) {
				groups.add(rs.getString("Name"));
			}
		}catch(Exception e) {}
	}
	
	public ArrayList<String> getGroups(){
		return groups;
	}
}
