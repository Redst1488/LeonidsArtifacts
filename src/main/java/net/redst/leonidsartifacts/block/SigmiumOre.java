package net.redst.leonidsartifacts.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.redst.leonidsartifacts.load.InitItems;

import java.util.List;
import java.util.Collections;


public class SigmiumOre extends Block {

    public SigmiumOre() {
        super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2f, 10f));
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(InitItems.RAW_SIGMIUM.get()));
    }

}
