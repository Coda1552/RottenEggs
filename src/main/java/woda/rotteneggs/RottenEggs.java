package woda.rotteneggs;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import woda.rotteneggs.common.entity.RottenEggEntity;
import woda.rotteneggs.registry.REEntities;
import woda.rotteneggs.registry.REItems;
import woda.rotteneggs.registry.REParticles;
import woda.rotteneggs.registry.RESounds;

@Mod(RottenEggs.MOD_ID)
public class RottenEggs {
    public static final String MOD_ID = "rotteneggs";

    public RottenEggs() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        REEntities.ENTITIES.register(bus);
        REItems.ITEMS.register(bus);
        REParticles.PARTICLES.register(bus);
        RESounds.SOUNDS.register(bus);

        bus.addListener(this::createEntityAttributes);
        forgeBus.addListener(this::rottenEggDeath);
        forgeBus.addListener(this::newAI);
    }

    private void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(REEntities.ROTTEN_EGG.get(), RottenEggEntity.createAttributes().build());
    }

    private void rottenEggDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity target = event.getEntityLiving();
        if(target instanceof RottenEggEntity){
            for(byte i = 0; i < 18; i++) {
                target.level.addParticle(REParticles.STINKY.get(), target.getRandomX(2.0D), target.getBlockY() + target.getRandom().nextFloat(), target.getRandomZ(2.0D), 0, 0.08d,0);
            }
        }
        if(source instanceof Fox){
            ((Fox) source).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1, false, true));
            ((Fox) source).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 1, false, true));
            ((Fox) source).addEffect(new MobEffectInstance(MobEffects.HUNGER, 80, 1, false, true));
        }
    }

    private void newAI(EntityJoinWorldEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Chicken){
            ((PathfinderMob) entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((PathfinderMob) entity, RottenEggEntity.class, 6.0F, 1.0D, 1.2D));
        }
        if(entity instanceof Fox){
            ((PathfinderMob) entity).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>((PathfinderMob) entity, RottenEggEntity.class, 0, true, false, null));
        }
        if(entity instanceof Monster){
            ((PathfinderMob) entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((PathfinderMob) entity, RottenEggEntity.class, 6.0F, 1.0D, 1.2D));
        }
    }

}
