package com.baka632.arkadditions.init;


import com.baka632.arkadditions.ArkAdditions;

import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

public class ModLootTables {
	private ModLootTables(){

	}

    public static void init(){
        //Listen to loot table events
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
			String scrTableId = id.toString();
			if (scrTableId.contains("village") && scrTableId.contains("chests")) {
                LootTable customTable = lootManager.getTable(new Identifier("arkadditions:chests/village_additions"));
				if (customTable != null) {
                    table.copyFrom(customTable);
                }
			}
		});
		ArkAdditions.LOGGER.info("Loot table load complete");
    }
}

