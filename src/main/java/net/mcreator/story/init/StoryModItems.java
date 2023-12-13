
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.story.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

import net.mcreator.story.item.RebirthiconItem;
import net.mcreator.story.StoryMod;

public class StoryModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, StoryMod.MODID);
	public static final RegistryObject<Item> REBIRTHICON = REGISTRY.register("rebirthicon", () -> new RebirthiconItem());
	public static final RegistryObject<Item> TEST_SPAWN_EGG = REGISTRY.register("test_spawn_egg", () -> new ForgeSpawnEggItem(StoryModEntities.TEST, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
