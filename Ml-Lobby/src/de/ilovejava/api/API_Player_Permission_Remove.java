package de.ilovejava.api;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import de.ilovejava.utils.Utils;

public class API_Player_Permission_Remove {
	public API_Player_Permission_Remove(Player p) {
		PermissionAttachment at = p.addAttachment(Utils.getInstance());
		Set<PermissionAttachmentInfo> perms = p.getEffectivePermissions();
		for(PermissionAttachmentInfo pp : perms) {
			at.unsetPermission(pp.getPermission());
		}
	}
}
