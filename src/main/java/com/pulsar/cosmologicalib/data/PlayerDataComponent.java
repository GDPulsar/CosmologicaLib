package com.pulsar.cosmologicalib.data;

import net.minecraft.entity.EntityType;

public abstract class PlayerDataComponent extends EntityDataComponent {
    public PlayerDataComponent() {
        super(EntityType.PLAYER);
    }
}
