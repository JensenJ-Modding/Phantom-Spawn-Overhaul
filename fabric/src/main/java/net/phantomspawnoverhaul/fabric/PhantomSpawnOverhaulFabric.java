package net.phantomspawnoverhaul.fabric;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.fabricmc.api.ModInitializer;
import net.phantomspawnoverhaul.InsomniaEffect;
import net.phantomspawnoverhaul.PhantomSpawnOverhaul;

public class PhantomSpawnOverhaulFabric implements ModInitializer {

    public static Holder<MobEffect> INSOMNIA_EFFECT;

    static {
        INSOMNIA_EFFECT = registerInsomniaEffect(
                new InsomniaEffect(MobEffectCategory.HARMFUL, PhantomSpawnOverhaul.INSOMNIA_COLOUR));
    }

    private static Holder<MobEffect> registerInsomniaEffect(MobEffect mobEffect) {
        return Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(PhantomSpawnOverhaul.MOD_ID, "insomnia"),
                mobEffect);
    }

    @Override
    public void onInitialize() {
        PhantomSpawnOverhaul.init();
    }
}
