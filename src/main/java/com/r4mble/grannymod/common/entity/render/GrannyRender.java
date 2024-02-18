package com.r4mble.grannymod.common.entity.render;

import com.r4mble.grannymod.GrannyMod;
import com.r4mble.grannymod.common.entity.custom.GrannyEntity;
import com.r4mble.grannymod.common.entity.model.GrannyModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GrannyRender extends MobRenderer<GrannyEntity, GrannyModel<GrannyEntity>>{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(GrannyMod.MOD_ID, "textures/entity/granny.png");

    public GrannyRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GrannyModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(GrannyEntity entity) {
        return TEXTURE;
    }
}
