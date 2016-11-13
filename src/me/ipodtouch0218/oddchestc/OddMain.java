package me.ipodtouch0218.oddchestc;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import me.ipodtouch0218.oddchestc.events.EntityListener;
import me.ipodtouch0218.oddchestc.events.PlayerListener;

public class OddMain extends JavaPlugin {
	
	public static FileConfiguration main;
	
	private File messageFile = new File(getDataFolder() + "/messages.yml");
	private FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messageFile);
	
	private File entityFile = new File(getDataFolder() + "/entities.yml");
	private FileConfiguration entityConfig = YamlConfiguration.loadConfiguration(entityFile);
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		saveDefaultConfigs();
		main = getConfig();
		getCommand("oddchest").setExecutor(new CommandOddchest(messageConfig));
		getServer().getPluginManager().registerEvents(new EntityListener(entityConfig), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	@Override
	public void onDisable() {
		saveDefaultConfigs();
	}
	
	public void saveDefaultConfigs() {
	    if (!entityFile.exists()) {
	    	saveResource("entities.yml", false);
	    }
	    if (!messageFile.exists()) {
	    	saveResource("messages.yml", false);
	    }
	}
}
