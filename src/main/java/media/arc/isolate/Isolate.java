package media.arc.isolate;

import media.arc.isolate.block.ModBlocks;
import media.arc.isolate.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Isolate implements ModInitializer {
	public static final String MOD_ID = "isolate";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.init();
		ModItems.init();

		LOGGER.info("ISOLATE ME");
	}
}