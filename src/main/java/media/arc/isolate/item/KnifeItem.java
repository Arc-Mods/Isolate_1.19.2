package media.arc.isolate.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnifeItem extends SwordItem {
    public KnifeItem(ToolMaterial material, Settings settings) {
        super(material,4, -2.4f, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();

        // Only run on server and only for players
        if (!world.isClient && target instanceof ServerPlayerEntity serverPlayer) {
            teleportOut(serverPlayer);
        }

        return super.postHit(stack, target, attacker);
    }

    private void teleportOut(ServerPlayerEntity player) {
        ServerWorld overworld = player.getServer().getWorld(World.OVERWORLD);
        if (overworld != null) {
            player.teleport(
                    overworld,
                    0.5, 150.0, 0.5,
                    player.getYaw(),
                    player.getPitch()
            );
        } else {
            System.out.println("Failed to find Overworld!");
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(this.getDescription().formatted(Formatting.YELLOW));
        tooltip.add(this.getDescription_a().formatted(Formatting.YELLOW));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }

    public MutableText getDescription_a() {
        return Text.translatable(this.getTranslationKey() + ".desc_b");
    }
}