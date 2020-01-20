package hugman.mubble.objects.world.biome;

import com.google.common.collect.ImmutableList;

import hugman.mubble.init.world.MubbleFeatureConfigs;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class AutumnOakForestBiome extends Biome
{
	public AutumnOakForestBiome()
	{
		super((new Biome.Settings()).configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.depth(0.1F)
				.scale(0.2F)
				.temperature(0.8F)
				.downfall(0.9F)
				.waterColor(155336)
				.waterFogColor(541)
				.parent((String) null));
		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		this.addStructureFeature(Feature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
		this.addStructureFeature(Feature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
		DefaultBiomeFeatures.addLandCarvers(this);
		DefaultBiomeFeatures.addDefaultStructures(this);
		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDungeons(this);
		DefaultBiomeFeatures.addForestFlowers(this);
		DefaultBiomeFeatures.addMineables(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		DefaultBiomeFeatures.addDefaultDisks(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(Feature.NORMAL_TREE.configure(DefaultBiomeFeatures.OAK_TREE_WITH_MORE_BEEHIVES_CONFIG).withChance(0.2F), Feature.FANCY_TREE.configure(MubbleFeatureConfigs.FANCY_AUTUMN_OAK_BEEHIVED_CONFIG).withChance(0.1F)), Feature.NORMAL_TREE.configure(MubbleFeatureConfigs.AUTUMN_OAK_TREE_BEEHIVED_CONFIG).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(10, 0.1F, 1))))));
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addForestGrass(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(MubbleFeatureConfigs.AUTUMN_OAK_LEAF_PILE_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(4))));
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(MubbleFeatureConfigs.YELLOW_MUSHROOM_CONFIG).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceDecoratorConfig(8))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(MubbleFeatureConfigs.ORANGE_MUSHROOM_CONFIG).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceDecoratorConfig(8))));
		DefaultBiomeFeatures.addDefaultVegetation(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4));
		this.addSpawn(EntityCategory.AMBIENT, new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}
	
	@Override
	public int getGrassColorAt(double x, double z)
	{
		return 16091672;
	}
	
	@Override
	public int getFoliageColor()
	{
		return 16094768;
	}
}