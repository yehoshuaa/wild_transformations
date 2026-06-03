package com.jozua.wildtransformations.transformation;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class TransformationCommand {

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("transformation")
                        .then(Commands.literal("set")
                                .then(Commands.argument("type", StringArgumentType.word())
                                        .executes(context -> {
                                            ServerPlayer player = context.getSource().getPlayerOrException();
                                            String input = StringArgumentType.getString(context, "type").toUpperCase();

                                            TransformationType type;

                                            try {
                                                type = TransformationType.valueOf(input);
                                            } catch (IllegalArgumentException exception) {
                                                player.sendSystemMessage(Component.literal("Unknown transformation type: " + input));
                                                return 0;
                                            }

                                            TransformationData data = TransformationManager.getData(player);
                                            TransformationManager.setTransformation(data, type);

                                            player.sendSystemMessage(Component.literal("Transformation set to " + type.name()));

                                            if (type == TransformationType.VAMPIRE) {
                                                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 60, 0));
                                                player.sendSystemMessage(Component.literal("You feel the curse in your blood."));
                                            }

                                            if (type == TransformationType.WEREWOLF) {
                                                player.sendSystemMessage(Component.literal("Your instincts sharpen."));
                                            }

                                            if (type == TransformationType.LICH) {
                                                player.sendSystemMessage(Component.literal("Your soul feels bound to death."));
                                            }

                                            return 1;
                                        })))
                        .then(Commands.literal("clear")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();

                                    TransformationData data = TransformationManager.getData(player);
                                    TransformationManager.clearTransformation(data);

                                    player.sendSystemMessage(Component.literal("Your transformation has been cleared."));

                                    return 1;
                                }))
                        .then(Commands.literal("check")
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();

                                    TransformationData data = TransformationManager.getData(player);

                                    player.sendSystemMessage(Component.literal("Current transformation: " + data.getType().name()));
                                    player.sendSystemMessage(Component.literal("Stage: " + data.getStage()));
                                    player.sendSystemMessage(Component.literal("Blood: " + data.getBlood()));
                                    player.sendSystemMessage(Component.literal("Rage: " + data.getRage()));
                                    player.sendSystemMessage(Component.literal("Souls: " + data.getSouls()));

                                    return 1;
                                }))
        );
    }
}