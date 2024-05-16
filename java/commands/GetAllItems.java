package commands;

import items.*;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class GetAllItems implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if(commandSender instanceof Player player && (player.getGameMode().equals(GameMode.CREATIVE) || player.isOp())) {
			if(player.isOp()) {
				try {
					player = getServer().getPlayer(strings[1]);
				} catch(Exception exception) {
					//nothing here lol
				}
			}
			if(player == null) {
				commandSender.sendMessage(ChatColor.RED + "Invalid player provided.");
				return false;
			}
			player.getInventory().addItem(
					Axolotl.createCustomItem(),
					Cod.createCustomItem(),
					Pufferfish.createCustomItem(),
					Salmon.createCustomItem(),
					Squid.createCustomItem(),
					Tadpole.createCustomItem(),
					TropicalFish.createCustomItem()

			);
			commandSender.sendMessage("Successfully gave " + player.getName() + " Fish Items");
		}
		return true;
	}
}
