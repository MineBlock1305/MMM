
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.story.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.story.world.inventory.KvestMenu;
import net.mcreator.story.world.inventory.DialogGUIMenu;
import net.mcreator.story.StoryMod;

public class StoryModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, StoryMod.MODID);
	public static final RegistryObject<MenuType<KvestMenu>> KVEST = REGISTRY.register("kvest", () -> IForgeMenuType.create(KvestMenu::new));
	public static final RegistryObject<MenuType<DialogGUIMenu>> DIALOG_GUI = REGISTRY.register("dialog_gui", () -> IForgeMenuType.create(DialogGUIMenu::new));
}
