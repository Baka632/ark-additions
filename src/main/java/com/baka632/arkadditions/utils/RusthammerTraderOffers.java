package com.baka632.arkadditions.utils;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.village.TradeOffers.Factory;

import java.util.Map;

import com.baka632.arkadditions.init.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class RusthammerTraderOffers {
    public static final Int2ObjectMap<Factory[]> RUSTHAMMER_TRADER_TRADES;

    private RusthammerTraderOffers(){

    }

    static{
        //TODO:Add MORE things!
        Factory[] factories = new Factory[]{
            getRusthammerTraderTradeFactory(Items.TOTEM_OF_UNDYING, 16, 1, 5, 10),
            getRusthammerTraderTradeFactory(ModItems.WONDROUS_POST_CARD, 3, 2, 20, 10),
            getRusthammerTraderTradeFactory(Items.NETHERITE_INGOT, 20, 4, 10, 20),
            getRusthammerTraderTradeFactory(Items.GOLDEN_APPLE, 4, 2, 10, 10)
        };
        RUSTHAMMER_TRADER_TRADES = copyToFastUtilMap(Map.of(1, factories));
    }

    private static Int2ObjectMap<Factory[]> copyToFastUtilMap(Map<Integer, Factory[]> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }

    private static RusthammerTraderTradeFactory getRusthammerTraderTradeFactory(Item item,int price,int count,int maxUses,int experience){
        return new RusthammerTraderTradeFactory(item, price, count, maxUses, experience);
    }
}
