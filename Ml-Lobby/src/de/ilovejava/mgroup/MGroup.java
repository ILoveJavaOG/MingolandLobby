package de.ilovejava.mgroup;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.ilovejava.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class MGroup {
	String Group;
	public MGroup(String group) {
		Group = group;
	}
	
	public boolean isExists() {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group WHERE Name='"+Group+"'");
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {}
		return false;
	}
	
	public Integer getGroupId() {
		Integer i = 0;
		if(isExists()) {
			ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group WHERE Name='"+Group+"'");
			try {
				if(rs.next()) {
					i = rs.getInt("Group_ID");
				}
			}catch(Exception e) {}
		}
		return i;
	}
	
	public boolean delet() {
		Integer i = getDefaultGroup();
		Integer groupID = getGroupId();
		if(groupID != i) {
			ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions ORDER BY Player_Group='"+groupID+"'");
			try {
				while(rs.next()) {
					Utils.getMysql().update("UPDATE Permissions SET Player_Group='"+getDefaultGroup()+"' WHERE UUID='"+rs.getString("UUID")+"'");
				}
				del(groupID);
				return  true;
			}catch(Exception e) {}
		}
		return false;
	}
	
	public void del(Integer groupId) {
		Utils.getMysql().update("DELETE FROM Permissions_Group WHERE Group_ID='"+getGroupId()+"'");
		delPermission(groupId);
	}
	
	public void delPermission(Integer groupId) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Values ORDER BY Group_ID='"+groupId+"'");
		try {
			while (rs.next()) {
				Utils.getMysql().update("DELETE FROM Permissions_Values WHERE Group_ID='"+groupId+"'");
			}
		}catch(Exception e) {}
	}
	
	public String getGroupPrefix() {
		String Prefix = null;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group WHERE Name='"+Group+"'");
		try {
			if(rs.next()) {
				Prefix = ChatColor.translateAlternateColorCodes('&', rs.getString("Prefix"));
			}
		}catch(Exception e) {}
		System.out.println(Group);
		return Prefix;
	}
	
	public void changeDefaultGroup() {
		Integer group = getGroupId();
		Integer defaultGroup = getDefaultGroup();
		Utils.getMysql().update("UPDATE Permissions_Group SET default_group='0' WHERE Group_ID='"+defaultGroup+"'");
		Utils.getMysql().update("UPDATE Permissions_Group SET default_group='1' WHERE Group_ID='"+group+"'");
		setNewDefaultGroupToPlayer(group, defaultGroup);
	}
	
	public void setNewDefaultGroupToPlayer(Integer group, Integer oldGroup) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions ORDER BY Player_Group='"+oldGroup+"'");
		try {
			while (rs.next()) {
				Utils.getMysql().update("UPDATE Permissions SET Player_Group='"+group+"' WHERE UUID='"+rs.getString("UUID")+"'");
			}
		}catch(Exception e) {}
	}
	
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
	
	public ArrayList<String> getGroupPermission(){
		ArrayList<String> Permission = new ArrayList<>();
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Values ORDER BY Group_ID='"+getGroupId()+"'");
		try {
			while(rs.next()) {
				String perm = rs.getString("Permission_Name");
				if(!Permission.contains(perm)) {
					Permission.add(perm);
				}
			}
		}catch(Exception e) {}
		return Permission;
	}
	
	public Integer getTabID() {
		int i = 0;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group WHERE Name='"+Group+"'");
		try {
			if(rs.next()) {
				i = rs.getInt("Tab_ID");
			}
		}catch(Exception e) {}
		return i;
	}
	
	public void setPrefix(String prefix) {
		Utils.getMysql().update("UPDATE Permissions_Group SET Prefix='"+prefix+"' WHERE Name='"+Group+"'");
	}
	
	public void setTabID(Integer i) {
		Utils.getMysql().update("UPDATE Permissions_Group SET Tab_ID='"+i+"' WHERE Name='"+Group+"'");
	}
	
	public void create() {
		Utils.getMysql().update("INSERT INTO Permissions_Group(Prefix,CoverGroups,default_group,Name,Tab_ID) VALUES('null','null','0','"+Group+"','0')");
	}
	
}
