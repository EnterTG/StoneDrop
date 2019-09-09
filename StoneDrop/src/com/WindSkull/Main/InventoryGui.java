package com.WindSkull.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class InventoryGui implements InventoryHolder{

	protected Inventory inventory;
	protected Map<Integer, InventoryButton> allButtons = new HashMap<>();
	
	protected Player player;
	
	public InventoryGui(Player p)
	{
		this.player = p;
	}
	
	@Override
	public Inventory getInventory() 
	{
		return inventory;
	}
	
	public abstract boolean onInventoryClick(Player player,int slot,ItemStack item);
	public abstract boolean onInventoryOpen(Player player);
	public abstract boolean onInventoryClose(Player player);
	
	protected void setItem(int slot,ItemStack item,InventoryButton b)
	{
		setItem(slot, item);
		if(b != null)
			allButtons.put(slot, b);
	}
	protected void setItem(int slot,ItemStack item)
	{
		inventory.setItem(slot, item);
	}
	
	public InventoryButton getButtonAction(int slot)
	{
		return Optional.ofNullable(allButtons.get(slot)).orElse( e -> {});
	}
	
}
