package vc914.mccourse.block.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;
import vc914.mccourse.Item.ModItems;

public class TurnipCropBlock extends BeetrootBlock {
    public TurnipCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.TURNIP_SEEDS.get();
    }
}
