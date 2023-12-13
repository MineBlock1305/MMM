package net.mcreator.story.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.story.network.StoryModVariables;
import net.mcreator.story.StoryMod;

import javax.annotation.Nullable;

import java.util.Iterator;

@Mod.EventBusSubscriber
public class StartProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level, event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		StoryMod.queueServerWork(100, () -> {
			if (StoryModVariables.MapVariables.get(world).globalevents == 0) {
				StoryModVariables.MapVariables.get(world).globalevents = 1;
				StoryModVariables.MapVariables.get(world).syncData(world);
				{
					Entity _ent = entity;
					_ent.teleportTo(100, 10, 100);
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(100, 10, 100, _ent.getYRot(), _ent.getXRot());
				}
				StoryMod.queueServerWork(20, () -> {
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("story:rebirth"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							Iterator _iterator = _ap.getRemainingCriteria().iterator();
							while (_iterator.hasNext())
								_player.getAdvancements().award(_adv, (String) _iterator.next());
						}
					}
					StoryMod.queueServerWork(20, () -> {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(Component.literal(("[" + entity.getDisplayName().getString() + "]: \u042F... \u042F \u0436\u0438\u0432?")), false);
					});
				});
			}
		});
	}
}
