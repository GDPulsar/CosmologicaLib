package com.pulsar.cosmologicalib;

import com.pulsar.cosmologicalib.data.BaseDataComponent;
import com.pulsar.cosmologicalib.data.PlayerDataComponent;
import net.minecraft.util.Identifier;

public interface PlayerComponentAccessible {
    PlayerDataComponent cosmological$getComponentByID(Identifier id);
    void cosmological$setComponent(PlayerDataComponent component);
}
