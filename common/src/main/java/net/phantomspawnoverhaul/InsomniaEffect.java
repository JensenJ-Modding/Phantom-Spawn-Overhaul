package net.phantomspawnoverhaul;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class InsomniaEffect extends MobEffect {
    public InsomniaEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    public boolean applyEffectTick(LivingEntity livingEntity, int i) {
        if (!(livingEntity instanceof ServerPlayer serverPlayer)) {
            return true;
        }

        if (serverPlayer.isSpectator()) {
            return true;
        }

        ServerLevel serverLevel = serverPlayer.serverLevel();
        if (serverLevel.getDifficulty() == Difficulty.PEACEFUL) {
            return true;
        }

        if (!serverLevel.dimensionType().hasSkyLight()) {
            return true;
        }

        if (serverLevel.getSkyDarken() < 5) {
            return true;
        }

        BlockPos blockPos = serverPlayer.blockPosition();
        if (!serverLevel.canSeeSky(blockPos)) {
            return true;
        }

        if (blockPos.getY() < serverLevel.getSeaLevel()) {
            return true;
        }

        // Spawn phantoms - This logic is taken directly from PhantomSpawner
        RandomSource randomSource = serverLevel.random;
        DifficultyInstance difficultyInstance = serverLevel.getCurrentDifficultyAt(blockPos);
        BlockPos blockPos2 = blockPos.above(20 + randomSource.nextInt(15))
                .east(-10 + randomSource.nextInt(21))
                .south(-10 + randomSource.nextInt(21));
        BlockState blockState = serverLevel.getBlockState(blockPos2);
        FluidState fluidState = serverLevel.getFluidState(blockPos2);
        if (NaturalSpawner.isValidEmptySpawnBlock(serverLevel, blockPos2, blockState, fluidState, EntityType.PHANTOM)) {
            SpawnGroupData spawnGroupData = null;
            // Modified this to spawn more at once, guaranteed at least 3 as opposed to just 1 spawn
            int l = 3 + randomSource.nextInt(difficultyInstance.getDifficulty().getId() + 1);

            for (int m = 0; m < l; ++m) {
                Phantom phantom = EntityType.PHANTOM.create(serverLevel);
                if (phantom != null) {
                    phantom.moveTo(blockPos2, 0.0F, 0.0F);
                    spawnGroupData = phantom.finalizeSpawn(
                            serverLevel, difficultyInstance, MobSpawnType.NATURAL, spawnGroupData);
                    serverLevel.addFreshEntityWithPassengers(phantom);
                    ++i;
                }
            }
            return false;
        }

        return true;
    }
}
