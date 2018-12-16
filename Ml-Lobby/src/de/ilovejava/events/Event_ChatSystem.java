package de.ilovejava.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.ilovejava.utils.Utils;

public class Event_ChatSystem implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String prefix = Utils.getPlayer_Prefixes().get(e.getPlayer());
		String Message = prefix+" "+e.getPlayer().getName() + " §8§o§l>> §f" + e.getMessage();
		e.setFormat(Message);
	}
}
