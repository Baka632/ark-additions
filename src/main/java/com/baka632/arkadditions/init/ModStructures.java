package com.baka632.arkadditions.init;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.world.feature.LighthouseFeature;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.PlainsVillageData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.RegistryKey;

public class ModStructures {
    public static final StructureFeature<StructurePoolFeatureConfig> LIGHTHOUSE = new LighthouseFeature(
        StructurePoolFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> CONFIGURED_LIGHTHOUSE = LIGHTHOUSE
        .configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));

    public static void init(){
        //Lighthouse
        FabricStructureBuilder.create(new Identifier(ArkAdditions.MODID, "lighthouse"), LIGHTHOUSE)
        .step(GenerationStep.Feature.SURFACE_STRUCTURES)
        .defaultConfig(new StructureConfig(20, 10, 114514018))
        .adjustsSurface()
        .register();

        //Lighthouse register
        Registry.register(
            BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE,
            new Identifier(ArkAdditions.MODID, "configured_lighthouse"), CONFIGURED_LIGHTHOUSE);
        
        addStructureSpawningToDimensionsAndBiomes();
    }

    private static void addStructureSpawningToDimensionsAndBiomes() {
        BiomeModifications.addStructure(
                BiomeSelectors.categories(Biome.Category.BEACH),
                RegistryKey.of(
                        Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                        BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(CONFIGURED_LIGHTHOUSE))
        );
    }

    private ModStructures(){

    }
}
