package de.ilovejava.api;

import de.ilovejava.mgroup.MGroup;

public class API_RegisterTabList {
	public API_RegisterTabList() {
		GetAllGroups g = new GetAllGroups();
		for(String s : g.getGroups()) {
			MGroup mg = new MGroup(s);
			new TabList(s, mg.getTabID(), s);
		}
	}
}
