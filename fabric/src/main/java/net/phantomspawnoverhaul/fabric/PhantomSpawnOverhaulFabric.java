package net.phantomspawnoverhaul.fabric;

import net.fabricmc.api.ModInitializer;
import net.phantomspawnoverhaul.PhantomSpawnOverhaul;

public class PhantomSpawnOverhaulFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PhantomSpawnOverhaul.init();
    }
}
