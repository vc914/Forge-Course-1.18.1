package vc914.mccourse.Item.custom;


import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.jetbrains.annotations.Nullable;
import vc914.mccourse.Item.ModItems;
import vc914.mccourse.sound.ModSound;
import vc914.mccourse.util.InventoryUtil;
import vc914.mccourse.util.ModTags;

import java.util.List;

public class DowsingRodItem extends Item {
    private Item MobBellow;

    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(1)).getBlock();

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.below(1), player, blockBelow);
                    foundBlock = true;

                    if(isValuableBlock(blockBelow)) {
                        addNbtToDataTablet(player, positionClicked.below(i), blockBelow);
                    }

                    pContext.getLevel().playSound(player, positionClicked, ModSound.DOWSING_ROD_FOUND_ORE.get(),
                            SoundSource.BLOCKS, 1f, 1f);

                    break;
                   }
                }

                if(!foundBlock) {
                    player.sendMessage(new TranslatableComponent("item.mccourse.dowsing_rod.no_valuables"),
                            player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void addNbtToDataTablet(Player player, BlockPos pos, Block blockBelow) {
       ItemStack dataTablet =
       player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));

       CompoundTag nbtData = new CompoundTag();
       nbtData.putString("mccourse.last_ore","Found" + blockBelow.asItem().getRegistryName().toString() + " at " +
               pos.getX() + ", "+ pos.getY() + ", "+ pos.getZ() + ")");

        dataTablet.setTag(nbtData);

    }


    private void addNbtToMobTablet(Player player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.MOB_TABLET.get()));

        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("mccourse.last_mob","Found" + MobBellow.asItem().getRegistryName().toString() + " at " +
                pos.getX() + ", "+ pos.getY() + ", "+ pos.getZ() + ")");

        dataTablet.setTag(nbtData);

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents,
                                TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(new TranslatableComponent("tooltip.mccourse.dowsing_rod.tooltip.shift"));
    } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.mccourse.dowsing_rod.tooltip"));

        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendMessage(new TextComponent("Found" + blockBelow.asItem().getRegistryName().toString() + " at " +
                "(" + blockPos.getX() + "," + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(Block block) {
        return ModTags.BLocks.DOWSING_ROD_VALUABLE.contains(block);

    }
}
