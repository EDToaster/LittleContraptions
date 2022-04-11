package ca.edtoaster.littlecontraptions;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * TODO: remove configs
 */
public class LCConfig {
    public static class Client {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        static {
            SPEC = BUILDER.build();
        }
    }

    public static class Server {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        static {
            SPEC = BUILDER.build();
        }
    }



}
