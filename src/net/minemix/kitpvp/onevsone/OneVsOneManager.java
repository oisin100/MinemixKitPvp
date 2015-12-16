package net.minemix.kitpvp.onevsone;

import net.minemix.kitpvp.KitPvp;

public class OneVsOneManager {

	public OneVsOneManager() {
		KitPvp.instance.getServer().getPluginManager().registerEvents(new OneVsOneListeners(), KitPvp.instance);
	}
	
}
