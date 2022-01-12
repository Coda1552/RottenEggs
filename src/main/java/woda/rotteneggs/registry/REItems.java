package woda.rotteneggs.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.rotteneggs.RottenEggs;

public class REItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RottenEggs.MOD_ID);

    public static final RegistryObject<Item> ROTTEN_EGG_SPAWN_EGG = ITEMS.register("rotten_egg_spawn_egg", () -> new ForgeSpawnEggItem(REEntities.ROTTEN_EGG, 0xfbfcfc, 0xfff167, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

}
