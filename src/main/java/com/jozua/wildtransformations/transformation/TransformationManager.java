package com.jozua.wildtransformations.transformation;

import com.jozua.wildtransformations.registry.ModAttachments;
import net.minecraft.server.level.ServerPlayer;

public class TransformationManager {

    public static TransformationData getData(ServerPlayer player) {
        return player.getData(ModAttachments.TRANSFORMATION_DATA);
    }

    public static void setTransformation(TransformationData data, TransformationType type) {
        if (type == TransformationType.NONE) {
            clearTransformation(data);
            return;
        }

        WildTransformations.LOGGER.info("Transformation set to {}", type.name());

        if (type == TransformationType.VAMPIRE) {
            data.setBlood(100);
            data.setRage(0);
            data.setSouls(0);
        }

        if (type == TransformationType.WEREWOLF) {
            data.setBlood(0);
            data.setRage(100);
            data.setSouls(0);
        }

        if (type == TransformationType.LICH) {
            data.setBlood(0);
            data.setRage(0);
            data.setSouls(100);
        }
    }

    public static void clearTransformation(TransformationData data) {
        data.setType(TransformationType.NONE);
        data.setStage(0);
        data.setBlood(0);
        data.setRage(0);
        data.setSouls(0);
    }

    public static boolean isVampire(TransformationData data) {
        return data.getType() == TransformationType.VAMPIRE;
    }

    public static boolean isWerewolf(TransformationData data) {
        return data.getType() == TransformationType.WEREWOLF;
    }

    public static boolean isLich(TransformationData data) {
        return data.getType() == TransformationType.LICH;
    }
}