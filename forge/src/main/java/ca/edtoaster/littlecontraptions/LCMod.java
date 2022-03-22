package ca.edtoaster.littlecontraptions;

import ca.edtoaster.littlecontraptions.setup.Registration;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.ui.PonderButton;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LCMod.MOD_ID)
public class LCMod
{
    public static final String MOD_ID = "littlecontraptions";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public LCMod() {
        Registration.register();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
