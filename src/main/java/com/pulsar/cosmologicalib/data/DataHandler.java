package com.pulsar.cosmologicalib.data;

import com.pulsar.cosmologicalib.CosmologicaLib;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class DataHandler extends PersistentState {
    public NbtCompound worldData = new NbtCompound();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.put("worldData", worldData);
        return nbt;
    }

    public static DataHandler createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        DataHandler handler = new DataHandler();
        handler.worldData = nbt.getCompound("worldData");
        return handler;
    }

    private static final Type<DataHandler> type = new Type<>(
            DataHandler::new,
            DataHandler::createFromNbt,
            null
    );

    public static DataHandler getDataHandler(MinecraftServer server) {
        PersistentStateManager persistentStateManager = Objects.requireNonNull(server.getWorld(World.OVERWORLD)).getPersistentStateManager();
        DataHandler handler = persistentStateManager.getOrCreate(type, CosmologicaLib.MOD_ID);
        handler.markDirty();
        return handler;
    }

    public static NbtCompound getWorldData(World world) {
        DataHandler handler = getDataHandler(Objects.requireNonNull(world.getServer()));
        return handler.worldData;
    }

    public static void setWorldData(World world, NbtCompound nbt) {
        DataHandler handler = getDataHandler(Objects.requireNonNull(world.getServer()));
        handler.worldData = nbt;
    }
}
