
package net.mcreator.story.item;

import net.minecraft.network.chat.Component;

public class FItem extends RecordItem {

	public FItem() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.cave")), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE).stacksTo(1).rarity(Rarity.RARE), 100);
	}

}
