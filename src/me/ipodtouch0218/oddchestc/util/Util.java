package me.ipodtouch0218.oddchestc.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.ipodtouch0218.oddchestc.OddMain;
import me.ipodtouch0218.oddchestc.item.OddChest;

public class Util {

	static FileConfiguration config = OddMain.main;

	public static OddChest getRandomOddChest() {
		int amount = config.getKeys(false).size();
		Bukkit.broadcastMessage(config.getKeys(false).size() + "");
		int randomInt = (int) (((Math.random())*amount)+1);
		Bukkit.broadcastMessage(randomInt + "");
		int iterations = 0;
		for (String str : config.getKeys(false)) {
			Bukkit.broadcastMessage(str);
			iterations++;
			if (iterations == randomInt) {
				return new OddChest(str);
			}
		}
		return null;
	}
	
    public static String hideString(String input) {
        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            builder.append(ChatColor.COLOR_CHAR).append(c);
        }
        return builder.toString();
    }
    
    public static String messageConfigMsg(FileConfiguration cfg, String label, Player target, String item, Integer amount) {
		String msg = cfg.getString(label);
    	msg = ChatColor.translateAlternateColorCodes('&', msg);
    	if (label.equals("playerGivenItem")) {
    		msg = msg.replaceAll("%amount%", amount.toString());
    		msg = msg.replaceAll("%player%", target.getName());
    		msg = msg.replaceAll("%item%", item);
    	} else if (label.equals("playerInvFull")) {
    		msg = msg.replaceAll("%player%", target.getName());
    	}
    	return msg;
    }
    
    public static String messageConfigMsg(FileConfiguration cfg, String label, Player target) {
		String msg = cfg.getString(label);
    	msg = ChatColor.translateAlternateColorCodes('&', msg);
    	if (label.equals("playerGivenItem")) {
    		msg = msg.replaceAll("%player%", target.getName());
    	} else if (label.equals("playerInvFull")) {
    		msg = msg.replaceAll("%player%", target.getName());
    	}
    	return msg;
    }
    
    public static String messageConfigMsg(FileConfiguration cfg, String label) {
		String msg = cfg.getString(label);
    	return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
