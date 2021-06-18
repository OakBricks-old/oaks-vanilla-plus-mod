package org.oakbricks.vanillaplusplus.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.oakbricks.vanillaplusplus.block.tin.TinOre;

import static org.oakbricks.vanillaplusplus.OakVanillaPlus.MOD_ID;

public class BlockInit {
    public static final Block TIN_ORE = new TinOre(FabricBlockSettings.copy(Blocks.STONE).strength(3.2f, 8.6f).requiresTool().sounds(BlockSoundGroup.STONE));
    public static final Block TIN_DEEPSLATE_ORE = new TinOre(FabricBlockSettings.copy(Blocks.DEEPSLATE).strength(6.2f, 10.6f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "tin_ore"), TIN_ORE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "tin_deepslate_ore"), TIN_DEEPSLATE_ORE);
    }
}
