package woda.rotteneggs.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.item.RottenEggArmorItem;

public class EggHatModel extends AnimatedGeoModel<RottenEggArmorItem> {
    @Override
    public ResourceLocation getModelLocation(RottenEggArmorItem object) {
        return new ResourceLocation(RottenEggs.MOD_ID, "geo/armor/egghat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RottenEggArmorItem object) {
        return new ResourceLocation(RottenEggs.MOD_ID, "textures/armor/egghat.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RottenEggArmorItem animatable) {
        return new ResourceLocation(RottenEggs.MOD_ID, "animations/armor/egghat.animations.json");
    }
}