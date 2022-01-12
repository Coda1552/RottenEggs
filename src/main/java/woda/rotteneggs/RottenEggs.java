package woda.rotteneggs;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import woda.rotteneggs.registry.REEntities;

@Mod(RottenEggs.MOD_ID)
public class RottenEggs {
    public static final String MOD_ID = "rotteneggs";

    public RottenEggs() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        REEntities.ENTITIES.register(bus);
    }
}
