package com.WindSkull.Main;

import java.util.List;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.WindSkull.Main.LuckyEnchantData.LuckyEnchantDataSet;

public class DropGui extends InventoryGui{

	private int page = 0;
	
	
	public DropGui(Player p)
	{
		super(p);
		createInventory();
	}
	
	private void createInventory()
	{
		if(inventory == null)inventory = Bukkit.createInventory(this, 9,"Drop gui");
		inventory.clear();
		allButtons.clear();
		LuckyEnchantDataSet[] lucky = LuckyEnchantData.getLuckyEnchantData().getLuckyLevel();
		IntStream.range(0, lucky.length).forEach(i -> {
			this.setItem(i, getLuckItemStack(i), e -> openGuiLeve(lucky[i]));
			
		});
		
	}
	
	public ItemStack getLuckItemStack(int index)
	{
		ItemStack i = new ItemStack(Material.BOOK,1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GRAY + "Drop lvel: " + ChatColor.GOLD + index);
		i.setItemMeta(im);
		return i;
	}
	
	private void openGuiLeve(LuckyEnchantDataSet lucky)
	{
		inventory.clear();
		allButtons.clear();
		List<ItemStack> items= lucky.getGuiItems();
		IntStream.range(0, items.size()).forEach(i -> {
			this.setItem(i, items.get(i));
			
		});
		
		this.setItem(8, new ItemStack(Material.BARRIER), e -> createInventory());
	}
	
	@Override
	public boolean onInventoryClick(Player player, int slot, ItemStack item) {
		return true;
	}

	@Override
	public boolean onInventoryOpen(Player player) {
		return false;
	}

	@Override
	public boolean onInventoryClose(Player player) {
		return false;
	}



}
