package net.mcreator.story.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.story.network.StoryModVariables;

public class Choose2procProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (StoryModVariables.MapVariables.get(world).dialognumber == 1) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Test: \u0412\u0442\u043E\u0440\u043E\u0439 \u0432\u0430\u0440\u0438\u0430\u043D\u0442 1"), false);
		} else if (StoryModVariables.MapVariables.get(world).dialognumber == 2) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Test: \u0412\u0442\u043E\u0440\u043E\u0439 \u0432\u0430\u0440\u0438\u0430\u043D\u0442 2"), false);
		}
	}
}
