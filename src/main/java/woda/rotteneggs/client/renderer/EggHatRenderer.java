package woda.rotteneggs.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import woda.rotteneggs.client.model.EggHatModel;
import woda.rotteneggs.common.item.RottenEggArmorItem;

public class EggHatRenderer extends GeoArmorRenderer<RottenEggArmorItem> {
    public EggHatRenderer() {
        super(new EggHatModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}