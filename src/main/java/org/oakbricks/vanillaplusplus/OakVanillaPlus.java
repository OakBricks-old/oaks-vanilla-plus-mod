package org.oakbricks.vanillaplusplus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import org.oakbricks.vanillaplusplus.block.BlockInit;
import org.oakbricks.vanillaplusplus.item.ItemInit;

public class OakVanillaPlus implements ModInitializer {

	public static final String MOD_ID = "vanillaplusplus";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "general"),
			() -> new ItemStack(ItemInit.TIN_SCRAP));

	//Standard stone ore gen for Tin
	private static ConfiguredFeature<?, ?> TIN_ORE_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, BlockInit.TIN_ORE.getDefaultState(), 10)) /* TODO: Make this configurable */
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(Integer.parseInt("0")), YOffset.fixed(Integer.parseInt("128")))))) /* TODO: Make this configurable */
			.spreadHorizontally()
			.repeat(Integer.parseInt("35"));

	private static ConfiguredFeature<?, ?> TIN_DEEPSLATE_ORE_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.DEEPSLATE), BlockInit.TIN_DEEPSLATE_ORE.getDefaultState(), 5))
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(Integer.parseInt("0")), YOffset.fixed(Integer.parseInt("32"))))))
			.spreadHorizontally()
			.repeat(Integer.parseInt("15"));


	@Override
	public void onInitialize() {

		RegistryKey<ConfiguredFeature<?, ?>> tinOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MOD_ID, "tin_ore_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, tinOreOverworld.getValue(), TIN_ORE_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, tinOreOverworld);

		RegistryKey<ConfiguredFeature <?, ?>> tinDeepslateOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MOD_ID, "tin_ore_deepslate_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, tinDeepslateOreOverworld.getValue(), TIN_DEEPSLATE_ORE_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, tinDeepslateOreOverworld);

		BlockInit.registerBlocks();
		ItemInit.registerItems();
		BlockInit.registerBlockItems();

	}
}
