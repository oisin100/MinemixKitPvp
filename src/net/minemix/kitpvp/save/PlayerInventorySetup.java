package net.minemix.kitpvp.save;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInventorySetup {
	
	private Player player;
	
	private HashMap<EnumItemType, Integer> slotNumber;
	
	public PlayerInventorySetup(Player player) {
		
		this.player = player;
		
		for(int i = 0; i < player.getInventory().getSize(); i++) {
			ItemStack stack = player.getInventory().getItem(i);
			for(EnumItemType type : EnumItemType.list) {
				if(type.isApplicable(stack))
					slotNumber.put(type, i);
			}
		}
	}

	public int getSlotForType(EnumItemType type, Inventory inventory) {
		
		if(slotNumber.containsKey(type))
			return slotNumber.get(type);
		
		if(inventory != null)
			return inventory.firstEmpty();
		
		return 0;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
