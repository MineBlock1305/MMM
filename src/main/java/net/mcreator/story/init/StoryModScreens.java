
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.story.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.story.client.gui.KvestScreen;
import net.mcreator.story.client.gui.DialogGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StoryModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(StoryModMenus.KVEST.get(), KvestScreen::new);
			MenuScreens.register(StoryModMenus.DIALOG_GUI.get(), DialogGUIScreen::new);
		});
	}
}
