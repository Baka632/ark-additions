package com.baka632.arkadditions.item;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class WondrousPostCard extends Item {

    public WondrousPostCard(Settings settings) {
        super(settings);
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.arkadditions.wondrous_post_card.tooltip"));
        tooltip.add(new TranslatableText("item.arkadditions.wondrous_post_card.tooltip.tradable").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
    }
}
