package woda.rotteneggs.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.client.renderer.RottenEggRenderer;
import woda.rotteneggs.registry.REEntities;

@Mod.EventBusSubscriber(modid = RottenEggs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(REEntities.ROTTEN_EGG.get(), RottenEggRenderer::new);

    }
}
