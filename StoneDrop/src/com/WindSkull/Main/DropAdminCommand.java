package com.WindSkull.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DropAdminCommand implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)  {
		
		if(args.length > 0)
		{
			if(args[0].equalsIgnoreCase("megadrop"))
			{
				BlockBreakListener.megaDrop = !BlockBreakListener.megaDrop;
				sender.sendMessage("Mega drop: " + (BlockBreakListener.megaDrop ? "On" : "Off"));
				return true;
				
			}
			else if(args[0].equalsIgnoreCase("drop"))
			{
				BlockBreakListener.drop = !BlockBreakListener.drop;
				sender.sendMessage("Drop: " + (BlockBreakListener.drop ? "On" : "Off"));
				return true;
			}
			else if(args[0].equalsIgnoreCase("gui"))
			{
				
			}
				
		}
		return false;
	}
	
}
