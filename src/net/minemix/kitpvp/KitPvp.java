package net.minemix.kitpvp;

import org.bukkit.plugin.java.JavaPlugin;

public class KitPvp extends JavaPlugin{

	public static KitPvp instance;
	
	public void onEnable(){
		instance = this;
	}
	
}
