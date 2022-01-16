package com.baka632.arkadditions.init;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.item.WondrousPostCard;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item WONDROUS_POST_CARD = new WondrousPostCard(new FabricItemSettings().group(ArkAdditions.ITEM_GROUP).rarity(Rarity.UNCOMMON));
    
    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(ArkAdditions.MODID, "wondrous_post_card"), WONDROUS_POST_CARD);
    }
    
    private ModItems(){
        
    }
}
