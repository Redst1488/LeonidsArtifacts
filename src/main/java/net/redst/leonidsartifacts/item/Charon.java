package net.redst.leonidsartifacts.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.load.InitEffects;
import net.redst.leonidsartifacts.load.InitParticles;

public class Charon extends Item {
    public Charon() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            AreaEffectCloud vapor = new AreaEffectCloud(pLevel, pPlayer.getX(), pPlayer.getY() + 1, pPlayer.getZ());
            vapor.setParticle(InitParticles.VAPOR.get());
            vapor.addEffect(new MobEffectInstance(InitEffects.STUNNED.get(), 60, 0, false, false));
            vapor.setRadius(4.75f);
            vapor.setDuration(600);
            vapor.setOwner(pPlayer);AreaEffectCloud vapor2 = new AreaEffectCloud(pLevel, pPlayer.getX(), pPlayer.getY() + 0.5, pPlayer.getZ());
            vapor2.setParticle(InitParticles.VAPOR.get());
            vapor2.addEffect(new MobEffectInstance(InitEffects.STUNNED.get(), 60, 0, false, false));
            vapor2.setRadius(5f);
            vapor2.setDuration(600);
            vapor2.setOwner(pPlayer);
            vapor.setOwner(pPlayer);AreaEffectCloud vapor3 = new AreaEffectCloud(pLevel, pPlayer.getX(), pPlayer.getY() + 1.3, pPlayer.getZ());
            vapor3.setParticle(InitParticles.VAPOR.get());
            vapor3.addEffect(new MobEffectInstance(InitEffects.STUNNED.get(), 60, 0, false, false));
            vapor3.setRadius(4.5f);
            vapor3.setDuration(600);
            vapor3.setOwner(pPlayer);
            pPlayer.level().addFreshEntity(vapor);
            pPlayer.level().addFreshEntity(vapor2);
            pPlayer.level().addFreshEntity(vapor3);
            pPlayer.addEffect(new MobEffectInstance(InitEffects.VAPED.get(), 600, 0, false, false));
        }
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

}
