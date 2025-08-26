package media.arc.isolate.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static media.arc.isolate.block.ModBlocks.*;

public class ModItems {

    public static final Item CHARM = register("charm", new CharmItem(new Item.Settings()));
    public static final Item KNIFE = register("knife", new KnifeItem(ToolMaterials.NETHERITE, new Item.Settings().rarity(Rarity.UNCOMMON)));


    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("isolate", name), item);
    }

    public static final ItemGroup GROUP = FabricItemGroupBuilder
            .create(new Identifier("isolate", "group"))
            .icon(() -> new ItemStack(CHARM))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(WHITE));
                stacks.add(new ItemStack(CHARM));
                stacks.add(new ItemStack(KNIFE));
                stacks.add(new ItemStack(PILLAR_GRAY));
                stacks.add(new ItemStack(PILLAR));
                stacks.add(new ItemStack(PILLAR_HORIZONTAL));
                stacks.add(new ItemStack(HORIZ_PILLAR));
                stacks.add(new ItemStack(PACKAGE));

            })
            .build();

    public static void init() {
    }
}
