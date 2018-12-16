package de.ilovejava.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.ilovejava.utils.Utils;

public class TabList {
	String g;
	String Prefix;
	Integer i;
	Scoreboard board;
	Player a;
	public TabList(String group, Integer size,String pre) {
		this.g = group;
		this.Prefix = pre;
		this.i = size;
		this.board = Bukkit.getScoreboardManager().getMainScoreboard();
		if(!Utils.getTeamSize().containsKey(this.g)) {
			Utils.getTeamSize().put(this.g, createTeam());
		}
	}
	
	public TabList(Player p, String group) {
		this.a = p;
		this.g = group;
		this.board = Bukkit.getScoreboardManager().getMainScoreboard();
	}
	
	public void setPlayerToTeam() {
		if(Utils.getTeamSize().containsKey(this.g)) {
			Team t = Utils.getTeamSize().get(this.g);
			t.addPlayer(this.a);
			update();
		}
	}
	
	public void update() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
	}
	
	public Team createTeam() {
		Team t = board.registerNewTeam(this.g);
		System.out.println("team: " + this.g + " wurde erstellt!");
		t.setPrefix("000"+this.i +this.Prefix);
		return t;
	}
}
