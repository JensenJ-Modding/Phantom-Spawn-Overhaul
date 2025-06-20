package net.phantomspawnoverhaul;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhantomSpawnOverhaul {
    public static final String MOD_ID = "phantomspawnoverhaul";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final Holder<MobEffect> INSOMNIA;

    static {
        INSOMNIA = registerInsomniaEffect(new InsomniaEffect(MobEffectCategory.HARMFUL, 4378923));
    }

    private static Holder<MobEffect> registerInsomniaEffect(MobEffect mobEffect) {
        return Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(MOD_ID, "insomnia"), mobEffect);
    }

    public static final RegistrySupplier<Item> INSOMNIA_POTION = ITEMS.register(
            "insomnia_bottle", () -> new InsomniaBottle(new Item.Properties().food(Foods.OMINOUS_BOTTLE)));

    public static void init() {
        ITEMS.register();
    }
}
