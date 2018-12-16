package de.ilovejava.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import de.ilovejava.lobby.Lobby;
import de.ilovejava.mysql.MySQL;
import de.ilovejava.scoreboard.API_ScorePacket;
import lombok.Getter;
import lombok.Setter;

public class Utils {
	@Getter @Setter
	public static Lobby instance;
	@Getter @Setter
	public static Location spawnLocation;
	@Getter @Setter
	public static String Prefix;
	@Getter @Setter
	public static ArrayList<Player> isBuild;
	@Getter @Setter
	public static MySQL mysql;
	@Getter @Setter
	public static String Host,User,Database,Password;
	@Getter @Setter
	public static Integer port;
	@Getter @Setter
	public static ArrayList<Player> Login;
	@Getter @Setter
	public static ArrayList<Player>Build;
	@Getter @Setter
	public static HashMap<Player, String>Player_Prefixes = new HashMap<>();
	@Getter @Setter
	public static HashMap<Player, API_ScorePacket>Player_ScoreBoard;
	@Getter @Setter
	public static HashMap<String, Team>TeamSize;
}
