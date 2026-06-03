package com.jozua.wildtransformations.event;

import com.jozua.wildtransformations.transformation.TransformationData;
import com.jozua.wildtransformations.transformation.TransformationManager;
import com.jozua.wildtransformations.transformation.TransformationType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class PlayerTransformationEvents {

    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        TransformationData data = TransformationManager.getData(player);

        if (data.getType() == TransformationType.VAMPIRE) {
            handleVampireTick(player);
        }
    }

    private static void handleVampireTick(ServerPlayer player) {
        // Refresh night vision every few seconds, instead of every tick.
        // Temporary test effect.
        // Later this will become a toggle ability.
        if (player.tickCount % 100 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
        }
    }

    private static boolean isInSunlight(ServerPlayer player) {
        return player.level().isDay()
                && player.level().canSeeSky(player.blockPosition())
                && !player.isInWaterOrRain()
                && !player.isCreative()
                && !player.isSpectator();
    }
}