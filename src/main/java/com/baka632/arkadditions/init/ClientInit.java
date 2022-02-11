package com.baka632.arkadditions.init;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.client.renders.RusthammerTraderRender;
import com.baka632.arkadditions.item.IndigoStaff;

import org.apache.commons.lang3.NotImplementedException;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{
    private static final String CHARGE_NBT_STRING = "Charge";

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.RUSTHAMMER_TRADER, RusthammerTraderRender::new);

        EntityRendererRegistry.register(ModEntities.INDIGO_ENERGY, FlyingItemEntityRenderer::new);

        FabricModelPredicateProviderRegistry.register(ModItems.INDIGO_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity,intVal) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            else{
                NbtCompound nbt = itemStack.getOrCreateNbt();
                if (nbt != null && nbt.contains(CHARGE_NBT_STRING)) {                   
                    return nbt.getInt(CHARGE_NBT_STRING);
                }
                return 0.0F;
            }
        });
    }
}
