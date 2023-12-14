package net.mcreator.story.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.story.world.inventory.KvestMenu;
import net.mcreator.story.network.StoryModVariables;

import java.util.HashMap;

public class KwestProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (entity instanceof Player _plr ? _plr.containerMenu instanceof KvestMenu : false) {
			if (StoryModVariables.MapVariables.get(world).kwest == 1) {
				if (guistate.get("text:kwest") instanceof EditBox _tf)
					_tf.setValue("\u041E\u0441\u043E\u0437\u043D\u0430\u043D\u0438\u0435.");
			}
		}
	}
}
