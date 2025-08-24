package media.arc.isolate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class HorizontalPillarBlock extends Block {

    public HorizontalPillarBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(2, 2, 0, 14, 14, 16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
