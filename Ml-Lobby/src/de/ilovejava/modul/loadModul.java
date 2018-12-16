package de.ilovejava.modul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import de.ilovejava.api.API_Key;
import de.ilovejava.api.API_RandomString;
import de.ilovejava.api.API_RegisterTabList;
import de.ilovejava.command.Command_Build;
import de.ilovejava.command.Command_Forum;
import de.ilovejava.command.Command_Help;
import de.ilovejava.command.Command_Login;
import de.ilovejava.command.Command_Logout;
import de.ilovejava.command.Command_Permission;
import de.ilovejava.command.Command_Spawn;
import de.ilovejava.command.Command_Teamspeak;
import de.ilovejava.config.Config_Default;
import de.ilovejava.events.Event_Build;
import de.ilovejava.events.Event_ChatSystem;
import de.ilovejava.events.Event_Command;
import de.ilovejava.events.Event_Food;
import de.ilovejava.events.Event_Join;
import de.ilovejava.events.Event_Leave;
import de.ilovejava.events.Event_PVP;
import de.ilovejava.events.Event_PickUP;
import de.ilovejava.mperms.MPerms;
import de.ilovejava.mysql.MySQL;
import de.ilovejava.utils.Utils;

public class loadModul {
	public loadModul() {
		loadListener();
		loadCommands();
		loadConfigs();
		setUpUtils();
		setUpMySQl();
		setUpDataBases();
	}

	private void setUpDataBases() {
		//this.mysql.update("CREATE TABLE IF NOT EXISTS Coins(UUID varchar(70),Coins int,loot int, Skulls int);");
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS Permissions(UUID varchar(70),Player_Group int,CoverGroup int,acr int);");
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS Permissions_Values(Permission_ID MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (Permission_ID),Group_ID int, Permission_Name varchar(300))");
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS Permissions_Group(Group_ID MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (Group_ID), Prefix varchar(230), CoverGroups varchar(300), default_group int, Name varchar(200), Tab_ID int)");
		Utils.getMysql().update("CREATE TABLE IF NOT EXISTS Permissions_AdminKey(Admin_Key varchar(300),Key_Create int)");
		MPerms mp = new MPerms();
		API_Key k = new API_Key();
		API_RandomString r = new API_RandomString();
		new API_RegisterTabList();
		if(!k.isKeyCreate()) {
			Utils.getMysql().update("INSERT INTO Permissions_AdminKey(Admin_Key,Key_Create) VALUES('"+r.getString()+"','1')");
		}
		if(mp.getDefaultGroup() == 0) {Utils.getMysql().update("INSERT INTO Permissions_Group(Prefix,CoverGroups,default_group,Name,Tab_ID) VALUES('default','null','1','Default','0')");}
	}

	private void setUpMySQl() {
		new Config_Default();
		if(!Utils.getDatabase().equalsIgnoreCase("Host") && !Utils.getHost().equalsIgnoreCase("Database")) {
			Utils.setMysql(new MySQL(Utils.getHost(), Utils.getDatabase(), Utils.getUser(), Utils.getPassword(), Utils.getPort()));
		}else {Bukkit.getConsoleSender().sendMessage("§cAchtug, MySQL Fehler!");Bukkit.getServer().getPluginManager().disablePlugin(Utils.getInstance());}
	}

	private void setUpUtils() {
		Utils.setIsBuild(new ArrayList<>());
		Utils.setLogin(new ArrayList<>());
		Utils.setBuild(new ArrayList<>());
		Utils.setPlayer_ScoreBoard(new HashMap<>());
		Utils.setTeamSize(new HashMap<>());
	}

	private void loadConfigs() {
		FileConfiguration cfg = Utils.getInstance().getConfig();
		if(cfg.isSet("Config.Spawn")) {
			Location spawn = new Location(Bukkit.getWorld(cfg.getString("Config.Spawn.World")), 
					cfg.getDouble("Config.Spawn.x"), 
					cfg.getDouble("Config.Spawn.Y"), 
					cfg.getDouble("Config.Spawn.z"), (float)cfg.getDouble("Config.Spawn.Yaw"),
					(float)cfg.getDouble("Config.Spawn.Pitch"));
			Utils.setSpawnLocation(spawn);
		}
	}

	private void loadCommands() {
		new Command_Spawn("spawn",Utils.getInstance());
		new Command_Help("hilfe",Utils.getInstance());
		new Command_Help("help", Utils.getInstance());
		new Command_Teamspeak("Teamspeak",Utils.getInstance());
		new Command_Forum("Forum",Utils.getInstance());
		new Command_Permission("IPerms",Utils.getInstance());
		new Command_Login("IPermsLogin", Utils.getInstance());
		new Command_Logout("IPermsLogout",Utils.getInstance());
		new Command_Build("Build",Utils.getInstance());
	}

	private void loadListener() {
		HashSet<Listener> events = new HashSet<>();
		events.add(new Event_Join());
		events.add(new Event_Command());
		events.add(new Event_Leave());
		events.add(new Event_PVP());
		events.add(new Event_Build());
		events.add(new Event_Food());
		events.add(new Event_PickUP());
		events.add(new Event_ChatSystem());
		
		for(Listener l : events) {
			Bukkit.getServer().getPluginManager().registerEvents(l, Utils.getInstance());
		}
	}
}
