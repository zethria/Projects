package com.zethria.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.FactionsPlugin;



public class Main extends JavaPlugin {
	
	private static Main mainInstance;
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Listeners(this), this);
		Bukkit.getPluginManager().registerEvents(new Sponges(this), this);
		Bukkit.getPluginManager().registerEvents(new Enderpearl(this), this);
		getCommand("Speed").setExecutor(new SpeedCommand(this));
		getCommand("Jellylegs").setExecutor(new JellyLegs(this));
		getCommand("Nightvision").setExecutor(new Nightvision(this));
		getCommand("Lff").setExecutor(new LFF(this));
		getCommand("Waterbreathing").setExecutor(new Waterbreathing(this));
		getCommand("Bless").setExecutor(new BlessCommand(this));
		getCommand("Poison").setExecutor(new PoisonCommand());
		Bukkit.getConsoleSender().sendMessage("§b--------------------");
		Bukkit.getConsoleSender().sendMessage("    §azCore enabled    ");
		Bukkit.getConsoleSender().sendMessage("    §fAuthor: §cZethria    ");
		Bukkit.getConsoleSender().sendMessage("§b--------------------");
		mainInstance = this;
		saveDefaultConfig();
		
		if(getApi() != null) {
			Bukkit.getConsoleSender().sendMessage("§a[zCore] §bFactions has been found!");
		}
	}
	
	public void onDisable() {
		
	}
	
	public static Main getMainInstance() {
		return mainInstance;
	}
	
	public FactionsPlugin getApi() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Factions");
		if(plugin instanceof FactionsPlugin) {
			return (FactionsPlugin) plugin;
		} else {
			return null;
		}
	}
}
