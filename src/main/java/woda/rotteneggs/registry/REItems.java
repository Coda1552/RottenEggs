package woda.rotteneggs.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.item.PotatoArmorItem;
import woda.rotteneggs.RottenEggs;
import woda.rotteneggs.common.item.RottenEggArmorItem;
import woda.rotteneggs.common.item.RottenEggArmorMaterial;

public class REItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RottenEggs.MOD_ID);

    public static RottenEggArmorMaterial EGG_HAT_ARMOR_MATERIAL = new RottenEggArmorMaterial("egg", 18,20, new int[]{1, 1, 1, 1}, SoundEvents.ARMOR_EQUIP_LEATHER, 0);

    public static final RegistryObject<RottenEggArmorItem> EGG_HAT = ITEMS.register("egg_hat",
            () -> new RottenEggArmorItem(EGG_HAT_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties()));
}
