package de.ilovejava.api;


import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import de.ilovejava.mgroup.MGroup;
import de.ilovejava.permplayer.PermPlayer;
import de.ilovejava.utils.Utils;

public class API_Player_Permission_Set {
	public API_Player_Permission_Set(Player p ) {
		PermPlayer pp = new PermPlayer(p);
		System.out.println("Id: " + pp.getId());
		System.out.println("Name: " + pp.getName(pp.getId()));
		MGroup mg = new MGroup(pp.getName(pp.getId()));
		ArrayList<String>perm = mg.getGroupPermission();
		Utils.getPlayer_Prefixes().put(p, mg.getGroupPrefix());
		
		if(perm.size() != 0) {
			PermissionAttachment at = p.addAttachment(Utils.getInstance());
			for(String perms : perm) {
				at.setPermission(perms, true);
			}
		}else {System.out.println("Spieler " + p.getName()+ " hat keine Permissions!");}
	}
}
