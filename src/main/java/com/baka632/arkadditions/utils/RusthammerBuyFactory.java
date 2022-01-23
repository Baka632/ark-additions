package com.baka632.arkadditions.utils;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers.Factory;
import com.baka632.arkadditions.init.ModItems;

public class RusthammerBuyFactory implements Factory {
    private final Item buy;
    private final int price;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public RusthammerBuyFactory(ItemConvertible item, int price, int maxUses, int experience) {
        this.buy = item.asItem();
        this.price = price;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
     }

    @Override
    public TradeOffer create(Entity arg0, Random arg1) {
        ItemStack itemStack = new ItemStack(this.buy, this.price);
        return new TradeOffer(itemStack, new ItemStack(ModItems.ORIGINIUM_INGOT), this.maxUses, this.experience, this.multiplier);
    }
}

