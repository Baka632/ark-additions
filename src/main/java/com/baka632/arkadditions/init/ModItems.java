package com.baka632.arkadditions.init;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.item.IndigoStaff;
import com.baka632.arkadditions.item.WondrousPostCard;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {
    //Mod icon item
    public static final Item MOD_ICON_ITEM = new Item(new FabricItemSettings().rarity(Rarity.RARE));

    //Item group
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
		new Identifier(ArkAdditions.MODID, "arkadditions_itemgroup")).icon(() -> new ItemStack(MOD_ICON_ITEM)).build();

    //Item
    public static final Item WONDROUS_POST_CARD = new WondrousPostCard(new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON));
    public static final Item ORIGINIUM_INGOT = new Item(new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON));
    public static final Item INDIGO_STAFF = new IndigoStaff(new FabricItemSettings().group(ITEM_GROUP).maxCount(1).rarity(Rarity.UNCOMMON));
    
    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(ArkAdditions.MODID, "mod_icon"), MOD_ICON_ITEM);
        Registry.register(Registry.ITEM, new Identifier(ArkAdditions.MODID, "wondrous_post_card"), WONDROUS_POST_CARD);
        Registry.register(Registry.ITEM, new Identifier(ArkAdditions.MODID, "originium_ingot"), ORIGINIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(ArkAdditions.MODID, "indigo_staff"), INDIGO_STAFF);
    }

    private ModItems(){
        
    }
}
