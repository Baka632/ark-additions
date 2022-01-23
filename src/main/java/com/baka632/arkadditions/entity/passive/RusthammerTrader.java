package com.baka632.arkadditions.entity.passive;

import java.util.Iterator;

import com.baka632.arkadditions.utils.RusthammerTraderOffers;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.GoToWalkTargetGoal;
import net.minecraft.entity.ai.goal.LookAtCustomerGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.StopAndLookAtEntityGoal;
import net.minecraft.entity.ai.goal.StopFollowingCustomerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderNearTargetGoal;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers.Factory;
import net.minecraft.world.World;

public class RusthammerTrader extends MerchantEntity {

    private long lastRestockTime;
    private long lastRestockCheckTime;
    private boolean canDespawn = true;
    private int despawnDelay = 72000;

    private static final String CAN_DESPAWN_STRING = "CanDespawn";
    private static final String DESPAWN_DELAY_STRING = "DespawnDelay";
    private static final String LAST_RESTOCK_TIME_STRING = "LastRestock";

    public RusthammerTrader(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
        MobNavigation nav = (MobNavigation)this.getNavigation();
        nav.setCanPathThroughDoors(true);
        nav.setCanEnterOpenDoors(true);
        this.getNavigation().setCanSwim(true);
    }

    @Override
    protected void afterUsing(TradeOffer offer) {
        if (offer.shouldRewardPlayerExperience()) {
            int i = 3 + this.random.nextInt(4);
            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    @Override
    protected void fillRecipes() {
        Factory[] factorys = RusthammerTraderOffers.RUSTHAMMER_TRADER_TRADES.get(1);
        if (factorys != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factorys, 5);
        }
    }

    @Override
    public boolean isLeveledMerchant() {
        return false;
    }

    @Override
    public PassiveEntity createChild(ServerWorld arg0, PassiveEntity arg1) {
        return null;
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new StopFollowingCustomerGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<ZombieEntity>(this, ZombieEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<EvokerEntity>(this, EvokerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<VindicatorEntity>(this, VindicatorEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<VexEntity>(this, VexEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<PillagerEntity>(this, PillagerEntity.class, 15.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<IllusionerEntity>(this, IllusionerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<ZoglinEntity>(this, ZoglinEntity.class, 10.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5D));
        this.goalSelector.add(1, new LookAtCustomerGoal(this));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 2.0D, 0.35f));
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 0.35D));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 0.35D));
        this.goalSelector.add(9, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
     }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putLong(LAST_RESTOCK_TIME_STRING, this.lastRestockTime);
        nbt.putInt(DESPAWN_DELAY_STRING, this.despawnDelay);
        nbt.putBoolean(CAN_DESPAWN_STRING, this.canDespawn);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(DESPAWN_DELAY_STRING, 99)) {
            this.despawnDelay = nbt.getInt(DESPAWN_DELAY_STRING);
        }

        if (nbt.contains(CAN_DESPAWN_STRING)) {
            this.canDespawn = nbt.getBoolean(CAN_DESPAWN_STRING);
        }

        if (nbt.contains(LAST_RESTOCK_TIME_STRING)) {
            this.lastRestockTime = nbt.getLong(LAST_RESTOCK_TIME_STRING);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
           if (hand == Hand.MAIN_HAND) {
              player.incrementStat(Stats.TALKED_TO_VILLAGER);
           }
  
           if (this.getOffers().isEmpty()) {
              return ActionResult.success(this.world.isClient);
           }
           else {
              if (!this.world.isClient) {
                 this.setCurrentCustomer(player);
                 this.sendOffers(player, this.getDisplayName(), 1);
              }
  
              return ActionResult.success(this.world.isClient);
            }
        } else {
           return super.interactMob(player, hand);
        }
    }


    public int getDespawnDelay() {
        return this.despawnDelay;
    }

    public void setDespawnDelay(int delay) {
        this.despawnDelay = delay;
    }


    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.world.isClient && canDespawn) {
           this.tickDespawnDelay();
        }
    }

    private void tickDespawnDelay() {
        if (this.despawnDelay > 0 && !this.hasCustomer() && --this.despawnDelay == 0) {
           this.discard();
        }
        
        tryRestock();
    }

    private void tryRestock() {
        long lastPlusADay = this.lastRestockTime + 12000L;
        long time = this.world.getTime();
        boolean canRestock = time > lastPlusADay;
        long days = this.world.getTimeOfDay();
        if (this.lastRestockCheckTime > 0L) {
            long o = this.lastRestockCheckTime / 24000L;
            long p = days / 24000L;
            canRestock |= p > o;
        }

        this.lastRestockCheckTime = days;

        if(canRestock){
            this.lastRestockTime = time;
            restockAndUpdateDemandBonus();
            this.getOffers().clear();
            fillRecipes();
        }
    }

    private void restockAndUpdateDemandBonus() {
        Iterator<TradeOffer> offers = this.getOffers().iterator();
  
        while(offers.hasNext()) {
            TradeOffer tradeOffer = offers.next();
            tradeOffer.resetUses();
            tradeOffer.updateDemandBonus();
        }
     }
}
