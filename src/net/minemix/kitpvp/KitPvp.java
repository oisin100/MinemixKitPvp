package net.minemix.kitpvp;

import org.bukkit.plugin.java.JavaPlugin;

import net.minemix.kitpvp.onevsone.OneVsOneManager;
import net.minemix.kitpvp.save.Save;

public class KitPvp extends JavaPlugin{

	public static KitPvp instance;
	
	public OneVsOneManager oneVsOne;
	public Save save;
	
	public void onEnable(){
		instance = this;
		
		oneVsOne = new OneVsOneManager();
		save = new Save();
	}
	
}
