package net.minemix.kitpvp.onevsone;

import net.minemix.kitpvp.KitPvp;
import net.minemix.kitpvp.onevsone.arena.ArenaManager;

public class OneVsOneManager {
	
	public ArenaManager manager;

	public OneVsOneManager() {
		KitPvp.instance.getServer().getPluginManager().registerEvents(new OneVsOneListeners(this), KitPvp.instance);
		
		manager = new ArenaManager();
	}
	
}
