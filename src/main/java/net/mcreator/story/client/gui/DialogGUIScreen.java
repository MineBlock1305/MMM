package net.mcreator.story.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import net.mcreator.story.world.inventory.DialogGUIMenu;
import net.mcreator.story.procedures.EntityPROCProcedure;
import net.mcreator.story.procedures.Choose3ifProcedure;
import net.mcreator.story.network.DialogGUIButtonMessage;
import net.mcreator.story.StoryMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class DialogGUIScreen extends AbstractContainerScreen<DialogGUIMenu> {
	private final static HashMap<String, Object> guistate = DialogGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_select;
	ImageButton imagebutton_s1;
	ImageButton imagebutton_s11;

	public DialogGUIScreen(DialogGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 224;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("story:textures/screens/dialog_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		if (EntityPROCProcedure.execute(world) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryRaw(this.leftPos + 194, this.topPos + 64, 20, 0f + (float) Math.atan((this.leftPos + 194 - mouseX) / 40.0), (float) Math.atan((this.topPos + 14 - mouseY) / 40.0), livingEntity);
		}
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("story:textures/screens/small.png"));
		this.blit(ms, this.leftPos + 177, this.topPos + 40, 0, 0, 32, 32, 32, 32);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
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
		imagebutton_select = new ImageButton(this.leftPos + 12, this.topPos + 7, 32, 32, 0, 0, 32, new ResourceLocation("story:textures/screens/atlas/imagebutton_select.png"), 32, 64, e -> {
			if (true) {
				StoryMod.PACKET_HANDLER.sendToServer(new DialogGUIButtonMessage(0, x, y, z));
				DialogGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_select", imagebutton_select);
		this.addRenderableWidget(imagebutton_select);
		imagebutton_s1 = new ImageButton(this.leftPos + 12, this.topPos + 43, 32, 32, 0, 0, 32, new ResourceLocation("story:textures/screens/atlas/imagebutton_s1.png"), 32, 64, e -> {
			if (true) {
				StoryMod.PACKET_HANDLER.sendToServer(new DialogGUIButtonMessage(1, x, y, z));
				DialogGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:imagebutton_s1", imagebutton_s1);
		this.addRenderableWidget(imagebutton_s1);
		imagebutton_s11 = new ImageButton(this.leftPos + 12, this.topPos + 79, 32, 32, 0, 0, 32, new ResourceLocation("story:textures/screens/atlas/imagebutton_s11.png"), 32, 64, e -> {
			if (Choose3ifProcedure.execute(world)) {
				StoryMod.PACKET_HANDLER.sendToServer(new DialogGUIButtonMessage(2, x, y, z));
				DialogGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (Choose3ifProcedure.execute(world))
					super.render(ms, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_s11", imagebutton_s11);
		this.addRenderableWidget(imagebutton_s11);
	}
}
