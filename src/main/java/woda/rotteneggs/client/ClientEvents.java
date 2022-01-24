package woda.rotteneggs.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.client.particle.StinkyParticle;
import woda.rotteneggs.client.renderer.EggHatRenderer;
import woda.rotteneggs.client.renderer.RottenEggRenderer;
import woda.rotteneggs.common.item.RottenEggArmorItem;
import woda.rotteneggs.registry.REEntities;
import woda.rotteneggs.registry.REParticles;

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

    @SubscribeEvent
    public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
        ParticleEngine engine = Minecraft.getInstance().particleEngine;
        engine.register(REParticles.STINKY.get(), StinkyParticle.Provider::new);
    }
}
