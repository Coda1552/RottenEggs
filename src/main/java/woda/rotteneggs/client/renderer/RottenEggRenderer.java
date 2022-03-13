package woda.rotteneggs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.rotteneggs.client.layer.EggYolkLayer;
import woda.rotteneggs.client.model.RottenEggModel;
import woda.rotteneggs.common.entity.RottenEggEntity;

public class RottenEggRenderer extends GeoEntityRenderer<RottenEggEntity> {

    public RottenEggRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RottenEggModel());
        this.addLayer(new EggYolkLayer(this));
        this.shadowRadius = 0.4F;
    }

}