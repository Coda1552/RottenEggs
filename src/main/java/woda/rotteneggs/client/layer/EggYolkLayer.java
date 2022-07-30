package woda.rotteneggs.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.RenderUtils;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.entity.RottenEggEntity;

public class EggYolkLayer extends GeoLayerRenderer {
    private static final ResourceLocation MODEL = new ResourceLocation(RottenEggs.MOD_ID, "geo/entity/egg_yolk.geo.json");
    ResourceLocation TEXTURE;
    @SuppressWarnings("unchecked")
    public EggYolkLayer(IGeoRenderer<?> entityRendererIn) {
        super(entityRendererIn);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity e, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            GeoModel model = this.getEntityModel().getModel(MODEL);
            for (GeoBone group : model.topLevelBones) {
                renderRecursively(group, (RottenEggEntity) e, matrixStackIn, bufferIn, packedLightIn, partialTicks);
            }
    }

    private void renderRecursively(GeoBone bone, RottenEggEntity e, PoseStack matrixStackIn, MultiBufferSource renderBuffer, int packedLightIn, float partialTicks) {

        matrixStackIn.pushPose();
        RenderUtils.translate(bone, matrixStackIn);
        RenderUtils.moveToPivot(bone, matrixStackIn);
        RenderUtils.rotate(bone, matrixStackIn);
        RenderUtils.scale(bone, matrixStackIn);
        RenderUtils.moveBackFromPivot(bone, matrixStackIn);
        if(bone.getName().equals("yolk")){
            RottenEggEntity entity = e;
            if(!entity.colours.isEmpty()) {
                for (int i = 0; i < entity.colours.toArray().length; i++) {
                    TEXTURE = new ResourceLocation(RottenEggs.MOD_ID, "textures/armor/egg_hat_"+ entity.coloursID.get(i)+".png");
                    RenderType cameo =  RenderType.armorCutoutNoCull(TEXTURE);
                    matrixStackIn.pushPose();
                    //matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(entity.getYRot()));
                    matrixStackIn.scale(0.96f, 0.96f, 0.96f);
                    matrixStackIn.translate(0.0d, 0.2d + (i * 0.186f), 0.01d);
                    this.getRenderer().render(this.getEntityModel().getModel(MODEL), entity, partialTicks, cameo, matrixStackIn, renderBuffer,
                            renderBuffer.getBuffer(cameo), packedLightIn, OverlayTexture.RED_OVERLAY_V, 1f, 1f, 1f, 1f);
                    matrixStackIn.popPose();
                }
            }
        }

        if (!bone.isHidden) {
            for (GeoBone childBone : bone.childBones) {
                renderRecursively(childBone, e ,  matrixStackIn, renderBuffer, packedLightIn, partialTicks);
            }
        }

        matrixStackIn.popPose();
    }
}
