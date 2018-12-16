package de.ilovejava.mperms;

import java.sql.ResultSet;

import de.ilovejava.utils.Utils;

public class MPerms {
	public MPerms() {}
	
	
	public Integer getDefaultGroup() {
		Integer i = 0;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group WHERE default_group='1'");
		try {
			if(rs.next()) {
				i = rs.getInt("Group_ID");
			}
		}catch(Exception e) {}
		return i;
	}
	
	public boolean isExists(String Permission) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Values WHERE Permission_Name='"+Permission+"'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {}
		return false;
	}
	
	public boolean removePermissionFromGroup(String perm, Integer group) {
		if(isExists(perm)) {
			Utils.getMysql().update("DELETE FROM Permissions_Values WHERE Permission_Name='"+perm+"' AND Group_ID='"+group+"'");
			return true;
		}
		return false;
	}
	
	public boolean createPermission(String Perm,Integer Group) {
		Utils.getMysql().update("INSERT INTO Permissions_Values(Group_ID,Permission_Name) VALUES('"+Group+"','"+Perm+"')");
		return true;
	}
}
