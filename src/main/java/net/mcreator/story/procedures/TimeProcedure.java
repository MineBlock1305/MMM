package net.mcreator.story.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TimeProcedure {
	@SubscribeEvent
	public static void onPlayerInBed(PlayerSleepInBedEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.giveExperiencePoints(-((int) 0.1));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					Component.literal("\u044F \u0438\u0449\u043E \u043D\u0435 \u0432\u043E\u0441\u0442\u043E\u043D\u043E\u0432\u0438\u043B \u0440\u043E\u0441\u0443\u0434\u043E\u043A \u0448\u0442\u043E\u0431\u044B \u0441\u043F\u043E\u0442\u044C"),
					true);
	}
}
