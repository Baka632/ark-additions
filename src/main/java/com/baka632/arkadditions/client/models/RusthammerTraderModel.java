package com.baka632.arkadditions.client.models;

import com.baka632.arkadditions.entity.passive.RusthammerTrader;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.util.math.MathHelper;

public class RusthammerTraderModel extends SinglePartEntityModel<RusthammerTrader> implements ModelWithHead {

   private final ModelPart root;
   private final ModelPart head;
   private final ModelPart rightLeg;
   private final ModelPart leftLeg;

   public RusthammerTraderModel(ModelPart root) {
      super();
      this.root = root;
      this.head = root.getChild("head");
      this.rightLeg = root.getChild("right_leg");
      this.leftLeg = root.getChild("left_leg");
   }

   public static ModelData getModelData() {
      ModelData modelData = new ModelData();
      ModelPartData modelPartData = modelData.getRoot();
      modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0), ModelTransform.NONE);
      ModelPartData modelPartData4 = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 20).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F), ModelTransform.NONE);
      modelPartData4.addChild("jacket", ModelPartBuilder.create().uv(0, 38).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.5F)), ModelTransform.NONE);
      modelPartData.addChild("arms", ModelPartBuilder.create().uv(44, 22).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).uv(44, 22).cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, true).uv(40, 38).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F), ModelTransform.of(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
      modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
      modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
      return modelData;
   }

   @Override
   public ModelPart getPart() {
      return this.root;
   }

   @Override
   public void setAngles(RusthammerTrader entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
      boolean bl = false;
      if (entity instanceof MerchantEntity) {
         bl = entity.getHeadRollingTimeLeft() > 0;
      }

      this.head.yaw = headYaw * 0.017F;
      this.head.pitch = headPitch * 0.017F;
      if (bl) {
         this.head.roll = 0.3F * MathHelper.sin(0.45F * animationProgress);
         this.head.pitch = 0.4F;
      } else {
         this.head.roll = 0.0F;
      }

      this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.67F) * 1.4F * limbDistance * 0.5F;
      this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.67F + 3.141592F) * 1.4F * limbDistance * 0.5F;
      this.rightLeg.yaw = 0.0F;
      this.leftLeg.yaw = 0.0F;
   }

   @Override
   public ModelPart getHead() {
      return this.head;
   }
}
