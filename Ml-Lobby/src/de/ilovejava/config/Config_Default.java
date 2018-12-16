package de.ilovejava.config;

import org.bukkit.configuration.file.FileConfiguration;

import de.ilovejava.utils.Utils;

public class Config_Default {
	public Config_Default() {
		initConfig("Config.MySQL.Host", "Host");
		initConfig("Config.MySQL.Database", "Database");
		initConfig("Config.MySQL.User","User");
		initConfig("Config.MySQL.Password", "Password");
		initConfig("Config.MySQL.Port", 3306);
		
		Utils.setHost((String)getInit("Config.MySQL.Host"));
		Utils.setDatabase((String)getInit("Config.MySQL.Database"));
		Utils.setUser((String)getInit("Config.MySQL.User"));
		Utils.setPassword((String)getInit("Config.MySQL.Password"));
		Utils.setPort((Integer)getInit("Config.MySQL.Port"));
	}
	
	private Object getInit(String Path) {
		Object obj = null;
		FileConfiguration cfg = Utils.getInstance().getConfig();
		obj = cfg.get(Path);
		return obj;
	}
	
	private void initConfig(String Path, Object obj) {
		FileConfiguration cfg = Utils.getInstance().getConfig();
		if(!cfg.isSet(Path)) {
			cfg.set(Path, obj);
			Utils.getInstance().saveConfig();
		}
	}
}
