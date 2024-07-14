package com.pulsar.cosmologicalib.config;

import net.minecraft.util.Identifier;

public abstract class ConfigData {
    final Identifier id;

    protected ConfigData(Identifier id) {
        this.id = id;
    }
}
