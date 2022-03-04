package dev.murad.littlelogistics.setup;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.util.Location;
import dev.murad.shipping.ShippingMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class LLSounds {

    public static final RegistrySupplier<SoundEvent> STEAM_TUG_WHISTLE = Registration.SOUND_EVENTS.register("steam_tug_whistle",
            () -> new SoundEvent(Location.modLoc("steam_tug_whistle")));

    public static final RegistrySupplier<SoundEvent> TUG_DOCKING = Registration.SOUND_EVENTS.register("tug_docking",
            () -> new SoundEvent(Location.modLoc("tug_docking")));

    public static final RegistrySupplier<SoundEvent> TUG_UNDOCKING = Registration.SOUND_EVENTS.register("tug_undocking",
            () -> new SoundEvent(Location.modLoc("tug_undocking")));

    public static void register () {}
}
