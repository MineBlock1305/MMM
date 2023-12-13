package net.mcreator.story.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.story.network.StoryModVariables;

public class Choose3ifProcedure {
	public static boolean execute(LevelAccessor world) {
		if (StoryModVariables.MapVariables.get(world).dialognumber == 2) {
			return true;
		}
		return false;
	}
}
