package com.pulsar.cosmologicalib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.pulsar.cosmologicalib.CosmologicaLib;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.lang3.SerializationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class ConfigSerializer<T extends ConfigData> {
    private final Supplier<T> configDefault;
    private final Class<T> configClass;
    private final Gson gson;

    public ConfigSerializer(Class<T> configClass, Supplier<T> configDefault) {
        this.configClass = configClass;
        this.configDefault = configDefault;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void serialize(T config) throws SerializationException {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(config.id.getNamespace()).resolve(config.id.getPath()+".json");
        try {
            Files.createDirectories(path.getParent());
            BufferedWriter writer = Files.newBufferedWriter(path);
            gson.toJson(config, configClass, writer);
            writer.close();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public T deserialize() throws SerializationException {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(configDefault.get().id.getNamespace()).resolve(configDefault.get().id.getPath()+".json");
        if (Files.exists(path)) {
            try {
                BufferedReader reader = Files.newBufferedReader(path);
                T read = gson.fromJson(reader, configClass);
                reader.close();
                return read;
            } catch (IOException | JsonParseException e) {
                throw new SerializationException(e);
            }
        } else {
            return configDefault.get();
        }
    }
}
