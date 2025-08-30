package media.arc.isolate.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CharmItem extends Item {
    public CharmItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
            if (hand == Hand.MAIN_HAND) {
                // Main hand → go to isolation
                teleportToIsolate(serverPlayer);
                return TypedActionResult.success(user.getStackInHand(hand));
            } else if (hand == Hand.OFF_HAND) {
                // Offhand → return to overworld IF in isolation
                if (serverPlayer.getWorld().getRegistryKey().getValue().equals(new Identifier("isolate", "isolation"))) {
                    teleportToOverworld(serverPlayer);
                    return TypedActionResult.success(user.getStackInHand(hand));
                } else {
                    return TypedActionResult.pass(user.getStackInHand(hand));
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient && target instanceof ServerPlayerEntity serverPlayer) {
            teleportToIsolate(serverPlayer);
        }
        return super.postHit(stack, target, attacker);
    }

    private void teleportToOverworld(ServerPlayerEntity player) {
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

    private void teleportToIsolate(ServerPlayerEntity player) {
        RegistryKey<World> isolateKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier("isolate", "isolation"));
        ServerWorld isolateWorld = player.getServer().getWorld(isolateKey);

        if (isolateWorld != null) {
            player.teleport(
                    isolateWorld,
                    isolateWorld.getSpawnPos().getX() + 0.5,
                    isolateWorld.getSpawnPos().getY(),
                    isolateWorld.getSpawnPos().getZ() + 0.5,
                    player.getYaw(),
                    player.getPitch()
            );
        } else {
            System.out.println("Failed to find isolate world!");
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }
}

