package net.minemix.kitpvp.onevsone.arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.minemix.kitpvp.KitPvp;
import net.minemix.kitpvp.save.EnumItemType;
import net.minemix.kitpvp.save.PlayerInventorySetup;

public class Arena {

	private String ID;
	private Player[] players;
	private Location[] spawns;
	
	private ArenaState state;
	private int ticksSinceStarted;
	private int round;
	private int timer;
	
	private Inventory[] inventories;
	private Location[] playerLocations;
	
	public Arena(String ID, Location[] spawns) {
		this.ID = ID;
		this.spawns = spawns;
		players = new Player[2];
		inventories = new Inventory[2];
		playerLocations = new Location[2];
		state = ArenaState.WAITING;
	}
	
	public void tick() {
		
		ticksSinceStarted++;
		
		if(state == ArenaState.WAITING){
			state = ArenaState.SETTING;
			ticksSinceStarted = 0;
		}
		
		if(state == ArenaState.SETTING) {
			if(round == 0) {
				if(ticksSinceStarted % 20 == 0) 
					sendMessageToPlayers("&7Time till start: &6&l" + (200 - ticksSinceStarted / 20));
			} else {
				if(ticksSinceStarted % 20 == 0) 
					sendMessageToPlayers("&7Next round in: &6&l" + (200 - ticksSinceStarted / 20));
			}
			if(ticksSinceStarted >= 200) {
				state = ArenaState.PLAYING;
				
				playerLocations[0] = players[0].getLocation();
				playerLocations[1] = players[1].getLocation();
				
				players[0].teleport(spawns[0]);
				players[1].teleport(spawns[1]);
				
				inventories[0] = players[0].getInventory();
				inventories[1] = players[1].getInventory();
				
				players[0].getInventory().clear();
				players[1].getInventory().clear();
				
				giveInventory(players[0]);
				giveInventory(players[1]);
				
				ticksSinceStarted = 0;
			}
		}
		
	}
	
	public void sendMessageToPlayers(String message) {
		String color = ChatColor.translateAlternateColorCodes('&', message);
		players[0].sendMessage(color);
		players[1].sendMessage(color);
	}
	
	public void removePlayer(Player player) {
		
		if(!isPlayerPlaying(player))
			return;

		if(state == ArenaState.SETTING || state == ArenaState.PLAYING || state == ArenaState.ENDING){

			for(int i = 0; i < players.length; i++){
				players[i].getInventory().clear();

				for(ItemStack stack : inventories[i].getContents()) {
					players[i].getInventory().addItem(stack);
				}
				
				players[i].teleport(playerLocations[i]);
				players[i] = null;
			}
		}
	}
	
	public void giveInventory(Player player) {
		
		PlayerInventorySetup setup = KitPvp.instance.save.getPlayerSetup(player);
		
		if(setup != null){
			player.getInventory().setItem(setup.getSlotForType(EnumItemType.SWORD, player.getInventory()), new ItemStack(Material.IRON_SWORD));
			player.getInventory().setItem(setup.getSlotForType(EnumItemType.BOW, player.getInventory()), new ItemStack(Material.BOW));
			player.getInventory().setItem(setup.getSlotForType(EnumItemType.ROD, player.getInventory()), new ItemStack(Material.FISHING_ROD));
			player.getInventory().setItem(setup.getSlotForType(EnumItemType.FNS, player.getInventory()), new ItemStack(Material.FIREBALL));
		}
		else {
			player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
			player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
			player.getInventory().addItem(new ItemStack(Material.BOW));
			player.getInventory().addItem(new ItemStack(Material.FIREBALL));
		}
		
		player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));

		
	}
	
	public boolean isPlayerPlaying(Player player) {
		return (players[0] == player || players[1] == player);
	}
	
	public ArenaState getState(){
		return state;
	}
	
}
