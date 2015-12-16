package net.minemix.kitpvp.save;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.minemix.kitpvp.KitPvp;
import net.minemix.kitpvp.onevsone.arena.ArenaState;

public class SaveCommandExecutor implements CommandExecutor {
	private final Save save;
 
	public SaveCommandExecutor(Save save) {
		this.save = save;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("save")){
			if(!(sender instanceof Player)){
				sender.sendMessage("You must be a player to do this.");
				return true;
			}
			
			if(!KitPvp.instance.oneVsOne.manager.isPlayerInGame((Player) sender)){
				sender.sendMessage(Save.PREFIX + "You must be participating in a duel to use this!");
				return true;
			}
			
			if(KitPvp.instance.oneVsOne.manager.getGame((Player) sender)).getState() != ArenaState.PLAYING) {
				sender.sendMessage(Save.PREFIX + "The duel hasnt begun yet!");
				return true;
			}
			
			save.addPlayerSetup((Player) sender);
			
			sender.sendMessage(Save.PREFIX + "You saved your inventory setup!");
		}
		
		return false;
	}

}