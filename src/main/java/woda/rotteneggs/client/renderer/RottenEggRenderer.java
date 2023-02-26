package woda.rotteneggs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.rotteneggs.client.model.RottenEggModel;
import woda.rotteneggs.common.entity.RottenEggEntity;

public class RottenEggRenderer extends GeoEntityRenderer<RottenEggEntity> {

    public RottenEggRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RottenEggModel());
        //this.addLayer(new EggYolkLayer(this));
        this.shadowRadius = 0.4F;
    }

    @Override
    public RenderType getRenderType(RottenEggEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}