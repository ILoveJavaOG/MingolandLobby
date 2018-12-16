package de.ilovejava.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.ilovejava.api.API_Player_Permission_Remove;
import de.ilovejava.scoreboard.API_ScorePacket;
import de.ilovejava.utils.Utils;

public class Event_Leave implements Listener {
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		new API_Player_Permission_Remove(e.getPlayer());
		if(Utils.getPlayer_Prefixes().containsKey(e.getPlayer())) {
			Utils.getPlayer_Prefixes().remove(e.getPlayer());
		}
		if(Utils.getLogin().contains(e.getPlayer())) {
			Utils.getLogin().remove(e.getPlayer());
		}
		if(Utils.getPlayer_Prefixes().containsKey(e.getPlayer())) {
			API_ScorePacket sc = Utils.getPlayer_ScoreBoard().get(e.getPlayer());
			sc.removeScoreBoard();
			Utils.getPlayer_ScoreBoard().remove(e.getPlayer());
		}
	}
}
