package de.ilovejava.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.ilovejava.api.API_Key;
import de.ilovejava.utils.Utils;

public class Command_Login extends AbstartcCommands{

	public Command_Login(String commandName, Plugin pl) {
		super(commandName, pl);
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("*")) {
				if(args.length == 0) {
					p.sendMessage("§b§o§l/IPermsLogin Key");
				}else if(args.length == 1) {
					String key = args[0];
					API_Key k = new API_Key();
					if(k.isKey(key)) {
						Utils.getLogin().add(p);
						p.sendMessage(Utils.getPrefix() + "Der Key wurde angenommen! /IPerms steht dir jetzt zur zurverfügung!");
					}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, der Schlüssel ist falsch!");}
				}
			}else {p.sendMessage(Utils.getPrefix() + "§c§o§lDas darfst du nicht!");}
		}
		return true;
	}
	
}
