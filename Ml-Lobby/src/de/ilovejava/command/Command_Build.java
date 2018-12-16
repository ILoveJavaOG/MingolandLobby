package de.ilovejava.command;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.ilovejava.utils.Utils;

public class Command_Build extends AbstartcCommands{

	public Command_Build(String commandName, Plugin pl) {
		super(commandName, pl);
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("Lobby.Build")) {
				if(Utils.getBuild().contains(p)) {
					Utils.getBuild().remove(p);
					p.sendMessage(Utils.getPrefix() + "Du bist nun nicht mehr im Build Modus!");
					p.setGameMode(GameMode.ADVENTURE);
				}else {
					p.sendMessage(Utils.getPrefix() + "Du bist nun im Build Modus!");
					Utils.Build.add(p);
					p.setGameMode(GameMode.CREATIVE);
					p.getInventory().clear();
				}
			}
		}
		return true;
	}
	
}
