package woda.rotteneggs;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import woda.rotteneggs.common.entity.RottenEggEntity;
import woda.rotteneggs.registry.REEntities;
import woda.rotteneggs.registry.REItems;
import woda.rotteneggs.registry.REParticles;

@Mod(RottenEggs.MOD_ID)
public class RottenEggs {
    public static final String MOD_ID = "rotteneggs";

    public RottenEggs() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        REEntities.ENTITIES.register(bus);
        REItems.ITEMS.register(bus);
        REParticles.PARTICLES.register(bus);

        bus.addListener(this::createEntityAttributes);
        forgeBus.addListener(this::rottenEggDeath);
    }

    private void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(REEntities.ROTTEN_EGG.get(), RottenEggEntity.createAttributes().build());
    }

    private void rottenEggDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getDirectEntity();
        LivingEntity target = event.getEntityLiving();

        if (source instanceof ThrownEgg && target instanceof RottenEggEntity) {
            target.level.addFreshEntity(new ItemEntity(target.level, target.getX(), target.getY(), target.getZ(), new ItemStack(REItems.EGG_HAT.get())));
        }
    }
}
