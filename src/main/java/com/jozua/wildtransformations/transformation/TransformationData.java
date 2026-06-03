package com.jozua.wildtransformations.transformation;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class TransformationData implements INBTSerializable<CompoundTag> {
    private TransformationType type = TransformationType.NONE;
    private int stage = 0;
    private int blood = 100;
    private int rage = 0;
    private int souls = 0;

    public TransformationType getType() {
        return type;
    }

    public void setType(TransformationType type) {
        this.type = type;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public boolean isTransformed() {
        return type != TransformationType.NONE;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putString("type", type.name());
        tag.putInt("stage", stage);
        tag.putInt("blood", blood);
        tag.putInt("rage", rage);
        tag.putInt("souls", souls);

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        String savedType = tag.getString("type");

        try {
            this.type = TransformationType.valueOf(savedType);
        } catch (IllegalArgumentException exception) {
            this.type = TransformationType.NONE;
        }

        this.stage = tag.getInt("stage");
        this.blood = tag.getInt("blood");
        this.rage = tag.getInt("rage");
        this.souls = tag.getInt("souls");
    }
}