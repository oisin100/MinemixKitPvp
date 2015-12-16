package net.minemix.kitpvp.save;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Save {
	
	public static final String PREFIX = ChatColor.GREEN + "" + ChatColor.BOLD + "Save" + ChatColor.YELLOW + ChatColor.BOLD + ">> " + ChatColor.AQUA; 
	
	private ArrayList<PlayerInventorySetup> setups;
	
	
	
	public Save() {
		EnumItemType.init();
		
		setups = new ArrayList<>();
	}
	
	public PlayerInventorySetup getPlayerSetup(Player player) {
		for(PlayerInventorySetup setup : setups)
			if(setup.getPlayer() == player)
				return setup;
		
		return null;
	}
	
	public void addPlayerSetup(Player player) {
		
		PlayerInventorySetup remove = null;
		
		for(PlayerInventorySetup setup : setups) {
			if(setup.getPlayer() == player) {
				remove = setup;
			}
		}
		
		if(remove != null)
			setups.remove(remove);
		
		setups.add(new PlayerInventorySetup(player));
	}

}
