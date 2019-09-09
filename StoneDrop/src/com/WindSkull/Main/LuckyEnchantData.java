package com.WindSkull.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.datafixers.util.Pair;

@SuppressWarnings("unused")
public class LuckyEnchantData 
{
	private static final LuckyEnchantData singelton = new LuckyEnchantData();
	private LuckyEnchantData()
	{
		
	}
	
	public static LuckyEnchantData getLuckyEnchantData()
	{
		return singelton;
	}
	
	private final LuckyEnchantDataSet[] luckyLevels = new LuckyEnchantDataSet[]
		{
			new LuckyEnchantDataSet()
			.addWeightPiece(80d, new LuckyEnchantDataPiece("stone", "Kamieñ"))
			.addWeightPiece(15d, new LuckyEnchantDataPiece("coal", "Wêgiel"))
			.addWeightPiece(5d, new LuckyEnchantDataPiece("iron_ore", "¯elazo")),
			new LuckyEnchantDataSet()
			.addWeightPiece(50d, new LuckyEnchantDataPiece("stone", "Kamieñ"))
			.addWeightPiece(25d, new LuckyEnchantDataPiece("coal", "Wêgiel"))
			.addWeightPiece(15d, new LuckyEnchantDataPiece("iron_ore", "¯elazo"))
			.addWeightPiece(10d, new LuckyEnchantDataPiece("diamond", "Diament")),
			new LuckyEnchantDataSet()
			.addWeightPiece(100d, new LuckyEnchantDataPiece("diamond", "Diament"))
			
		};
	
	public LuckyEnchantDataSet[] getLuckyLevel()
	{
		return luckyLevels;
	}
	
	private class LuckyEnchantDataPiece
	{
		public LuckyEnchantDataPiece(String nameid, String name) 
		{
			super();
			this.nameid = nameid;
			this.name = name;
		}
		private final String nameid;
		private final String name;
		

		
		public ItemStack getItemStack()
		{
			return createItemStack();
		}
		
		private ItemStack createItemStack()
		{
			ItemStack i = new ItemStack(Material.matchMaterial(nameid),1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(name.replace("&", "§"));
			i.setItemMeta(im);
			return i;
		}
		
		public ItemStack getGuiItem(double d)
		{
			ItemStack i = new ItemStack(Material.matchMaterial(nameid),1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(ChatColor.GRAY+ name.replace("&", "§"));
			List<String> lore =new ArrayList<String>();
			lore.add(ChatColor.GRAY+ "Szansa na drop: "+ChatColor.GOLD + d);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		
		@Override
		public String toString()
		{
			return "LuckyEnchantDataPiece[id: "+ nameid + " name: " + name+"]";
		}
	}
	public class LuckyEnchantDataSet
	{
		private final Random random = new Random();
		
		private double weightMax = 0;
		private NavigableMap<Double, LuckyEnchantDataPiece> map = new TreeMap<Double, LuckyEnchantDataPiece>();
		
		public LuckyEnchantDataSet addWeightPiece(double weight, LuckyEnchantDataPiece piece)
		{
			if(weight <= 0) return this;
			if(weight > weightMax)
				weightMax = weight;
			map.put(weight, piece);
			System.out.println(toString());
			return this;
		}
		
		public List<ItemStack> getGuiItems()
		{
			return map.entrySet().stream().map(e -> e.getValue().getGuiItem(e.getKey())).collect(Collectors.toList());
		}
		
		public ItemStack pickItemStack()
		{
			double value = random.nextDouble() * weightMax;
			return map
					.higherEntry(value)
					.getValue().getItemStack();
		}
		
		
		@Override
		public String toString()
		{
			return map.toString();
		}
	}
	
	public ItemStack getItemStack(int luckyLevel)
	{
		return luckyLevels[Math.min(luckyLevels.length-1, Math.max(0,luckyLevel))].pickItemStack();
	}
	
}
