package de.ilovejava.lobby;

import org.bukkit.plugin.java.JavaPlugin;

import de.ilovejava.api.API_Time_Wave;
import de.ilovejava.modul.loadModul;
import de.ilovejava.utils.Utils;

public class Lobby extends JavaPlugin{
	@Override
	public void onEnable() {
		Utils.setInstance(this);
		new loadModul();
		new API_Time_Wave();
		Utils.setPrefix("§b§o§lMingoland.de§8§o§l: §e§o§l");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
