package woda.rotteneggs.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.rotteneggs.RottenEggs;

public class REParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RottenEggs.MOD_ID);

    public static final RegistryObject<SimpleParticleType> STINKY = PARTICLES.register("stinky", () -> new SimpleParticleType(true));
}
