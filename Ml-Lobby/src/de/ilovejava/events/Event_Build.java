package de.ilovejava.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.ilovejava.utils.Utils;

public class Event_Build implements Listener {
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("Lobby.Build")) {
			if(Utils.getBuild().contains(p)) {
				
			}else {e.setCancelled(true);}
		}else {e.setCancelled(true);}
	}
	@EventHandler
	public void onBuild(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("Lobby.Build")) {
			if(Utils.getBuild().contains(p)) {
				
			}else {e.setCancelled(true);}
		}else {e.setCancelled(true);}
	}
}
