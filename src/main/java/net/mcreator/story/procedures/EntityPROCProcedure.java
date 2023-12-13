package net.mcreator.story.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;

import net.mcreator.story.init.StoryModEntities;
import net.mcreator.story.entity.TestEntity;

public class EntityPROCProcedure {
	public static Entity execute(LevelAccessor world) {
		return world instanceof Level _level ? new TestEntity(StoryModEntities.TEST.get(), _level) : null;
	}
}
