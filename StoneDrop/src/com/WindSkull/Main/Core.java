package com.WindSkull.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin{

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	
		if(args.length > 0)
		{
			if(args[0].equalsIgnoreCase("magedrop"))
			{
				BlockBreakListener.megaDrop = !BlockBreakListener.megaDrop;
				sender.sendMessage("Mega drop: " + (BlockBreakListener.megaDrop ? "On" : "Off"));
				
			}
			else if(args[0].equalsIgnoreCase("drop"))
			{
				BlockBreakListener.drop = !BlockBreakListener.drop;
				sender.sendMessage("Drop: " + (BlockBreakListener.drop ? "On" : "Off"));
			}
			else if(args[0].equalsIgnoreCase("gui"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player)sender;
					p.openInventory(new DropGui(p).getInventory());
					
				}
			}
				
		}
		return super.onCommand(sender, command, label, args);
	}

	@Override
	public void onEnable()
	{
		this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryActionListener(), this);
	}

}
