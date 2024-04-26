package net.redst.leonidsartifacts.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.network.Variable;
import net.redst.leonidsartifacts.utils.RadianceAura;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class Radiance extends SwordItem implements ICurioItem {
    private boolean isActive = true;

    public Radiance() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
                return 0f;
            }

            public float getAttackDamageBonus() {
                return 4f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 20;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2f, new Item.Properties().rarity(Rarity.EPIC));
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
            if (this.isActive) {
                RadianceAura.Burn(player, world);
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        Level level = slotContext.entity().level();
        if (this.isActive) {
            RadianceAura.Burn(player, level);
        }
        if ((player.getCapability(Variable.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variable.PlayerVariables())).isFirstPressed) {
            if (!(player.getCooldowns().isOnCooldown(stack.getItem()))) {
                player.getCooldowns().addCooldown(stack.getItem(), 40);
                this.isActive = !this.isActive;
            }
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        entity.getCooldowns().addCooldown(entity.getMainHandItem().getItem(), 40);
        this.isActive = !this.isActive;
        return ar;
    }
}
