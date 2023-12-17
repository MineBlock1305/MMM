package net.mcreator.story.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.story.network.StoryModVariables;
import net.mcreator.story.init.StoryModItems;

public class SwordNotProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (StoryModVariables.MapVariables.get(world).globalevents != 3) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == StoryModItems.ZAGHADOCHNYIIECH.get()) {
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), (int) Double.POSITIVE_INFINITY);
			}
		}
	}
}
