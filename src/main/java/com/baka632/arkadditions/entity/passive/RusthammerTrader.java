package com.baka632.arkadditions.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;

public class RusthammerTrader extends MerchantEntity {

    public RusthammerTrader(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void afterUsing(TradeOffer offer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void fillRecipes() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PassiveEntity createChild(ServerWorld arg0, PassiveEntity arg1) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
