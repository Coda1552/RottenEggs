package woda.rotteneggs;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import woda.rotteneggs.common.entity.RottenEggEntity;
import woda.rotteneggs.registry.REEntities;
import woda.rotteneggs.registry.REItems;

@Mod(RottenEggs.MOD_ID)
public class RottenEggs {
    public static final String MOD_ID = "rotteneggs";

    public RottenEggs() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        REEntities.ENTITIES.register(bus);
        REItems.ITEMS.register(bus);

        bus.addListener(this::createEntityAttributes);
    }

    private void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(REEntities.ROTTEN_EGG.get(), RottenEggEntity.createAttributes().build());
    }
}
