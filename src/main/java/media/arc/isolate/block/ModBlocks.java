package media.arc.isolate.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block WHITE = registerBlock(
            "white",
            new Block(Block.Settings.copy(Blocks.WHITE_CONCRETE).strength(250, 250).resistance(250))
    );

    public static final Block PACKAGE = registerBlock(
            "packing_box",
            new PackingBoxBlock(Block.Settings.copy(Blocks.CHEST).requiresTool())
    );

    public static final Block PILLAR = registerBlock(
            "pillar",
            new PillarBlock(Block.Settings.copy(Blocks.QUARTZ_PILLAR).requiresTool())
    );

    public static final Block PILLAR_GRAY = registerBlock(
            "pillar_gray",
            new PillarBlock(Block.Settings.copy(Blocks.QUARTZ_PILLAR).requiresTool())
    );

    public static final Block PILLAR_HORIZONTAL = registerBlock(
            "vert_pillar",
            new HorizontalPillarBlock(Block.Settings.copy(Blocks.QUARTZ_PILLAR).requiresTool())
    );

    public static final Block HORIZ_PILLAR = registerBlock(
            "horiz_pillar",
            new HorizPillarBlock(Block.Settings.copy(Blocks.QUARTZ_PILLAR).requiresTool())
    );

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier("isolate", name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier("isolate", name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void init() {}
}

