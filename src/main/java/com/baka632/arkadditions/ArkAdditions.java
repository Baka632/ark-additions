package com.baka632.arkadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import com.baka632.arkadditions.init.ModBlocks;
import com.baka632.arkadditions.init.ModEntities;
import com.baka632.arkadditions.init.ModItems;
import com.baka632.arkadditions.init.ModLootTables;
import com.baka632.arkadditions.init.ModStructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArkAdditions implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("ArkAdditions");
	public static final String MODID = "arkadditions";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Welcome from Ark Additions!");
		ModItems.init();
		ModBlocks.init();
		ModLootTables.init();
		ModEntities.init();
		ModStructures.init();
	}
}
