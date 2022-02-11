package com.baka632.arkadditions.entity.projectile;

import com.baka632.arkadditions.init.ModEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class IndigoEnergy extends ThrownItemEntity{
    private float damage = 12f;

    public IndigoEnergy(EntityType<? extends IndigoEnergy> entityType, World world) {
        super(entityType, world);
    }

    public IndigoEnergy(LivingEntity owner, World world) {
        super(ModEntities.INDIGO_ENERGY, owner, world);
    }

    public IndigoEnergy(double x, double y, double z, World world) {
        super(ModEntities.INDIGO_ENERGY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.ENDER_PEARL;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity thrower = getOwner();
        Entity hit = entityHitResult.getEntity();

        if (thrower instanceof TameableEntity tameable) {
            if (hit instanceof TameableEntity entity && hasSameOwner(tameable, entity)) {
                this.kill();
                return;
            }
            
            if (hit instanceof LivingEntity entity && tameable.isOwner(entity)) {
                this.kill();
                return;
            }
        }

        if (thrower != null && !hit.equals(thrower)) {
            DamageSource source = DamageSource.thrownProjectile(this, this.getOwner());
            hit.damage(source, damage);
            this.kill();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
		if (!this.world.isClient) {
			this.world.sendEntityStatus(this, (byte)3);
			this.kill();
		}
    }

    private static boolean hasSameOwner(TameableEntity tameableA, TameableEntity tameableB) {
        if (tameableA.getOwnerUuid() == null) {
            return false;
        }
        if (tameableB.getOwnerUuid() == null) {
            return false;
        }
        return tameableA.getOwnerUuid() == tameableB.getOwnerUuid();
    }

    public float getDamage(){
        return damage;
    }

    public void setDamage(float val){
        damage = val;
    }
}
