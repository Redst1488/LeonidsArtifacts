package net.redst.leonidsartifacts.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import net.redst.leonidsartifacts.network.Variables;
import net.redst.leonidsartifacts.utils.Dagger;

import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
public class BlinkDagger extends Item implements ICurioItem {
    public BlinkDagger() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    }
    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> pTooltip, TooltipFlag flag) {
        pTooltip.add(Component.translatable("tooltip.leonidsartifacts.blink_dagger"));
        super.appendHoverText(itemstack, world, pTooltip, flag);
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        Level level = slotContext.entity().level();
        if (slotContext.index() == 0) {
            if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).isFirstPressed) {
                if (!(player.getCooldowns().isOnCooldown(stack.getItem()))) {
                    Dagger.Blink(level, player, stack);
                }
            }
        }
        if (slotContext.index() == 1) {
            if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).isSecondPressed) {
                if (!(player.getCooldowns().isOnCooldown(stack.getItem()))) {
                    Dagger.Blink(level, player, stack);
                }
            }
        }
    }
}