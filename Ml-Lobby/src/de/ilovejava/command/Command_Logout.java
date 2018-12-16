package de.ilovejava.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.ilovejava.utils.Utils;

public class Command_Logout extends AbstartcCommands{

	public Command_Logout(String commandName, Plugin pl) {
		super(commandName, pl);
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("*")) {
				if(Utils.getLogin().contains(p)) {
					Utils.getLogin().remove(p);
					p.sendMessage(Utils.getPrefix() + "Du wurdest ausgeloggt!");
				}else {p.sendMessage(Utils.getPrefix() + "§c§o§lDu bist nicht eingeloggt!");}
			}else {p.sendMessage(Utils.getPrefix() + "§c§o§lDas darfst du nicht!");}
		}
		return true;
	}
	
}
