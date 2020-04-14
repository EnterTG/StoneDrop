package com.WindSkull.Main;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin{


	@Override
	public void onEnable()
	{
		try 
		{
			LuckyEnchantData.getLuckyEnchantData().loadData(this);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryActionListener(), this);
		
		this.getCommand("drop").setExecutor(new DropCommand());
		this.getCommand("dropadmin").setExecutor(new DropAdminCommand(this));
		
		
	}

}
