package com.baka632.arkadditions.item;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.entity.projectile.IndigoEnergy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class IndigoStaff extends Item {

    private int charge;
    private long lastChargeTime;
    private static final String CHARGE_NBT_STRING = "Charge";

    public IndigoStaff(Settings settings) {
        super(settings);
    }
    
    public void setLastChargeTime(long time){
        this.lastChargeTime = time;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, user.getSoundCategory(), 1.0f, 0.8f);
        user.getItemCooldownManager().set(this, 30);
        if (!world.isClient) {
            IndigoEnergy indigoEnergy = new IndigoEnergy(user,world);
            indigoEnergy.setVelocity(user, user.getPitch(), user.getYaw(), user.getRoll(), 2.5f, 4f);
            float damage = indigoEnergy.getDamage();
            float val = damage + (this.charge * 5);
            indigoEnergy.setDamage(val);
            world.spawnEntity(indigoEnergy);
            if (this.charge != 0) {
                this.charge--;
                ItemStack activeItem = user.getActiveItem();
                NbtCompound nbtCompound = activeItem.getOrCreateNbt();
                nbtCompound.putInt(CHARGE_NBT_STRING, this.charge);
                ArkAdditions.LOGGER.info("IndigoStaff充能状态-1,现在的充能状态:{}",this.charge);
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient) {
            return;
        }

        if (!world.isClient) {
            this.startCharge(world.getTime(), stack);
        }
    }

    private void startCharge(long currentWorldTime,ItemStack stack) {
        long checkTime = this.lastChargeTime + 120L;
        long time = currentWorldTime;
        if (time > checkTime && this.charge + 1 < 4) {
            NbtCompound nbtCompound = stack.getOrCreateNbt();
            this.charge++;
            this.lastChargeTime = currentWorldTime;
            nbtCompound.putInt(CHARGE_NBT_STRING, this.charge);
            nbtCompound.putLong("LastChargeTime", this.lastChargeTime);
            ArkAdditions.LOGGER.info("IndigoStaff充能状态+1,现在的充能状态:{}",this.charge);
        }
    }
}
