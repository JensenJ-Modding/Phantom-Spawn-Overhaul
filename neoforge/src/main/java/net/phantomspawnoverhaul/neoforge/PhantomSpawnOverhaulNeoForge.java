package net.phantomspawnoverhaul.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.phantomspawnoverhaul.PhantomSpawnOverhaul;

@Mod(PhantomSpawnOverhaul.MOD_ID)
public class PhantomSpawnOverhaulNeoForge {
    public PhantomSpawnOverhaulNeoForge(ModContainer container, IEventBus bus) {
        PhantomSpawnOverhaul.init();
    }
}
