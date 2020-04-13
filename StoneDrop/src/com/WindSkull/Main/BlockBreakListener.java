package com.WindSkull.Main;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BlockBreakListener implements Listener{


	public static boolean megaDrop = false;
	public static boolean drop = true;
	
	
	
	private static final List<Material> drops = Arrays.asList(Material.STONE,Material.COAL_ORE,Material.IRON_ORE,
			Material.GOLD_ORE,Material.DIAMOND_ORE,Material.EMERALD_ORE,Material.LAPIS_ORE,Material.REDSTONE_ORE);
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		e.setDropItems(false);
		if(drop)
		{
			Block block = e.getBlock();
			if(drops.contains(block.getType()))
			{
				
				ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
				LuckyEnchantData luck = LuckyEnchantData.getLuckyEnchantData();
				
				ItemStack drop = megaDrop ? luck.getItemStack(999) : luck.getItemStack(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS));
				
				Location loc =block.getLocation();
				dropToPlayerInv(loc,e.getPlayer(),drop);
				return;
			}
		}
		Collection<ItemStack> ic = e.getBlock().getDrops();
		dropToPlayerInv(e.getBlock().getLocation(),e.getPlayer(),(ItemStack[] )ic.toArray(new ItemStack[ic.size()]));
	}
	
	private void dropToPlayerInv(Location dropLoc,Player p,ItemStack... items)
	{
		PlayerInventory pi = p.getInventory();
		pi.addItem(items).entrySet().forEach( e -> dropLoc.getWorld().dropItem(dropLoc, e.getValue()));
	}

}
