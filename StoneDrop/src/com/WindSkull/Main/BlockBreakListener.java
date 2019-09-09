package com.WindSkull.Main;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener{


	public static boolean megaDrop = false;
	public static boolean drop = true;
	
	private static final List<Material> drops = Arrays.asList(Material.STONE,Material.IRON_ORE);
	
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
				loc.getWorld().dropItem(loc, drop);
			}
		}
	}

}
