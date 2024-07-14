package com.pulsar.cosmologicalib.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public abstract class BaseDataComponent {
    public abstract Identifier getID();

    public abstract NbtCompound saveNbt();
    public abstract void readNbt(NbtCompound nbt);
}
