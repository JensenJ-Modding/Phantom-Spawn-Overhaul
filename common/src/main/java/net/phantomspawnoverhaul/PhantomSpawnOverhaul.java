package net.phantomspawnoverhaul;

import net.minecraft.core.registries.Registries;
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

    public static final int INSOMNIA_COLOUR = 4378923;

    public static final RegistrySupplier<Item> INSOMNIA_POTION = ITEMS.register(
            "insomnia_bottle", () -> new InsomniaBottle(new Item.Properties().food(Foods.OMINOUS_BOTTLE)));

    public static void init() {
        ITEMS.register();
    }
}
