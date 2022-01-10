package com.baka632.arkadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import com.baka632.arkadditions.init.ModLootTable;
import com.baka632.arkadditions.item.WondrousPostCard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArkAdditions implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("ArkAdditions");
	public static final String MODID = "arkadditions";

	//Item group
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MODID, "arkadditions_itemgroup")).icon(() -> new ItemStack(Items.BOWL)).build();

	//Items
	public static final Item WONDROUS_POST_CARD = new WondrousPostCard(new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("欢迎游玩ArkAdditions模组!");
		Registry.register(Registry.ITEM, new Identifier(MODID, "wondrous_post_card"), WONDROUS_POST_CARD);

		ModLootTable.init();
	}
}
