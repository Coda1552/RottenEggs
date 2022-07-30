package woda.rotteneggs.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import woda.rotteneggs.common.item.RottenEggArmorItem;
import woda.rotteneggs.registry.REItems;
import woda.rotteneggs.registry.REParticles;
import woda.rotteneggs.registry.RESounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RottenEggEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> IS_SHEARED = SynchedEntityData.defineId(RottenEggEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> HAT_NUM = SynchedEntityData.defineId(RottenEggEntity.class, EntityDataSerializers.);
    public LinkedList<String> colours = new LinkedList<String>();
    public List<Integer> coloursID = new ArrayList<>();

    public RottenEggEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_SHEARED, false);
        this.entityData.define(HAT_NUM, 1);
    }

    public void setSheared(boolean sheared) {
        this.entityData.set(IS_SHEARED, sheared);
    }

    public boolean getSheared() {
        return this.entityData.get(IS_SHEARED);
    }

    public void setHatNum(int num) {
        this.entityData.set(HAT_NUM, num);
    }

    public int getHatNum() {
        return this.entityData.get(HAT_NUM);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.colours.add("normal");
        this.coloursID.add(0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putIntArray("colourId", this.coloursID);
        tag.putInt("hatNum", this.getHatNum());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        //System.out.println(tag.getIntArray("colourId"));
        this.setHatNum(tag.getInt("hatNum"));
        this.coloursID = Arrays.stream(tag.getIntArray("colourId")).boxed().collect(Collectors.toList());
        IDtoColour((ArrayList<Integer>) this.coloursID);
}

    public void IDtoColour(ArrayList<Integer> list){
        for(Integer ID: list){
            switch(ID){
                case 0:
                    this.colours.add("normal");
                    break;
                case 1:
                    this.colours.add("red");
                    break;
                case 2:
                    this.colours.add("blue");
                    break;
                case 3:
                    this.colours.add("white");
                    break;
                case 4:
                    this.colours.add("brown");
                    break;
                case 5:
                    this.colours.add("black");
                    break;
                case 6:
                    this.colours.add("green");
                    break;
            }
        }
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
    public void tick() {
        super.tick();
        System.out.println(coloursID);
        System.out.println(colours);
        if (random.nextDouble() > 0.65 && !getSheared()) {
            this.level.addParticle(REParticles.STINKY.get(), this.getRandomX(1.0D), this.getRandomY() + 0.15F, this.getRandomZ(1.0D), 0.0, 0.0, 0.0);
        }
        if(random.nextDouble() > 0.85 && getSheared()) {
            this.level.addParticle(REParticles.STINKY.get(), this.getRandomX(1.0D), this.getRandomY() + 0.15F, this.getRandomZ(1.0D), 0.0, 0.0, 0.0);
        }
        this.colours.clear();
        IDtoColour((ArrayList<Integer>) this.coloursID);
        System.out.println(this.colours);
        if(getHatNum() == 0){
            this.setSheared(true);
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

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return RESounds.EGG_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return RESounds.EGG_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return RESounds.EGG_DEATH.get();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(player.getItemInHand(hand).is(Items.SHEARS) && this.getHatNum() >= 1 && this.getHatNum() < 16 ){
            this.coloursID.remove(this.coloursID.toArray().length - 1);
            this.colours.removeLast();
            level.addFreshEntity(new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(REItems.EGG_HAT.get())));
            player.getItemInHand(hand).hurtAndBreak(1, player, (p_29822_) -> {
                p_29822_.broadcastBreakEvent(hand);
            });
            return InteractionResult.SUCCESS;
        }
        if(player.getItemInHand(hand).getItem() instanceof RottenEggArmorItem){
            RottenEggArmorItem hat = (RottenEggArmorItem) player.getItemInHand(hand).getItem();
            this.setSheared(false);
            this.setHatNum(getHatNum() + 1);
            this.colours.add(hat.getColour());
            this.coloursID.add(hat.getColourID());
            player.getItemInHand(hand).shrink(1 );
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
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
