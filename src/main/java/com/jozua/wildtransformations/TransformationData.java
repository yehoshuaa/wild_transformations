package com.jozua.wildtransformations.transformation;

public class TransformationData {
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
}