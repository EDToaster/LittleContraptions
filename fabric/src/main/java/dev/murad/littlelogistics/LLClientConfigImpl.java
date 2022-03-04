package dev.murad.littlelogistics;

import com.oroarmor.config.*;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

import java.io.File;
import java.util.List;

public class LLClientConfigImpl {

    private static final ConfigItemGroup GENERAL = new GeneralGroup();
    private static class GeneralGroup extends ConfigItemGroup {
        public static final DoubleConfigItem TUG_SMOKE_MODIFIER = new DoubleConfigItem(
                "tugSmoke",
                0.4,
                "Modify the rate of smoke produced by a tug. Min 0, Max 1, Default 0.4",
                null,
                0, 1);

        public static final BooleanConfigItem DISABLE_TUG_ROUTE_BEACONS = new BooleanConfigItem(
                "disableTugRouteBeacons",
                false,
                "Disable indicator beacons for tug route item. Default false.");

        public GeneralGroup() {
            super(List.of(TUG_SMOKE_MODIFIER, DISABLE_TUG_ROUTE_BEACONS), "general");
        }
    }

    private static class InternalClientConfig extends Config {
        public InternalClientConfig(File configFile) {
            super(List.of(GENERAL), configFile, LLMod.MOD_ID);
        }
    }

    public static InternalClientConfig INSTANCE;

    public static void setup(File configFile) {
        INSTANCE = new InternalClientConfig(configFile);
        INSTANCE.readConfigFromFile();
        INSTANCE.saveConfigToFile();
    }

    public static double tugSmokeModifier() {
        return GeneralGroup.TUG_SMOKE_MODIFIER.getValue();
    }

    public static boolean disableTugRouteBeacons() {
        return GeneralGroup.DISABLE_TUG_ROUTE_BEACONS.getValue();
    }
}

