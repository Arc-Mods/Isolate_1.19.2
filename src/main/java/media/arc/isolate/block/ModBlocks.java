package media.arc.isolate.block;

import media.arc.isolate.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block WHITE = registerBlock(
            "white",
            new Block(Block.Settings.copy(Blocks.WHITE_CONCRETE).strength(250,250).resistance(250)),
            ModItems.GROUP
    );

    public static final Block PACKAGE = registerBlock(
            "packing_box",
            new PackingBoxBlock(FabricBlockSettings.copyOf(Blocks.CHEST).requiresTool()),
            ModItems.GROUP
    );

    public static final Block PILLAR = registerBlock(
            "pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).requiresTool()),
            ModItems.GROUP
    );

    public static final Block PILLAR_HORIZONTAL = registerBlock(
            "vert_pillar",
            new HorizontalPillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).requiresTool()),
            ModItems.GROUP
    );

    public static final Block HORIZ_PILLAR = registerBlock(
            "horiz_pillar",
            new HorizPillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).requiresTool()),
            ModItems.GROUP
    );

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier("isolate", name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier("isolate", name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void init(){

    }
}
