package com.pulsar.cosmologicalib;

import com.pulsar.cosmologicalib.data.BaseDataComponent;
import net.minecraft.util.Identifier;

public interface ComponentAccessible {
    BaseDataComponent cosmological$getComponentByID(Identifier id);
    void cosmological$setComponent(BaseDataComponent component);
}
