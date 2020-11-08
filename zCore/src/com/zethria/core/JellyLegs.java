package com.zethria.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JellyLegs implements CommandExecutor {
	
	public static List<String> Jellylegs = new ArrayList<>();
	
	private Main plugin;
	
	public JellyLegs(Main plugin) {
		this.plugin = plugin;
	}
	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Jellylegs") && !Jellylegs.contains(player.getName()) && player.hasPermission("zCore.jellylegs")) {
				Jellylegs.add(player.getName());
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") + 
						" &7You have enabled &b&nJellylegs&7!"));
				
			} else if (Jellylegs.contains(player.getName())) {
				Jellylegs.remove(player.getName());
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") + 
						" &7You have disabled &b&nJellylegs&7!"));
				
			} else if(!player.hasPermission("zCore.jellylegs")){
				player.sendMessage(color(plugin.getConfig().getString("Messages.Error")));
			}
		}
		return false;
	}

}
