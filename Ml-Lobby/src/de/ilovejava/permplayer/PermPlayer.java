package de.ilovejava.permplayer;

import java.sql.ResultSet;

import org.bukkit.entity.Player;

import de.ilovejava.mperms.MPerms;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;

public class PermPlayer {
	Player a;
	String uuid;
	String Query = "SELECT * FROM Permissions WHERE UUID='%'";
	public PermPlayer(Player p) {
		a = p;
		uuid = uuidfetcher.getUUID(p.getName()).toString();
		Query = Query.replace("%", uuidfetcher.getUUID(p.getName()).toString());
	}
	
	public PermPlayer(String p) {
		uuid = uuidfetcher.getUUID(p).toString();
		Query = Query.replace("%", uuid);
	}
	
	public boolean isExists() {
		ResultSet rs = Utils.getMysql().query(Query);
		try {
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {}
		return false;
	}
	
	public void setDefault() {
		MPerms mp = new MPerms();
		Utils.getMysql().update("UPDATE Permissions SET Player_Group='"+mp.getDefaultGroup()+"' WHERE UUID='"+uuid+"'");
	}
	
	public void setPlayerGroup(Integer group) {
		Utils.getMysql().update("UPDATE Permissions SET Player_Group='"+group+"' WHERE UUID='"+uuid+"'");
	}
	
	public Integer getId() {
		Integer id = 0;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions WHERE UUID='"+uuid+"'");
		try {
			if(rs.next()) {
				id = rs.getInt("Player_Group");
			}
		}catch(Exception e) {e.printStackTrace();}
		return id;
	}
	
	public String getName(Integer id) {
		String name = "";
		ResultSet rs = Utils.getMysql().query("SELECT * FROM Permissions_Group WHERE Group_ID='"+id+"'");
		try {
			if(rs.next()) {
				name = rs.getString("Name");
			}
		}catch(Exception e) {e.printStackTrace();}
		return name;
	}
	
	public void create() {
		MPerms mp = new MPerms();
		String Insert_Query = "INSERT INTO Permissions(UUID,Player_Group,CoverGroup,acr) VALUES('"+uuid+"','"+mp.getDefaultGroup()+"','0','1')";
		Utils.getMysql().update(Insert_Query);
	}
	
}
