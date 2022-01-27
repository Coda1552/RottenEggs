package woda.rotteneggs.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.rotteneggs.RottenEggs;

public class RESounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RottenEggs.MOD_ID);

    public static final RegistryObject<SoundEvent> EGG_AMBIENT = SOUNDS.register("egg.ambient", () -> new SoundEvent(new ResourceLocation(RottenEggs.MOD_ID, "egg.ambient")));
    public static final RegistryObject<SoundEvent> EGG_HURT = SOUNDS.register("egg.hurt", () -> new SoundEvent(new ResourceLocation(RottenEggs.MOD_ID, "egg.hurt")));
    public static final RegistryObject<SoundEvent> EGG_DEATH = SOUNDS.register("egg.death", () -> new SoundEvent(new ResourceLocation(RottenEggs.MOD_ID, "egg.death")));


}