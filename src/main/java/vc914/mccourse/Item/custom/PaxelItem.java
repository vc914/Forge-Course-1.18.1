package vc914.mccourse.Item.custom;


import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Vanishable;
import vc914.mccourse.util.ModTags;


public class PaxelItem extends DiggerItem implements Vanishable {
    public PaxelItem(Tier pTier,float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, ModTags.BLocks.PAXEL_MINEABLE, pProperties);
    }
}


