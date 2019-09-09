package com.WindSkull.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryActionListener implements Listener{

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if(e.getInventory().getHolder() instanceof InventoryGui && e.getSlot() >= 0)
		{
			InventoryGui i = (InventoryGui) e.getInventory().getHolder();
			e.setCancelled(i.onInventoryClick((Player) e.getWhoClicked(), e.getSlot(), e.getInventory().getItem(e.getSlot())));
			i.getButtonAction(e.getSlot()).onClick(e);
			
		}
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e)
	{
		if(e.getInventory().getHolder() instanceof InventoryGui)
		{
			InventoryGui i = (InventoryGui) e.getInventory().getHolder();
			i.onInventoryOpen((Player) e.getPlayer());
		}
	}
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e)
	{
		if(e.getInventory().getHolder() instanceof InventoryGui)
		{
			InventoryGui i = (InventoryGui) e.getInventory().getHolder();
			i.onInventoryClose((Player) e.getPlayer());
		}
	}
}
