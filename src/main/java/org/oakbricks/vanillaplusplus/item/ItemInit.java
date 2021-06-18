package org.oakbricks.vanillaplusplus.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.oakbricks.vanillaplusplus.OakVanillaPlus;

import static org.oakbricks.vanillaplusplus.OakVanillaPlus.MOD_ID;

public class ItemInit {

    public static final Item TIN_SCRAP = new Item(new FabricItemSettings().group(OakVanillaPlus.ITEM_GROUP));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tin_scrap"), TIN_SCRAP);
    }

}
