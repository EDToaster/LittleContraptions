package dev.murad.littlelogistics.setup;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.entity.custom.SpringEntity;
import dev.murad.littlelogistics.entity.custom.barge.*;
import dev.murad.littlelogistics.entity.custom.tug.EnergyTugEntity;
import dev.murad.littlelogistics.entity.custom.tug.SteamTugEntity;
import dev.murad.littlelogistics.LLMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class LLEntityTypes {
    public static final RegistrySupplier<EntityType<ChestBargeEntity>> CHEST_BARGE =
            Registration.ENTITIES.register("barge",
                    () -> EntityType.Builder.<ChestBargeEntity>of(ChestBargeEntity::new,
                                    MobCategory.MISC).sized(0.6f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "barge").toString()));

    public static final RegistrySupplier<EntityType<ChunkLoaderBargeEntity>> CHUNK_LOADER_BARGE =
            Registration.ENTITIES.register("chunk_loader_barge",
                    () -> EntityType.Builder.<ChunkLoaderBargeEntity>of(ChunkLoaderBargeEntity::new,
                                    MobCategory.MISC).sized(0.6f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "chunk_loader_barge").toString()));

    public static final RegistrySupplier<EntityType<FishingBargeEntity>> FISHING_BARGE =
            Registration.ENTITIES.register("fishing_barge",
                    () -> EntityType.Builder.<FishingBargeEntity>of(FishingBargeEntity::new,
                                    MobCategory.MISC).sized(0.6f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "fishing_barge").toString()));

    public static final RegistrySupplier<EntityType<FluidTankBargeEntity>> FLUID_TANK_BARGE =
            Registration.ENTITIES.register("fluid_barge",
                    () -> EntityType.Builder.<FluidTankBargeEntity>of(FluidTankBargeEntity::new,
                                    MobCategory.MISC).sized(0.6f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "fluid_barge").toString()));

    public static final RegistrySupplier<EntityType<SeaterBargeEntity>> SEATER_BARGE =
            Registration.ENTITIES.register("seater_barge",
                    () -> EntityType.Builder.<SeaterBargeEntity>of(SeaterBargeEntity::new,
                                    MobCategory.MISC).sized(0.6f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "seater_barge").toString()));

    public static final RegistrySupplier<EntityType<SteamTugEntity>> STEAM_TUG =
            Registration.ENTITIES.register("tug",
                    () -> EntityType.Builder.<SteamTugEntity>of(SteamTugEntity::new,
                                    MobCategory.MISC).sized(0.7f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "tug").toString()));

    public static final RegistrySupplier<EntityType<EnergyTugEntity>> ENERGY_TUG =
            Registration.ENTITIES.register("energy_tug",
                    () -> EntityType.Builder.<EnergyTugEntity>of(EnergyTugEntity::new,
                                    MobCategory.MISC).sized(0.7f, 0.9f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "energy_tug").toString()));

    public static final RegistrySupplier<EntityType<SpringEntity>> SPRING =
            Registration.ENTITIES.register("spring",
                    () -> EntityType.Builder.<SpringEntity>of(SpringEntity::new,
                                    MobCategory.MISC).sized(0.05f, 0.2f)
                            .build(new ResourceLocation(LLMod.MOD_ID, "spring").toString()));

    public static void register () {

    }
}
