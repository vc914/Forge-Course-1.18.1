package vc914.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.stream.Stream;

public class cobalt_blaster_block extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public cobalt_blaster_block(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }
    private static VoxelShape SHAPE_N = Stream.of(
            Block.box(13, 0, 2, 15, 2, 14),
            Block.box(1, 0, 2, 3, 2, 14),
            Block.box(2, 0, 12, 14, 2, 14),
            Block.box(1, 0, 2, 15, 2, 4),
            Block.box(13, 0, 1, 16, 3, 4),
            Block.box(0, 0, 1, 3, 3, 4),
            Block.box(13, 0, 12, 16, 3, 15),
            Block.box(0, 0, 12, 3, 3, 15),
            Block.box(3, 0, 4, 13, 9, 12),
            Block.box(4, 9, 5, 12, 16, 11),
            Block.box(1, 5, 7, 3, 7, 9),
            Block.box(12, 5, 7, 14, 7, 9),
            Block.box(14, 5, 7, 16, 15, 9),
            Block.box(0, 5, 7, 2, 15, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static VoxelShape SHAPE_O = Stream.of(
            Block.box(2, 0, 1, 14, 2, 3),
            Block.box(2, 0, 13, 14, 2, 15),
            Block.box(12, 0, 2, 14, 2, 14),
            Block.box(2, 0, 1, 4, 2, 15),
            Block.box(1, 0, 0, 4, 3, 3),
            Block.box(1, 0, 13, 4, 3, 16),
            Block.box(12, 0, 0, 15, 3, 3),
            Block.box(12, 0, 13, 15, 3, 16),
            Block.box(4, 0, 3, 12, 9, 13),
            Block.box(5, 9, 4, 11, 16, 12),
            Block.box(7, 5, 13, 9, 7, 15),
            Block.box(7, 5, 2, 9, 7, 4),
            Block.box(7, 5, 0, 9, 15, 2),
            Block.box(7, 5, 14, 9, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static VoxelShape SHAPE_I = Stream.of(
            Block.box(1, 0, 2, 3, 2, 14),
            Block.box(13, 0, 2, 15, 2, 14),
            Block.box(2, 0, 2, 14, 2, 4),
            Block.box(1, 0, 12, 15, 2, 14),
            Block.box(0, 0, 12, 3, 3, 15),
            Block.box(13, 0, 12, 16, 3, 15),
            Block.box(0, 0, 1, 3, 3, 4),
            Block.box(13, 0, 1, 16, 3, 4),
            Block.box(3, 0, 4, 13, 9, 12),
            Block.box(4, 9, 5, 12, 16, 11),
            Block.box(13, 5, 7, 15, 7, 9),
            Block.box(2, 5, 7, 4, 7, 9),
            Block.box(0, 5, 7, 2, 15, 9),
            Block.box(14, 5, 7, 16, 15, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static VoxelShape SHAPE_H = Stream.of(
            Block.box(2, 0, 13, 14, 2, 15),
            Block.box(2, 0, 1, 14, 2, 3),
            Block.box(2, 0, 2, 4, 2, 14),
            Block.box(12, 0, 1, 14, 2, 15),
            Block.box(12, 0, 13, 15, 3, 16),
            Block.box(12, 0, 0, 15, 3, 3),
            Block.box(1, 0, 13, 4, 3, 16),
            Block.box(1, 0, 0, 4, 3, 3),
            Block.box(4, 0, 3, 12, 9, 13),
            Block.box(5, 9, 4, 11, 16, 12),
            Block.box(7, 5, 1, 9, 7, 3),
            Block.box(7, 5, 12, 9, 7, 14),
            Block.box(7, 5, 14, 9, 15, 16),
            Block.box(7, 5, 0, 9, 15, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
      switch (pState.getValue(FACING)) {
          case NORTH:
              return SHAPE_N;
          case SOUTH:
              return SHAPE_I;
          case EAST:
              return SHAPE_O;
          case WEST:
              return SHAPE_H;

      }
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
