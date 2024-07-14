package com.pulsar.cosmologicalib.config;

import net.minecraft.util.Identifier;

public class ExampleConfigData extends ConfigData {
    private static final ConfigSerializer<ExampleConfigData> serializer = new ConfigSerializer<>(ExampleConfigData.class, ExampleConfigData::getDefault);

    public int value1;
    public int value2;
    public String value3;
    public enum value4 {
        OPTION_1,
        OPTION_2,
        OPTION_3
    }

    public ExampleConfigData() {
        super(new Identifier("cosmological", "example-config"));
    }

    private static ExampleConfigData getDefault() {
        return new ExampleConfigData();
    }

    public static ExampleConfigData load() {
        return serializer.deserialize();
    }

    public void save() {
        serializer.serialize(this);
    }
}
