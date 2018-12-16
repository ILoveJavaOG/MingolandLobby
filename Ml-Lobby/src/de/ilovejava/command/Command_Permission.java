package de.ilovejava.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.ilovejava.mgroup.MGroup;
import de.ilovejava.mperms.MPerms;
import de.ilovejava.permplayer.PermPlayer;
import de.ilovejava.utils.Utils;

public class Command_Permission extends AbstartcCommands{

	public Command_Permission(String commandName, Plugin pl) {
		super(commandName, pl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("*")) {
				if(!Utils.getLogin().contains(p)) {p.sendMessage(Utils.Prefix + "§c§o§lAchtung, der Command ist gesperrt!"); return true;}
				if(args.length == 0) {
					p.sendMessage(" ");
					p.sendMessage("§a§o§L============§8§o§l(§b§o§lGroup§8§o§l)§a§o§l============");
					p.sendMessage("§b§o§l/IPerms group create Name");
					p.sendMessage("§b§o§l/IPerms group addpermission Permission Group");
					p.sendMessage("§b§o§l/IPerms group setPrefix Prefix Group");
					p.sendMessage("§b§o§l/IPerms group setdefault Group");
					p.sendMessage("§b§o§l/IPerms group setTab Group ID");
					p.sendMessage("§b§o§l/IPerms group group/s (Name)");
					p.sendMessage("§a§o§l==============================");
					p.sendMessage(" ");
					p.sendMessage("§a§o§L============§8§o§l(§b§o§lSetting§8§o§l)§a§o§l===========");
					p.sendMessage("§b§o§l/IPerms group delgroup Group");
					p.sendMessage("§b§o§l/IPerms group removePermission Permission Group");
					p.sendMessage("§b§o§l/IPerms user groupset Player Group");
					p.sendMessage("§a§o§l===============================");
					p.sendMessage(" ");
				}else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("group")) {
						p.sendMessage(" ");
						p.sendMessage("§a§o§L============§8§o§l(§b§o§lGroup§8§o§l)§a§o§l============");
						p.sendMessage("§b§o§l/IPerms group create Name");
						p.sendMessage("§b§o§l/IPerms group addpermission Permission Group");
						p.sendMessage("§b§o§l/IPerms group setPrefix Prefix Group");
						p.sendMessage("§b§o§l/IPerms group setdefault Group");
						p.sendMessage("§b§o§l/group group/s (Name)");
						p.sendMessage("§a§o§l==============================");
						p.sendMessage(" ");
						p.sendMessage("§a§o§L============§8§o§l(§b§o§lSetting§8§o§l)§a§o§l===========");
						p.sendMessage("§b§o§l/IPerms group delgroup Group");
						p.sendMessage("§b§o§l/Iperms group removePermission Permission Group");
						p.sendMessage("§b§o§l/IPerms user groupset Player Group");
						p.sendMessage("§a§o§l===============================");
						p.sendMessage(" ");
					}else if(args[0].equalsIgnoreCase("groups")) {
						
					}
				}else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("group")) {
						p.sendMessage(" ");
						p.sendMessage("§a§o§L============§8§o§l(§b§o§lGroup§8§o§l)§a§o§l============");
						p.sendMessage("§b§o§l/IPerms group create Name");
						p.sendMessage("§b§o§l/IPerms group addpermission Permission Group");
						p.sendMessage("§b§o§l/IPerms group setPrefix Prefix Group");
						p.sendMessage("§b§o§l/IPerms group setdefault Group");
						p.sendMessage("§b§o§l/group group/s (Name)");
						p.sendMessage("§a§o§l==============================");
						p.sendMessage(" ");
						p.sendMessage("§a§o§L============§8§o§l(§b§o§lSetting§8§o§l)§a§o§l===========");
						p.sendMessage("§b§o§l/IPerms group delgroup Group");
						p.sendMessage("§b§o§l/Iperms group removePermission Permission Group");
						p.sendMessage("§b§o§l/IPerms user groupset Player Group");
						p.sendMessage("§b§o§l/IPerms user groupremove Player");
						p.sendMessage("§a§o§l===============================");
						p.sendMessage(" ");
					}
				}else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("group")) {
						if(args[1].equalsIgnoreCase("create")) {
							String Name = args[2];
							MGroup group = new MGroup(Name);
							if(!group.isExists()) {
								group.create();
								p.sendMessage(Utils.getPrefix() + "Die Groupe §b§o§l" + Name + "§e§o§l wurde erstellt!");
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe wurde bereits Erstellt!");}
						}else if(args[1].equalsIgnoreCase("setdefault")) {
							String group = args[2];
							MGroup mg = new MGroup(group);
							if(mg.isExists()) {
								mg.changeDefaultGroup();
								p.sendMessage(Utils.getPrefix() + "Du hast die Gruppe §b§o§l " + group + "§e§o§l zur default Gruppe ernannt!");
							}else {p.sendMessage("§c§o§lAchtung, die Gruppe wurde nicht erstellt!");}
						}else if(args[1].equalsIgnoreCase("delgroup")) {
							MGroup mg = new MGroup(args[2]);
							if(mg.isExists()) {
								if(mg.delet()) {
									p.sendMessage(Utils.getPrefix() + "Die Gruppe §c§o§l " + args[2] + "§e§o§l wurde gelöscht!");
								}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, es ist ein Fehler aufgetreten!");}
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe wurde nicht gefunden!");}
						}
					}else if(args[0].equalsIgnoreCase("user")) {
						if(args[1].equalsIgnoreCase("groupremove")) {
							PermPlayer pp = new PermPlayer(args[2]);
							if(pp.isExists()) {
								pp.setDefault();
								p.sendMessage(Utils.getPrefix() + "Der Spieler §c§o§l" + args[2] + "§e§o§l wurde wieder in die Default gruppe gesetzt!");
							}else{p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, der Spieler nicht in der Datenbank gefunden!");}
						}
					}
				}else if(args.length == 4) {
					if(args[0].equalsIgnoreCase("group")) {
						if(args[1].equalsIgnoreCase("addpermission")) {
							String Permission = args[2];
							String Group = args[3];
							MGroup g = new MGroup(Group);
							if(g.isExists()) {
								MPerms mp = new MPerms();
								mp.createPermission(Permission, g.getGroupId());
								p.sendMessage(Utils.getPrefix() + "Die Permission §b§l§o" + Permission + "§e§o§l wurde zur Gruppe §b§o§l " + Group + "§e§o§l hinzugefühgt!");
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe ist nicht erstellt worden!");}
						}else if(args[1].equalsIgnoreCase("setprefix")) {
							String group = args[2];
							String prefix = args[3];
							MGroup mg = new MGroup(group);
							if(mg.isExists()) {
								mg.setPrefix(prefix);
								p.sendMessage(Utils.Prefix + "Die Prefix der Gruppe §b§o§l" + group + "§e§o§l lautet nun §b§o§l" + prefix);
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe wurde nicht erstellt!");}
						}else if(args[1].equalsIgnoreCase("removePermission")) {
							MPerms mp = new MPerms();
							MGroup mg = new MGroup(args[3]);
							if(mg.isExists()) {
								if(mp.removePermissionFromGroup(args[2], mg.getGroupId())) {
									p.sendMessage(Utils.getPrefix() + "Die Permission §c§o§l" + args[2] + "§e§o§l Wurde gelöscht!");
								}else {p.sendMessage(Utils.Prefix + "§c§o§lAchtung, die Permission konnte nicht gelöscht werden!");}
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe wurde nicht erstellt!");}
						}else if(args[1].equalsIgnoreCase("setTab")) {
							Integer id = Integer.valueOf(args[2]);
							String Group = args[3];
							MGroup mg = new MGroup(Group);
							if(mg.isExists()) {
								mg.setTabID(id);
								p.sendMessage(Utils.getPrefix() + "Der Gruppe §b§o§l" + Group + "§e§o§l wurde die TabList ID §b§o§l " + id + "§e§o§L zugestellt!");
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe wurde nicht gefunden!");}
						}
					}else if(args[0].equalsIgnoreCase("user")) {
						if(args[1].equalsIgnoreCase("groupset")) {
							PermPlayer pp = new PermPlayer(args[2]);
							if(pp.isExists()) {
								MGroup mg = new MGroup(args[3]);
								if(mg.isExists()) {
									pp.setPlayerGroup(mg.getGroupId());
									p.sendMessage(Utils.getPrefix() + "Der Spieler §c§o§l" + args[2] + "§e§o§l wurde in die Gruppe §c§o§l" + args[3] + "§e§o§l gesetzt!");
								}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, die Gruppe wurde nicht gefunden!");}
							}else {p.sendMessage(Utils.getPrefix() + "§c§o§lAchtung, der Spieler ist nicht in der Datenbank aufgeführt!");}
						}
					}
				}
			}else {
				p.sendMessage(Utils.getPrefix() + "§c§o§lDu hast leider nich die Nötigen Rechte!");
			}
		}
		return true;
	}
	
}
