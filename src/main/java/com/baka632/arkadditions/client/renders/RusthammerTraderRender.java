package com.baka632.arkadditions.client.renders;

import com.baka632.arkadditions.ArkAdditions;
import com.baka632.arkadditions.client.models.RusthammerTraderModel;
import com.baka632.arkadditions.entity.passive.RusthammerTrader;

import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RusthammerTraderRender extends MobEntityRenderer<RusthammerTrader, RusthammerTraderModel> {
    private static final Identifier TEXTURE = new Identifier(ArkAdditions.MODID, "textures/entity/rusthammer_trader/rusthammer_trader.png");

    public RusthammerTraderRender(Context context) {
        super(context, new RusthammerTraderModel(context.getPart(EntityModelLayers.WANDERING_TRADER)), 0.5f);
        this.addFeature(new HeadFeatureRenderer(this, context.getModelLoader()));
        this.addFeature(new VillagerHeldItemFeatureRenderer(this));
    }
 
    @Override
    public Identifier getTexture(RusthammerTrader entity) {
        return TEXTURE;
    }
  
    @Override
    protected void scale(RusthammerTrader entity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
