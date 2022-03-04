package dev.murad.littlelogistics.util;

import dev.murad.littlelogistics.LLMod;
import net.minecraft.resources.ResourceLocation;

public class Location {

    public static ResourceLocation modLoc(String ident) {
        return new ResourceLocation(LLMod.MOD_ID, ident);
    }
}
