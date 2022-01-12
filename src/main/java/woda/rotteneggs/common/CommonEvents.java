package woda.rotteneggs.common;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.entity.RottenEggEntity;
import woda.rotteneggs.registry.REEntities;

import java.util.Random;

@Mod.EventBusSubscriber(modid = RottenEggs.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void onEggLand(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        HitResult result = event.getRayTraceResult();
        Random rand = new Random();


        if (rand.nextInt(32) == 0 && projectile instanceof ThrownEgg egg && result.getType() == HitResult.Type.BLOCK) {
            RottenEggEntity rottenEgg = REEntities.ROTTEN_EGG.get().create(egg.level);
            rottenEgg.moveTo(egg.position());
            egg.level.addFreshEntity(rottenEgg);
        }
    }
}
