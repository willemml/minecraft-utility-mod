package dev.wnuke.nukeclient.mixin;

import dev.wnuke.nukeclient.NukeClient;
import dev.wnuke.nukeclient.event.events.Tick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.GameRenderer.class)
public class GameRenderer {
    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        NukeClient.getEventManager().post(new Tick());
    }
}
