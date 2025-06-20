package net.phantomspawnoverhaul.neoforge;

import net.minecraft.world.effect.MobEffectInstance;

@SuppressWarnings("unused")
public class InsomniaBottleImpl {

    public static MobEffectInstance getInsomniaEffect() {
        return new MobEffectInstance(PhantomSpawnOverhaulNeoForge.INSOMNIA_EFFECT, 120000, 0, false, false, true);
    }
}
