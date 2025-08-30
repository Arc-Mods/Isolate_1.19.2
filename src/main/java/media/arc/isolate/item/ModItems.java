package media.arc.isolate.item;


import media.arc.isolate.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item CHARM = register("charm", new CharmItem(new Item.Settings()));
    public static final Item KNIFE = register("knife", new KnifeItem(ToolMaterials.NETHERITE, new Item.Settings().rarity(Rarity.UNCOMMON)));


    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("isolate", name), item);
    }

    public static final ItemGroup GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier("isolate", "group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.isolate.group")) // <-- REQUIRED NOW
                    .icon(() -> new ItemStack(ModItems.CHARM))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.WHITE);
                        entries.add(ModItems.CHARM);
                        entries.add(ModItems.KNIFE);
                        entries.add(ModBlocks.PILLAR_GRAY);
                        entries.add(ModBlocks.PILLAR);
                        entries.add(ModBlocks.PILLAR_HORIZONTAL);
                        entries.add(ModBlocks.HORIZ_PILLAR);
                        entries.add(ModBlocks.PACKAGE);
                    })
                    .build()
    );



    public static void init() {
    }
}
