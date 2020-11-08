package com.zethria.core;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;


public class Sponges implements Listener {
	
private Main plugin;
	
	public Sponges(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(e.isCancelled())
			return;
		Player p = e.getPlayer();
		if(plugin.getConfig().getBoolean("Sponges.enabled")) {
			if(e.getBlock().getType() == Material.SPONGE) {
				FLocation flocation = new FLocation(e.getBlock().getLocation());
				Faction faction = Board.getInstance().getFactionAt(flocation);
				if(faction.isWilderness() || faction.isNormal()) {
					for(int radius = plugin.getConfig().getInt("Radius"), x = -radius; x <= radius; x++) {
						for(int y = -radius; y<= radius; y++) {
							for(int z = -radius; z<= radius; z++) {
								Location finalLoc = new Location(p.getWorld(), (e.getBlock().getX() +x), (e.getBlock().getY() + y), (e.getBlock().getZ() + z));
								 if (finalLoc.getBlock().getType() == Material.STATIONARY_LAVA || finalLoc
							              .getBlock().getType() == Material.LAVA || finalLoc
							              .getBlock().getType() == Material.WATER || finalLoc
							              .getBlock().getType() == Material.STATIONARY_WATER)
							              finalLoc.getBlock().setType(Material.AIR); 
							}
							
						}
					}	
					
				}
				
			}
		}
	}
	  
	  @SuppressWarnings("deprecation")
	@EventHandler
	  public void onHit(PlayerInteractEvent e) {
	    Player p = e.getPlayer();
	    FPlayer fp = FPlayers.getInstance().getByPlayer(p);
	    Faction fac = fp.getFaction();
	    if(plugin.getConfig().getBoolean("Sponges.enabled") == true) {
	    if (e.getClickedBlock() == null)
	      return; 
	    if (e.getAction() == Action.LEFT_CLICK_BLOCK && 
	      e.getClickedBlock().getType() == Material.SPONGE) {
	      Faction facLoc = Board.getInstance().getFactionAt(new FLocation(e.getClickedBlock()));
	      if (fac.getId().equals(facLoc.getId()) || facLoc.isNone()) {
	        p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), new ItemStack(Material.SPONGE));
	        e.getClickedBlock().setType(Material.AIR);
	        e.setCancelled(true);
	      } 
	    } }
	  }
	  
	  @EventHandler
	  private void onFlow(BlockFromToEvent evt) {
	    Block b = (Block) evt.getBlock();
	    if(plugin.getConfig().getBoolean("Sponges.enabled") == true) {
	    if( b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER) {
	    	int x = b.getX();
	    	int y = b.getY();
	    	int z = b.getZ();
	    	for(int flowX = -plugin.getConfig().getInt("Sponges.radius"); flowX <= plugin.getConfig().getInt("Sponges.radius"); flowX++) {
	    		for(int flowY = -plugin.getConfig().getInt("Sponges.radius"); flowY <= plugin.getConfig().getInt("Sponges.radius"); flowY++) {
	    			for(int flowZ = -plugin.getConfig().getInt("Sponges.radius"); flowZ <= plugin.getConfig().getInt("Sponges.radius"); flowZ++) {
	    				if (evt.getBlock().getWorld().getBlockAt(x + flowX, y + flowY, z + flowZ).getType().equals(Material.SPONGE))
	    		              evt.setCancelled(true);
	    			}
	    		}
	    	}
	    }
	    }
	    
}
}