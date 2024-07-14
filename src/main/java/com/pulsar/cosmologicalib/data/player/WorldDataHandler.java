package com.pulsar.cosmologicalib.data.player;

import com.pulsar.cosmologicalib.data.DataHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class WorldDataHandler {
    public static void set(World world, String key, NbtCompound value) {
        NbtCompound data = DataHandler.getWorldData(world);
        data.put(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, boolean value) {
        NbtCompound data = DataHandler.getWorldData(world);
        data.putBoolean(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, byte value) {
        NbtCompound data = DataHandler.getWorldData(world);
        data.putByte(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, byte[] value) {
        NbtCompound data = DataHandler.getWorldData(world);
        data.putByteArray(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, double value) {
        NbtCompound data = DataHandler.getWorldData(world);
        data.putDouble(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, float value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putFloat(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, int value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putInt(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, int[] value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putIntArray(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, long value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putLong(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, long[] value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putLongArray(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, short value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putShort(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, String value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putString(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static void set(World world, String key, UUID value) {
        NbtCompound data = DataHandler.getWorldData(world);;
        data.putUuid(key, value);
        DataHandler.setWorldData(world, data);
    }

    public static boolean getBoolean(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getBoolean(key);
    }

    public static byte getByte(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getByte(key);
    }

    public static byte[] getByteArray(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getByteArray(key);
    }

    public static NbtCompound getCompound(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getCompound(key);
    }

    public static double getDouble(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getDouble(key);
    }

    public static float getFloat(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getFloat(key);
    }

    public static int getInt(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getInt(key);
    }

    public static int[] getIntArray(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getIntArray(key);
    }

    public static long getLong(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getLong(key);
    }

    public static long[] getLongArray(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getLongArray(key);
    }

    public static short getShort(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getShort(key);
    }

    public static String getString(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getString(key);
    }

    public static UUID getUuid(World world, String key) {
        NbtCompound data = DataHandler.getWorldData(world);;
        return data.getUuid(key);
    }
}
