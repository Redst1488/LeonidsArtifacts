package net.redst.leonidsartifacts.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.network.Variables;
import net.redst.leonidsartifacts.utils.RadianceAura;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
public class Radiance extends SwordItem implements ICurioItem {
    public Radiance() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 2f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 0;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2.8f, new Item.Properties().rarity(Rarity.EPIC));
    }
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> pTooltip, TooltipFlag flag) {
        pTooltip.add(Component.translatable("tooltip.leonidsartifacts.radiance"));
        super.appendHoverText(itemstack, world, pTooltip, flag);
    }
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        Player player = (Player) entity;
        if (selected) {
            if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).RadianceActive) {
                RadianceAura.Burn(player, world);
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        Level level = slotContext.entity().level();
        if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).RadianceActive) {
            RadianceAura.Burn(player, level);
        }
        if (slotContext.index() == 0) {
            if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).isFirstPressed) {
                if (!(player.getCooldowns().isOnCooldown(stack.getItem()))) {
                    player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.RadianceActive = !(player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).RadianceActive;
                        capability.syncPlayerVariables(player);
                    });
                    player.getCooldowns().addCooldown(stack.getItem(), 20);
                }
            }
        }
        if (slotContext.index() == 1) {
            if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).isSecondPressed) {
                if (!(player.getCooldowns().isOnCooldown(stack.getItem()))) {
                    player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.RadianceActive = !(player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).RadianceActive;
                        capability.syncPlayerVariables(player);
                    });
                    player.getCooldowns().addCooldown(stack.getItem(), 20);
                }
            }
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        entity.getCooldowns().addCooldown(entity.getMainHandItem().getItem(), 20);
        entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.RadianceActive = !(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).RadianceActive;
            capability.syncPlayerVariables(entity);
        });
        return ar;
    }
}
