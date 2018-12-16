package de.ilovejava.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import de.ilovejava.utils.Utils;

public class Event_Command implements Listener {
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(!e.isCancelled()) {
			String cmd = e.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			String msg = e.getMessage().toLowerCase();
			if(topic == null) {
				e.getPlayer().sendMessage(Utils.Prefix + "§b§o§lDer Command §e§o§l" + cmd + "§b§o§l ist nicht bekannt, benutz bitte /hilfe");
				e.setCancelled(true);
			}else if(msg.contains("/plugins") || msg.contains("/pl") || msg.contains("/gc") || msg.contains("/icanhasbukkit") || msg.contains("/?")
					|| msg.contains("/bukkit")) {
				if(e.getPlayer().hasPermission("Lobby.Developer")) {}else {e.setCancelled(true); e.getPlayer().sendMessage("§fPlugins (1): §aMingoland");}
		
			}else if(msg.contains("/version")) {
				if(e.getPlayer().hasPermission("Lobby.Developer")) {}else {e.setCancelled(true); e.getPlayer().sendMessage(Utils.Prefix + "§e§o§lMingoland.de ist für die Version 1.12.2 verfügbar");}
			}
		}
	}
}
