package de.ilovejava.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Event_PVP implements Listener {
	@EventHandler
	public void onPVP(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.hasPermission("Lobby.PVP")) {}else {e.setCancelled(true);}
		}
	}
	
	@EventHandler
	public void onMon(CreatureSpawnEvent e) {
		if(e.getSpawnReason().equals(SpawnReason.NATURAL)) {e.setCancelled(true);}
	}
}
