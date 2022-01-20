package com.baka632.arkadditions.init;

import com.baka632.arkadditions.client.renders.RusthammerTraderRender;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.RUSTHAMMER_TRADER, RusthammerTraderRender::new);
    }
   
}
