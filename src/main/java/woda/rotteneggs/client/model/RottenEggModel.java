package woda.rotteneggs.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.entity.RottenEggEntity;

public class RottenEggModel extends AnimatedTickingGeoModel<RottenEggEntity> {

    @Override
    public ResourceLocation getModelLocation(RottenEggEntity object) {
        //if(object.getSheared()){
            return new ResourceLocation(RottenEggs.MOD_ID, "geo/entity/sheared_egg.geo.json");
        //}
        //return new ResourceLocation(RottenEggs.MOD_ID, "geo/entity/rotten_egg.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RottenEggEntity object) {
        //if(object.getSheared()){
            return new ResourceLocation(RottenEggs.MOD_ID, "textures/entity/sheared_egg.png");
        //}
        //return new ResourceLocation(RottenEggs.MOD_ID, "textures/entity/rotten_egg.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RottenEggEntity animatable) {
        return new ResourceLocation(RottenEggs.MOD_ID, "animations/entity/rotten_egg.animations.json");
    }
}
