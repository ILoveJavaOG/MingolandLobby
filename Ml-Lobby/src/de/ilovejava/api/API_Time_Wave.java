package de.ilovejava.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.ilovejava.utils.Utils;

public class API_Time_Wave {
	Integer i = 1;
	public API_Time_Wave() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				switch (i) {
				case 1:
					i ++;
					for(Player p : Bukkit.getOnlinePlayers()) {
						API_ActionBar.sendActionBarTime(p, "§e§o§lForum? §b§o§l/Forum", 29);
					}
					break;
				case 2:
					i++;
					for(Player p : Bukkit.getOnlinePlayers()) {
						API_ActionBar.sendActionBarTime(p, "§a§o§lTeamspeak? §b§o§l/Teamspeak", 29);
					}
					break;
				case 3:
					i=1;
					for(Player p : Bukkit.getOnlinePlayers()) {
						API_ActionBar.sendActionBarTime(p, "§a§o§lHilfe? §b§o§l/Hilfe", 29);
					}
					break;
				}
			}
		}.runTaskTimer(Utils.getInstance(), 0, 30);
	}
}
