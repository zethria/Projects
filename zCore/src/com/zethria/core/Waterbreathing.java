package com.zethria.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Waterbreathing implements CommandExecutor {
	
private Main plugin;
	
	public Waterbreathing(Main plugin) {
		this.plugin = plugin;
	}
	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static List<String> wb = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Waterbreathing") && !wb.contains(player.getName()) && player.hasPermission("zCore.waterbreathing")) {
				wb.add(player.getName());
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") 
						+ " &7You have enabled &bwaterbreathing!"));
				player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, plugin.getConfig().getInt("Settings.WaterBreathing")));
				
			} else if (wb.contains(player.getName())) {
				wb.remove(player.getName());
				player.removePotionEffect(PotionEffectType.WATER_BREATHING);
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") + " &7You have disabled &bwaterbreathing!"));
			} else if(!player.hasPermission("zCore.waterbreathing")) {
				player.sendMessage(color(plugin.getConfig().getString("Messages.Error")));
			}
		}
		
		return false;
	}

}
