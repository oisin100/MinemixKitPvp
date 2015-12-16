package net.minemix.kitpvp.save;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum EnumItemType {
	SWORD("sword"),
	FNS(Material.FIREWORK_CHARGE),
	ROD(Material.FISHING_ROD),
	BOW(Material.BOW);
	
	public static ArrayList<EnumItemType> list = new ArrayList<EnumItemType>();
	
	public static void init(){
		list.add(SWORD);
		list.add(FNS);
		list.add(ROD);
		list.add(BOW);
	}
	
	private String applicable;
	private Material mapplicable;
	
	private EnumItemType(Material applicable) {
		mapplicable = applicable;
	}
	
	private EnumItemType(String applicable) {
		this.applicable = applicable;
	}

	public boolean isApplicable(ItemStack material) {
		if(mapplicable != null)
			if(material.getType() == mapplicable)
				return true;
			else
				return false;
		if(!applicable.equals(""))
			if(material.getItemMeta().getDisplayName().contains(applicable))
				return true;
		
		return false;
	}
	
}
