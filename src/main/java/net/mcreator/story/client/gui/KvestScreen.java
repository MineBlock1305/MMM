package net.mcreator.story.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import net.mcreator.story.world.inventory.KvestMenu;
import net.mcreator.story.network.KvestButtonMessage;
import net.mcreator.story.StoryMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class KvestScreen extends AbstractContainerScreen<KvestMenu> {
	private final static HashMap<String, Object> guistate = KvestMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox kwest;
	EditBox pob1;
	ImageButton imagebutton_dv;

	public KvestScreen(KvestMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("story:textures/screens/kvest.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		kwest.render(ms, mouseX, mouseY, partialTicks);
		pob1.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("story:textures/screens/s2.png"));
		this.blit(ms, this.leftPos + 9, this.topPos + 56, 0, 0, 32, 32, 32, 32);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (kwest.isFocused())
			return kwest.keyPressed(key, b, c);
		if (pob1.isFocused())
			return pob1.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		kwest.tick();
		pob1.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		kwest = new EditBox(this.font, this.leftPos + 45, this.topPos + 15, 120, 20, Component.translatable("gui.story.kvest.kwest"));
		kwest.setMaxLength(32767);
		guistate.put("text:kwest", kwest);
		this.addWidget(this.kwest);
		pob1 = new EditBox(this.font, this.leftPos + 44, this.topPos + 61, 120, 20, Component.translatable("gui.story.kvest.pob1"));
		pob1.setMaxLength(32767);
		guistate.put("text:pob1", pob1);
		this.addWidget(this.pob1);
		imagebutton_dv = new ImageButton(this.leftPos + 9, this.topPos + 10, 32, 32, 0, 0, 32, new ResourceLocation("story:textures/screens/atlas/imagebutton_dv.png"), 32, 64, e -> {
			if (true) {
				StoryMod.PACKET_HANDLER.sendToServer(new KvestButtonMessage(0, x, y, z));
				KvestButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_dv", imagebutton_dv);
		this.addRenderableWidget(imagebutton_dv);
	}
}
