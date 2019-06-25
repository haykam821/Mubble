package hugman.mod.init.world;

import hugman.mod.init.MubbleBlocks;
import hugman.mod.objects.world.surface_builder.PermafrostSurfaceBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class MubbleSurfaceBuilders
{
	protected static final BlockState PERMAROCK = MubbleBlocks.PERMAROCK.getDefaultState();
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> PERMAFROST_SURFACE_BUILDER = new PermafrostSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilderConfig PERMAROCK_SURFACE = new SurfaceBuilderConfig(PERMAROCK, PERMAROCK, PERMAROCK);
}