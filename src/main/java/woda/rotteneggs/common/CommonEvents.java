package woda.rotteneggs.common;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.entity.RottenEggEntity;
import woda.rotteneggs.registry.REEntities;

@Mod.EventBusSubscriber(modid = RottenEggs.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(REEntities.ROTTEN_EGG.get(), RottenEggEntity.createAttributes().build());
    }
}
