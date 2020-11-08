package com.zethria.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class Enderpearl implements Listener{
	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
private Main plugin;
	
	public Enderpearl(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	  public void teleport(PlayerTeleportEvent e) {
	    Player p = e.getPlayer();
	    double border = p.getWorld().getWorldBorder().getSize() / 2.0D;
	    if(plugin.getConfig().getBoolean("WorldBorderPearl.enabled") == true) {
		    if (p.getWorld().getWorldBorder() == null || e.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL)
			      return; 
		    
	    	  if (p.getWorld().getWorldBorder().getCenter().getX() + border < e.getTo().getX() || p.getWorld().getWorldBorder().getCenter().getX() - border > e.getTo().getX() || p.getWorld().getWorldBorder().getCenter().getZ() + border < e.getTo().getZ() || p.getWorld().getWorldBorder().getCenter().getZ() - border > e.getTo().getZ()) {
	    	      e.setCancelled(true);
	    	      p.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") + " &7Your enderpearl has been refunded!"));
	    	      p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_PEARL, 1) });
	    	    } 
	    }
	  }

}
