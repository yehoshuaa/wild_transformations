package com.jozua.wildtransformations.registry;

import com.jozua.wildtransformations.WildTransformations;
import com.jozua.wildtransformations.transformation.TransformationData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, WildTransformations.MODID);

    public static final Supplier<AttachmentType<TransformationData>> TRANSFORMATION_DATA =
            ATTACHMENT_TYPES.register(
                    "transformation_data",
                    () -> AttachmentType.serializable(TransformationData::new)
                            .copyOnDeath()
                            .build()
            );
}