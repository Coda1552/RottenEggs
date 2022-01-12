package woda.rotteneggs.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.example.GeckoLibMod;
import software.bernie.example.client.renderer.armor.PotatoArmorRenderer;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.client.renderer.EggHatRenderer;
import woda.rotteneggs.client.renderer.RottenEggRenderer;
import woda.rotteneggs.common.item.RottenEggArmorItem;
import woda.rotteneggs.registry.REEntities;

@Mod.EventBusSubscriber(modid = RottenEggs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(REEntities.ROTTEN_EGG.get(), RottenEggRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
            GeoArmorRenderer.registerArmorRenderer(RottenEggArmorItem.class, new EggHatRenderer());

    }
}
