package me.ipodtouch0218.oddchestc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.ipodtouch0218.oddchestc.item.OddChest;
import me.ipodtouch0218.oddchestc.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CommandOddchest implements CommandExecutor {

	private FileConfiguration messages;
	
	public CommandOddchest(FileConfiguration cfg) {
		messages = cfg;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("oddchest.give")) {
			if (args.length >= 4) {
				Player target = Bukkit.getPlayer(args[1]);
				OddChest oddchest = null;
				int amount = 0;;
				try {
					oddchest = new OddChest(args[2]);
					amount = Integer.parseInt(args[3]);
				} catch (NullPointerException npe) {
					sender.sendMessage(ChatColor.RED + "The Oddchest could not be found!");
					npe.printStackTrace();
					return true;
				} catch (NumberFormatException nfe) {
					sender.sendMessage(ChatColor.RED + "You did not enter a number for the amount!");
					return true;
				}
				if (target.getInventory().firstEmpty() == -1) {
					sender.sendMessage(Util.messageConfigMsg(messages, "playerInvFull", target));
					return true;
				} else {
					ItemStack itemToAdd = oddchest.toItemStack();
					itemToAdd.setAmount(amount);
					target.getInventory().addItem(itemToAdd);
				}
				sender.sendMessage(Util.messageConfigMsg(messages, "playerGivenItem", target, args[2], amount));
			} else {
				sender.sendMessage(Util.messageConfigMsg(messages, "giveInvalidArgs"));
			}
			return true;
		}
		return false;
	}

}
