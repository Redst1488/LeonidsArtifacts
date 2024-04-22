package net.redst.leonidsartifacts.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SigmiumDust extends Item {
    public SigmiumDust() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> pTooltip, TooltipFlag flag) {
        pTooltip.add(Component.translatable("tooltip.leonidsartifats.sigmium_dust"));
        super.appendHoverText(itemstack, world, pTooltip, flag);
    }
}
