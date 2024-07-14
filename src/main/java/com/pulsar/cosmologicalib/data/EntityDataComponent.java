package com.pulsar.cosmologicalib.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public abstract class EntityDataComponent extends BaseDataComponent {
    EntityType<? extends Entity> entityType;

    public EntityDataComponent(EntityType<? extends Entity> entityType) {
        this.entityType = entityType;
    }

    public EntityType<? extends Entity> getEntityType() {
        return entityType;
    }
}
