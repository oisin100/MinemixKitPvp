package net.minemix.kitpvp.onevsone.arena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ArenaManager {
	
	public ArrayList<Arena> arenas;
	
	public ArenaManager() {
		arenas = new ArrayList<Arena>();
		Location[] locs = new Location[2];
		locs[0] = new Location(Bukkit.getWorld("world"), 0 ,70, 0);
		locs[1] = new Location(Bukkit.getWorld("world"), 0 ,70, 0);
		arenas.add(new Arena("MUCHHARDCODE", locs));
	}

	public boolean isPlayerInGame(Player player) {
		for(Arena arena : arenas)
			if(arena.isPlayerPlaying(player))
				return true;
		
		return false;
	}
	
	public void removePlayer(Player player) {
		for(Arena arena : arenas)
			if(arena.isPlayerPlaying(player))
				arena.removePlayer(player);
	}
	
	public Arena getGame(Player player) {
		for(Arena arena : arenas){
			if(arena.isPlayerPlaying(player)) {
				return arena;
			}
		}
		return null;
	}
}
