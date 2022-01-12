package woda.rotteneggs.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.entity.RottenEggEntity;

public class REEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RottenEggs.MOD_ID);

    public static final RegistryObject<EntityType<RottenEggEntity>> ROTTEN_EGG = create("rotten_egg", EntityType.Builder.of(RottenEggEntity::new, MobCategory.MONSTER).sized(0.6f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(RottenEggs.MOD_ID + "." + name));
    }
}
