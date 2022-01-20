package com.baka632.arkadditions.init;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.entity.passive.RusthammerTrader;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<RusthammerTrader> RUSTHAMMER_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(ArkAdditions.MODID, "rusthammer_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RusthammerTrader::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    
    public static void init() {
        FabricDefaultAttributeRegistry.register(RUSTHAMMER_TRADER, RusthammerTrader.createMobAttributes());
    }
    
    private ModEntities(){
        
    }
}
