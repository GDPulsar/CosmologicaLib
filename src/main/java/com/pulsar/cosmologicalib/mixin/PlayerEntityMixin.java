package com.pulsar.cosmologicalib.mixin;

import com.mojang.authlib.GameProfile;
import com.pulsar.cosmologicalib.ComponentAccessible;
import com.pulsar.cosmologicalib.CosmologicaLib;
import com.pulsar.cosmologicalib.PlayerComponentAccessible;
import com.pulsar.cosmologicalib.data.BaseDataComponent;
import com.pulsar.cosmologicalib.data.PlayerDataComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerComponentAccessible {
	@Unique
	private HashMap<Identifier, PlayerDataComponent> dataComponents = new HashMap<>();

	@Inject(method = "<init>", at = @At("TAIL"))
	private void cosmological$componentInit(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo ci) {
		dataComponents = new HashMap<>();
		for (Supplier<? extends PlayerDataComponent> component : CosmologicaLib.getPlayerComponents()) {
			PlayerDataComponent dataComponent = component.get();
			dataComponents.put(dataComponent.getID(), dataComponent);
		}
	}

	@Override
	public PlayerDataComponent cosmological$getComponentByID(Identifier id) {
		return dataComponents.getOrDefault(id, null);
	}

	@Override
	public void cosmological$setComponent(PlayerDataComponent component) {
		dataComponents.put(component.getID(), component);
	}

	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	private void cosmological$writeComponentNbt(NbtCompound nbt, CallbackInfo ci) {
		NbtCompound components = new NbtCompound();
		for (Map.Entry<Identifier, PlayerDataComponent> component : dataComponents.entrySet()) {
			components.put(component.getKey().toString(), component.getValue().saveNbt());
		}
		nbt.put("cosmologicalib", components);
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	private void cosmological$readComponentNbt(NbtCompound nbt, CallbackInfo ci) {
		NbtCompound components = nbt.getCompound("cosmologicalib");
		for (Supplier<? extends PlayerDataComponent> component : CosmologicaLib.getPlayerComponents()) {
			PlayerDataComponent dataComponent = component.get();
			if (dataComponents.containsKey(dataComponent.getID())) {
				dataComponents.get(dataComponent.getID()).readNbt(components.getCompound(dataComponent.getID().toString()));
			}
		}
	}
}