package net.mcreator.story.procedures;

import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

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
						StoryMod.queueServerWork(48000, () -> {
						});
					});
				});
			}
		});
	}
}
