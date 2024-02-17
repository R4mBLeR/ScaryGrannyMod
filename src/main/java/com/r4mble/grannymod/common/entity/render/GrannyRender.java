package com.r4mble.grannymod.common.entity.render;

import com.r4mble.grannymod.GrannyMod;
import com.r4mble.grannymod.common.entity.custom.GrannyEntity;
import com.r4mble.grannymod.common.entity.model.GrannyModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GrannyRender extends MobRenderer<GrannyEntity, GrannyModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(GrannyMod.MOD_ID, "textures/entity/granny.png");

    public GrannyRender(EntityRendererManager manager) {
        super(manager, new GrannyModel(), 0.5f);
    }


    @Override
    public ResourceLocation getTextureLocation(GrannyEntity grannyEntity) {
        return TEXTURE;
    }
}
