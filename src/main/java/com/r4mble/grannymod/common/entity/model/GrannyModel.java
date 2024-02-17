package com.r4mble.grannymod.common.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.r4mble.grannymod.common.entity.custom.GrannyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GrannyModel extends EntityModel<GrannyEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public GrannyModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 24.0F, 0.0F);
		head.texOffs(0, 0).addBox(-5.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(16, 16).addBox(-5.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(0.0F, 24.0F, 0.0F);
		rightArm.texOffs(32, 48).addBox(-9.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(0.0F, 24.0F, 0.0F);
		leftArm.texOffs(40, 16).addBox(3.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(0.0F, 24.0F, 0.0F);
		rightLeg.texOffs(16, 48).addBox(-5.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(0.0F, 24.0F, 0.0F);
		leftLeg.texOffs(16, 48).addBox(-1.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}
	@Override
	public void setupAnim(GrannyEntity entityIn, float limbSwing, float limbSwingAmount,
								  float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.xRot = headPitch * 0.017453292F;
		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
	}
	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}