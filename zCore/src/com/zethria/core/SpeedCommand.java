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

public class SpeedCommand implements CommandExecutor {
	
	private Main plugin;
	
	public static List<String> speed = new ArrayList<>();
	public SpeedCommand() {
		
	}
	
	public SpeedCommand(Main plugin) {
		this.plugin = plugin;
	}

	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Speed") && !speed.contains(player.getName()) && player.hasPermission("zCore.speed")) {
				speed.add(player.getName());
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, plugin.getConfig().getInt("Settings.Speed")));
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") 
						+ " &7you have enabled the &bspeed &7effect"));

			} else if(speed.contains(player.getName())) {
				speed.remove(player.getName());
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") 
						+ " &7You have disabled the &bspeed &7effect"));
				player.removePotionEffect(PotionEffectType.SPEED);
				
			}
				else if(!player.hasPermission("zCore.speed")) { 
					player.sendMessage(color(plugin.getConfig().getString("Messages.Error")));
			}
		}
		
	
		return false;
	}

}
