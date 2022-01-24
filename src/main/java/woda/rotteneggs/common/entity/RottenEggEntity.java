package woda.rotteneggs.common.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import woda.rotteneggs.registry.REItems;
import woda.rotteneggs.registry.REParticles;

public class RottenEggEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public RottenEggEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Player.class, 6F, 1, 1.2));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0F, 40));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Fox.class, 6.0F, 2.0D, 1.2D));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ATTACK_DAMAGE, 0.5D).add(Attributes.MAX_HEALTH, 6.0D);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.isProjectile() && source.getDirectEntity() instanceof ThrownEgg) {
            System.out.println(source.getEntity());
            return hurt(DamageSource.thrown(source.getEntity(), null), 2.0F);
        }
        else {
            return super.hurt(source, amount);
        }
    }

    @Override
    public void thunderHit(ServerLevel world, LightningBolt p_19928_) {
        level.addFreshEntity(new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(REItems.EGG_HAT.get())));
        discard();
        super.thunderHit(world, p_19928_);
    }

    @Override
    public void tick() {
        super.tick();

        if (random.nextDouble() > 0.75) {
            this.level.addParticle(REParticles.STINKY.get(), this.getRandomX(1.0D), this.getRandomY() + 0.15F, this.getRandomZ(1.0D), 0.0, 0.0, 0.0);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.egg.walk", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.egg.idle", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }
}
