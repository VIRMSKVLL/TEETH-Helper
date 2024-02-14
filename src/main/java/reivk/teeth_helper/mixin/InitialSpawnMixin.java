package reivk.teeth_helper.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reivk.teeth_helper.Teeth;


@Mixin(PlayerManager.class)
public class InitialSpawnMixin {
	@Inject(at = @At("TAIL"), method = "respawnPlayer")
	private void init(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
		Teeth.LOGGER.info("Respawn!");
		player.setPose(EntityPose.SPIN_ATTACK);
		player.setPosition(player.getX(), 500, player.getZ());
	}
}