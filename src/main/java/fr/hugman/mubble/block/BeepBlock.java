package fr.hugman.mubble.block;

import fr.hugman.mubble.world.MubbleGamerules;
import net.minecraft.SharedConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author komerish
 * @author Napero
 * @author Hugman
 * @since v4.0.0
 */
public class BeepBlock extends Block {
	public static final int DEFAULT_COOLDOWN = SharedConstants.TICKS_PER_SECOND * 4;
	public static final BooleanProperty FRAME = BooleanProperty.of("frame");

	public final boolean offset;

	public BeepBlock(Settings settings, boolean offset) {
		super(settings);
		this.offset = offset;
		this.setDefaultState(getDefaultState().with(FRAME, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FRAME);
	}

	@Override
	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
		return state.get(FRAME);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return state.get(FRAME) ? VoxelShapes.empty() : VoxelShapes.fullCube();
	}

	@Override
	public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
		return state.get(FRAME) ? 1.0F : super.getAmbientOcclusionLightLevel(state, world, pos);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return getStateAtTime(ctx.getWorld());
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		this.refreshState(world, pos);
		this.scheduleTick(world, pos, state.getBlock());
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if(!world.isClient()) {
			this.refreshState(world, pos);
			this.scheduleTick(world, pos, state.getBlock());
		}
	}

	public void refreshState(World world, BlockPos pos) {
		world.setBlockState(pos, getStateAtTime(world));
	}

	public void scheduleTick(World world, BlockPos pos, Block block) {
		int cooldown = world.getGameRules().getInt(MubbleGamerules.BEEP_BLOCK_COOLDOWN);
		if(cooldown > 0) {
			long worldTime = world.getTime();
			int delta = (int) (cooldown - worldTime);
			world.scheduleBlockTick(pos, block, (delta == 0) ? cooldown : delta % cooldown);
		}
	}

	public BlockState getStateAtTime(World world) {
		int cooldown = world.getGameRules().getInt(MubbleGamerules.BEEP_BLOCK_COOLDOWN);
		if(cooldown <= 0) return getDefaultState().with(FRAME, this.offset);
		long worldTime = world.getTime();
		boolean frame = (int) ((worldTime + (this.offset ? cooldown : 0)) % (cooldown * 2)) < cooldown;
		return this.getDefaultState().with(FRAME, frame);
	}
}