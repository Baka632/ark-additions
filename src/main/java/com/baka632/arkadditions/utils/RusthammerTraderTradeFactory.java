package com.baka632.arkadditions.utils;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers.Factory;
import com.baka632.arkadditions.init.ModItems;

public class RusthammerTraderTradeFactory implements Factory {
    private final ItemStack sell;
    private final int price;
    private final int count;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public RusthammerTraderTradeFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
        this.sell = stack;
        this.price = price;
        this.count = count;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = multiplier;
     }

    public RusthammerTraderTradeFactory(Block block, int price, int count, int maxUses, int experience) {
        this(new ItemStack(block), price, count, maxUses, experience);
    }
  
    public RusthammerTraderTradeFactory(Item item, int price, int count, int experience) {
        this((new ItemStack(item)), price, count, 12, experience);
    }
  
    public RusthammerTraderTradeFactory(Item item, int price, int count, int maxUses, int experience) {
        this(new ItemStack(item), price, count, maxUses, experience);
    }
  
    public RusthammerTraderTradeFactory(ItemStack stack, int price, int count, int maxUses, int experience) {
        this(stack, price, count, maxUses, experience, 0.05F);
    }

    @Override
    public TradeOffer create(Entity arg0, Random arg1) {
        return new TradeOffer(new ItemStack(ModItems.ORIGINIUM_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
    }
    
}
