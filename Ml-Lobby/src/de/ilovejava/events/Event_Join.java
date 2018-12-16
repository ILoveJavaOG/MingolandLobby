package de.ilovejava.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import de.ilovejava.api.API_Player_Permission_Set;
import de.ilovejava.crafttitle.API_CraftTitleApi;
import de.ilovejava.permplayer.PermPlayer;
import de.ilovejava.scoreboard.API_ScorePacket;
import de.ilovejava.utils.Utils;

public class Event_Join implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		for(int i = 0; i< 60; i++) {e.getPlayer().sendMessage(" ");}
		e.getPlayer().sendMessage("§a§o§l========§8§o§l(§e§o§lMingoland.de§8§o§l)§a§o§l========");
		e.getPlayer().sendMessage("      §b§o§LWillkommen auf Mingoland.de");
		e.getPlayer().sendMessage("§a§o§l============================");
		API_CraftTitleApi.sendFullTitle(e.getPlayer(), 20, 20, 20, "§b§o§l"+e.getPlayer().getName(), "§5§o§lWillkommen auf Mingoland.de");
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if(Utils.getSpawnLocation() != null) {
					e.getPlayer().setGameMode(GameMode.ADVENTURE);
					e.getPlayer().teleport(Utils.getSpawnLocation());
				}
			}
		}.runTaskLater(Utils.getInstance(), 8);
		settings(e.getPlayer());
	}
	
	public void settings(Player p) {
		API_ScorePacket score = new API_ScorePacket(p);
		Utils.getPlayer_ScoreBoard().put(p, score);
		PermPlayer pp = new PermPlayer(p);
		if(!pp.isExists()) {
			pp.create();
		}
		
		p.setFoodLevel(20);
		p.setHealth(20);
		
		new API_Player_Permission_Set(p);
	}
}
