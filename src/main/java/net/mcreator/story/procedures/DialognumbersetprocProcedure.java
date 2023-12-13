package net.mcreator.story.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.story.network.StoryModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class DialognumbersetprocProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		StoryModVariables.MapVariables.get(world).dialognumber = DoubleArgumentType.getDouble(arguments, "dialognumber");
		StoryModVariables.MapVariables.get(world).syncData(world);
	}
}
