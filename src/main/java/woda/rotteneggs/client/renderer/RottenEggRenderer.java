package woda.rotteneggs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.RenderUtils;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.client.layer.EggYolkLayer;
import woda.rotteneggs.client.model.RottenEggModel;
import woda.rotteneggs.common.entity.RottenEggEntity;

public class RottenEggRenderer extends GeoEntityRenderer<RottenEggEntity> {

    public RottenEggRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RottenEggModel());
        //this.addLayer(new EggYolkLayer(this));
        this.shadowRadius = 0.4F;
    }

    @Override
    public void render(GeoModel model, RottenEggEntity animatable, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }

}