package net.minemix.kitpvp;

import org.bukkit.plugin.java.JavaPlugin;

import net.minemix.kitpvp.onevsone.OneVsOneManager;

public class KitPvp extends JavaPlugin{

	public static KitPvp instance;
	
	public OneVsOneManager oneVsOne;
	
	public void onEnable(){
		instance = this;
		
		oneVsOne = new OneVsOneManager();
	}
	
}
