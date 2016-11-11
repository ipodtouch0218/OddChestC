package me.ipodtouch0218.oddchestc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;

public class OddChestCMD implements CommandExecutor {

	private Plugin instance;
	
	public OddChestCMD(Plugin pl) {
		instance = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("oddchest.give")) {
			if (args.length >= 4) {
				
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("Messages.invalidUsage")));
			}
		}
		return false;
	}

}
