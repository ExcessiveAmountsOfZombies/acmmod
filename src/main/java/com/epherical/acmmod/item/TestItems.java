package com.epherical.acmmod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class TestItems {


    public static class AwesomeBlock extends Block {

        public AwesomeBlock(Properties properties) {
            super(properties);
        }

        @Override
        public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
            if (!level.isClientSide()) {
                level.setBlock(player.getOnPos().above(24), Blocks.ANVIL.defaultBlockState(), Block.UPDATE_ALL);
            }
            return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        }
    }


    public static class InfiniteFoodItem extends Item {

        public InfiniteFoodItem(Properties properties) {
            super(properties);

        }

        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
            FoodProperties properties = stack.getFoodProperties(livingEntity);
            ItemStack newStack = stack.transmuteCopy(stack.getItem());
            livingEntity.eat(level, newStack, properties);
            return stack;
        }


    }

    public static class PacificationItem extends Item {

        public PacificationItem(Properties properties) {
            super(properties);
        }


        @Override
        public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
            // handle this with an event instead.
            return super.interactLivingEntity(stack, player, interactionTarget, usedHand);
        }
    }


}
