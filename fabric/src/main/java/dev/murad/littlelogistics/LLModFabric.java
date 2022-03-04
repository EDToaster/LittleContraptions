package dev.murad.littlelogistics;

import dev.murad.littlelogistics.LLMod;
import net.fabricmc.api.ModInitializer;

public class LLModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LLMod.init();
    }
}
