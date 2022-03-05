package dev.murad.littlelogistics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class LLModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        LLClientConfig.setup(new File(FabricLoader.getInstance().getConfigDir().toFile(), "littlelogistics-client.json"));
//        ClientLifecycleEvents.CLIENT_STOPPING.register(instance -> LLClientConfig.save());
//        new ClientConfigCommand<FabricClientCommandSource>(LLClientConfigImpl.INSTANCE).register(ClientCommandManager.DISPATCHER, p -> true);
//        Storage
    }
}
