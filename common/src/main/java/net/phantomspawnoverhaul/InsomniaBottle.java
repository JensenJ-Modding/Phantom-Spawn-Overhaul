package net.phantomspawnoverhaul;

import java.util.List;
import java.util.Objects;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.NotNull;

public class InsomniaBottle extends Item {

    public InsomniaBottle(Item.Properties properties) {
        super(properties);
    }

    public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            level.playSound(
                    null,
                    livingEntity.blockPosition(),
                    SoundEvents.OMINOUS_BOTTLE_DISPOSE,
                    livingEntity.getSoundSource(),
                    1.0F,
                    1.0F);
            livingEntity.addEffect(getInsomniaEffect());
        }

        itemStack.consume(1, livingEntity);
        return itemStack;
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 32;
    }

    public @NotNull UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(
            Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    public void appendHoverText(
            ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
        List<MobEffectInstance> list2 = List.of(getInsomniaEffect());
        Objects.requireNonNull(list);
        PotionContents.addPotionTooltip(list2, list::add, 1.0F, tooltipContext.tickRate());
    }

    @ExpectPlatform
    private static MobEffectInstance getInsomniaEffect() {
        throw new AssertionError("Not implemented for this modloader");
    }
}
