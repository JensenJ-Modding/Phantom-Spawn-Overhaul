package net.phantomspawnoverhaul.neoforge;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phantomspawnoverhaul.InsomniaEffect;
import net.phantomspawnoverhaul.PhantomSpawnOverhaul;

@Mod(PhantomSpawnOverhaul.MOD_ID)
public class PhantomSpawnOverhaulNeoForge {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, PhantomSpawnOverhaul.MOD_ID);

    public static final Holder<MobEffect> INSOMNIA_EFFECT = MOB_EFFECTS.register(
            "insomnia", () -> new InsomniaEffect(MobEffectCategory.HARMFUL, PhantomSpawnOverhaul.INSOMNIA_COLOUR));

    public PhantomSpawnOverhaulNeoForge(ModContainer container, IEventBus bus) {
        MOB_EFFECTS.register(bus);
        PhantomSpawnOverhaul.init();
    }
}
