package com.pulsar.cosmologicalib;

import com.pulsar.cosmologicalib.config.ExampleConfigData;
import com.pulsar.cosmologicalib.data.BaseDataComponent;
import com.pulsar.cosmologicalib.data.EntityDataComponent;
import com.pulsar.cosmologicalib.data.PlayerDataComponent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CosmologicaLib implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("cosmologicalib");
	public static final String MOD_ID = "cosmologicalib";

	private static List<Supplier<? extends BaseDataComponent>> components = new ArrayList<>();

	public static ExampleConfigData exampleConfigData;

	// all sorts of attributes
	// player data handling - done
	// world data handling - done
	// clientside configs
	// serverside configs
	// player events
	// world events
	// client events
	// json-based command creation for datapacks

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Cosmological.");

		exampleConfigData = ExampleConfigData.load();

		ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
			exampleConfigData.save();
		});

		ClientPlayerBlockBreakEvents.AFTER.register(((world, player, pos, state) -> {
			if (state.isOf(Blocks.DIRT)) {
				ExamplePlayerComponent component = getOrDefaultPlayerComponent(player, ExamplePlayerComponent::new);
				component.dirtBroken++;
				setPlayerComponent(player, component);
			}
			if (state.isOf(Blocks.STONE)) {
				ExamplePlayerComponent component = getOrDefaultPlayerComponent(player, ExamplePlayerComponent::new);
				component.stoneBroken++;
				setPlayerComponent(player, component);
			}
		}));

		registerComponent(ExamplePlayerComponent::new);
	}

	public static void registerComponent(Supplier<BaseDataComponent> component) {
		components.add(component);
	}

	public static <T extends PlayerDataComponent> T getOrDefaultPlayerComponent(PlayerEntity player, Supplier<T> component) {
		BaseDataComponent dataComponent = ((PlayerComponentAccessible)player).cosmological$getComponentByID(component.get().getID());
		try {
			return (T)dataComponent;
		} catch (ClassCastException exception) {
			LOGGER.warn("Invalid component cast! Exception: " + exception);
			return component.get();
		}
	}

	public static <T extends PlayerDataComponent> T getPlayerComponent(PlayerEntity player, Identifier id) {
		PlayerDataComponent component = ((PlayerComponentAccessible)player).cosmological$getComponentByID(id);
		try {
			return (T)component;
		} catch (ClassCastException exception) {
			LOGGER.warn("Invalid component cast! Exception: " + exception);
			return null;
		}
	}

	public static <T extends PlayerDataComponent> void setPlayerComponent(PlayerEntity player, T component) {
		((PlayerComponentAccessible)player).cosmological$setComponent(component);
	}

	@Nullable
	public static <T extends BaseDataComponent> T getComponentByClass(Class<T> componentClass) {
		try {
			for (Supplier<? extends BaseDataComponent> component : components) {
				if (component.get().getClass() == componentClass) {
					return (T)component.get();
				}
			}
		} catch (ClassCastException exception) {
			LOGGER.warn("Invalid component cast! Exception: " + exception);
		}
		return null;
	}

	public static List<Supplier<? extends EntityDataComponent>> getEntityComponents(EntityType<Entity> entityType) {
		List<Supplier<? extends EntityDataComponent>> entityComponents = new ArrayList<>();
		for (Supplier<? extends BaseDataComponent> component : components) {
			try {
				if (component.get() instanceof EntityDataComponent entityComponent) {
					if (entityComponent.getEntityType() == entityType) {
						entityComponents.add((Supplier<? extends EntityDataComponent>)component);
					}
				}
			} catch (ClassCastException exception) {
				LOGGER.warn("Invalid component cast! Exception: " + exception);
			}
		}
		return entityComponents;
	}

	public static List<Supplier<? extends PlayerDataComponent>> getPlayerComponents() {
		List<Supplier<? extends PlayerDataComponent>> playerComponents = new ArrayList<>();
		for (Supplier<? extends BaseDataComponent> component : components) {
			try {
				if (component.get() instanceof PlayerDataComponent) {
					playerComponents.add((Supplier<? extends PlayerDataComponent>)component);
				}
			} catch (ClassCastException exception) {
				LOGGER.warn("Invalid component cast! Exception: " + exception);
			}
		}
		return playerComponents;
	}

	public static class ExamplePlayerComponent extends PlayerDataComponent {
		private static final Identifier ID = new Identifier(CosmologicaLib.MOD_ID, "example");

		private int dirtBroken = 0;
		private int stoneBroken = 0;

		@Override
		public Identifier getID() {
			return ID;
		}

		@Override
		public NbtCompound saveNbt() {
			NbtCompound nbt = new NbtCompound();
			nbt.putInt("dirt", dirtBroken);
			nbt.putInt("stone", stoneBroken);
			return nbt;
		}

		@Override
		public void readNbt(NbtCompound nbt) {
			dirtBroken = nbt.getInt("dirt");
			stoneBroken = nbt.getInt("stone");
		}
	}
}