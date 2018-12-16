package de.ilovejava.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.ilovejava.utils.Utils;

public class Command_Forum extends AbstartcCommands{

	public Command_Forum(String commandName, Plugin pl) {
		super(commandName, pl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p  = (Player) sender;
			if(args.length == 0) {
				p.sendMessage("§a§o§l========§8§o§l(§e§o§lMingoland.de§8§o§l)§a§o§l========");
				p.sendMessage("§e§o§lUnser Forum findest du unter:");
				p.sendMessage("§b§o§lMingoland.de");
				p.sendMessage("§a§o§l============================");
			}else {p.sendMessage(Utils.Prefix + "§c§o§lAchtung, der Command ist und leider nicht bekannt!");}
		}
		return true;
	}
	
}
