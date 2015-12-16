package net.minemix.kitpvp.onevsone;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OneVsOneListeners implements Listener{

	private OneVsOneManager manager;
	
	public OneVsOneListeners(OneVsOneManager manager) {
		this.manager = manager;
	}
	
	public void onPlayerLeave(PlayerQuitEvent e) {
		manager.manager.removePlayer(e.getPlayer());
	}
	
	public void onPlayerDie(PlayerDeathEvent e) {
		manager.manager.removePlayer(e.getEntity());
	}
	
}
