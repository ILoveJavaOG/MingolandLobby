package de.ilovejava.command;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import de.ilovejava.utils.Utils;

public class Command_Spawn extends AbstartcCommands{

	public Command_Spawn(String commandName, Plugin pl) {
		super(commandName, pl);
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(Utils.getSpawnLocation() != null) {
					p.teleport(Utils.getSpawnLocation());
				}
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("set")) {
					if(p.hasPermission("Lobby.Spawn.Set")) {
						FileConfiguration cfg = Utils.getInstance().getConfig();
						cfg.set("Config.Spawn.x", p.getLocation().getX());
						cfg.set("Config.Spawn.Y", p.getLocation().getY());
						cfg.set("Config.Spawn.z", p.getLocation().getZ());
						cfg.set("Config.Spawn.World", p.getWorld().getName());
						cfg.set("Config.Spawn.Yaw", p.getLocation().getYaw());
						cfg.set("Config.Spawn.Pitch", p.getLocation().getPitch());
						Utils.getInstance().saveConfig();
						Utils.setSpawnLocation(p.getLocation());
						p.sendMessage(Utils.getPrefix() + "Der Spawn wurde gesetzt!");
					}else {
						if(Utils.getSpawnLocation() != null) {
							p.teleport(Utils.getSpawnLocation());
						}
					}
				}else {p.sendMessage(Utils.Prefix + "§c§o§lAchtung, der Command ist und leider nicht bekannt!");}
			}else {p.sendMessage(Utils.Prefix + "§c§o§lAchtung, der Command ist und leider nicht bekannt!");}
		}
		return true;
	}
	
}
