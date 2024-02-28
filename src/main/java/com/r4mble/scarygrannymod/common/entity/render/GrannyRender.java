package com.r4mble.scarygrannymod.common.entity.render;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import com.r4mble.scarygrannymod.common.entity.custom.GrannyEntity;
import com.r4mble.scarygrannymod.common.entity.model.GrannyModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GrannyRender extends MobRenderer<GrannyEntity, GrannyModel<GrannyEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(ScaryGrannyMod.MOD_ID, "textures/entity/granny.png");

    public GrannyRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GrannyModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(GrannyEntity entity) {
        return TEXTURE;
    }
}
