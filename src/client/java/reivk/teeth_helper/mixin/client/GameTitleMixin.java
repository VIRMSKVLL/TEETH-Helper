package reivk.teeth_helper.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MinecraftClient.class)
public class GameTitleMixin {
	/**
	 * @author REIVK
	 * @reason Change window title for thematic purposes.
	 */
	@Overwrite
	private String getWindowTitle() {
		return "The Express Elevator to Hell";
	};
}
