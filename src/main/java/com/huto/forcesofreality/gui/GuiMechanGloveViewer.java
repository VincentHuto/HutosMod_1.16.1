package com.huto.forcesofreality.gui;

import java.util.ArrayList;
import java.util.List;

import com.huto.forcesofreality.ForcesOfReality;
import com.huto.forcesofreality.container.MechanGloveItemHandler;
import com.huto.forcesofreality.gui.pages.GuiButtonTextured;
import com.huto.forcesofreality.gui.pages.GuiUtil;
import com.huto.forcesofreality.item.coven.tool.ItemMechanGlove;
import com.huto.forcesofreality.item.tool.coven.module.ItemMechanModuleBase;
import com.huto.forcesofreality.network.PacketHandler;
import com.huto.forcesofreality.network.coven.PacketUpdateMechanModule;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@OnlyIn(Dist.CLIENT)
public class GuiMechanGloveViewer extends Screen {
	final ResourceLocation texture = new ResourceLocation(ForcesOfReality.MOD_ID, "textures/gui/mechan_viewer_gui.png");
	int guiWidth = 145;
	int guiHeight = 149;
	int left, top;
	ItemStack icon;
	Minecraft mc = Minecraft.getInstance();
	PlayerEntity player;
	public MechanGloveItemHandler handler;

	@OnlyIn(Dist.CLIENT)
	public GuiMechanGloveViewer(ItemStack currentBinderIn, PlayerEntity playerIn) {
		super(new StringTextComponent("View All Patterns"));
		this.icon = currentBinderIn;
		this.player = playerIn;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		int centerX = (width / 2) - guiWidth / 2;
		int centerY = (height / 2) - guiHeight / 2;
		this.renderBackground(matrixStack);

		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			GuiUtil.drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth - 1, guiHeight);

		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(matrixStack, mouseX, mouseY, 511);
			if (buttons.get(i).isHovered()) {
				ItemStack stack = ForcesOfReality.findMechanGlove(player);
				if (stack != null && stack != ItemStack.EMPTY && stack.getItem() instanceof ItemMechanGlove) {
					IItemHandler binderHandler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
							.orElseThrow(NullPointerException::new);
					if (binderHandler.getStackInSlot(i).getItem() instanceof ItemMechanModuleBase) {
						ItemMechanModuleBase pat = (ItemMechanModuleBase) binderHandler.getStackInSlot(i).getItem();
						List<ITextComponent> text = new ArrayList<ITextComponent>();
						text.add(new StringTextComponent(I18n.format(pat.getItem().getName().getString())));
						func_243308_b(matrixStack, text, mouseX, mouseY);
					}
				}
			}
		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		{
			ItemStack stack = ForcesOfReality.findMechanGlove(player);
			if (stack != null && stack != ItemStack.EMPTY && stack.getItem() instanceof ItemMechanGlove) {
				IItemHandler binderHandler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
						.orElseThrow(NullPointerException::new);
				if (binderHandler instanceof MechanGloveItemHandler) {
					handler = (MechanGloveItemHandler) binderHandler;
					handler.load();
					slotcount = handler.getSlots();
					itemKey = stack.getTranslationKey();
					for (int i = 0; i < buttons.size(); i++) {
						GlStateManager.translatef(0, 0, 1);
						RenderHelper.enableStandardItemLighting();
						if (binderHandler.getStackInSlot(i).getItem() instanceof ItemMechanModuleBase) {
							ItemMechanModuleBase pat = (ItemMechanModuleBase) binderHandler.getStackInSlot(i).getItem();
							Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(
									new ItemStack(pat.getItem()), (buttons.get(i).x + 2), buttons.get(i).y + 2);
						}
					}
				}
			}
		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef(centerX, centerY, 0);
			GlStateManager.translatef(3, 3, 10);
			GlStateManager.scalef(1.9f, 1.7f, 1.9f);
			RenderHelper.enableStandardItemLighting();
			Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(icon, -1, -1);

		}
		GlStateManager.popMatrix();

	}

	public int slotcount = 0;
	public String itemKey = "";

	@Override
	protected void init() {
		left = width / 2 - guiWidth / 2;
		top = height / 2 - guiHeight / 2;
		int sideLoc = left + guiWidth;
		int verticalLoc = top + guiHeight;
		buttons.clear();
		ItemStack stack = ForcesOfReality.findMechanGlove(player);
		if (stack != null && stack != ItemStack.EMPTY && stack.getItem() instanceof ItemMechanGlove) {
			IItemHandler binderHandler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
					.orElseThrow(NullPointerException::new);
			if (stack.getItem() instanceof ItemMechanGlove) {
				ItemMechanGlove glove = (ItemMechanGlove) stack.getItem();
				if (binderHandler instanceof MechanGloveItemHandler) {
					handler = (MechanGloveItemHandler) binderHandler;
					handler.load();
					slotcount = handler.getSlots();
					itemKey = stack.getTranslationKey();
					for (int i = 0; i < binderHandler.getSlots(); i++) {
						if (i < 3) {
							this.addButton(new GuiButtonTextured(texture, i, sideLoc - (guiWidth - 30),
									(verticalLoc - 130) + (i * 45), 20, 20, 174, 98, null, new IPressable() {
										@SuppressWarnings("unused")
										@Override
										public void onPress(Button press) {
											if (press instanceof GuiButtonTextured) {
												player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 0.40f, 1F);
												ItemStack currentStack = binderHandler
														.getStackInSlot(((GuiButtonTextured) press).getId());
												if (currentStack.getItem() instanceof ItemMechanModuleBase) {
													ItemMechanModuleBase pat = (ItemMechanModuleBase) currentStack
															.getItem();
													PacketHandler.HANDLER.sendToServer(new PacketUpdateMechanModule(
															((GuiButtonTextured) press).getId(), currentStack));
													glove.setModuleStack(currentStack);
												}
											}
										}

									}));
						} else if (i < 6) {
							this.addButton(new GuiButtonTextured(texture, i, sideLoc - (guiWidth - 63),
									(verticalLoc - 265) + (i * 45), 20, 20, 174, 98, null, new IPressable() {
										@SuppressWarnings("unused")
										@Override
										public void onPress(Button press) {
											if (press instanceof GuiButtonTextured) {
												player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 0.40f, 1F);
												ItemStack currentStack = binderHandler
														.getStackInSlot(((GuiButtonTextured) press).getId());
												if (currentStack.getItem() instanceof ItemMechanModuleBase) {
													ItemMechanModuleBase pat = (ItemMechanModuleBase) currentStack
															.getItem();
													PacketHandler.HANDLER.sendToServer(new PacketUpdateMechanModule(
															((GuiButtonTextured) press).getId(), currentStack));
													glove.setModuleStack(currentStack);

												}
											}
										}
									}));
						} else if (i < 9) {
							this.addButton(new GuiButtonTextured(texture, i, sideLoc - (guiWidth - 95),
									(verticalLoc - 400) + (i * 45), 20, 20, 174, 98, null, new IPressable() {
										@SuppressWarnings("unused")
										@Override
										public void onPress(Button press) {
											if (press instanceof GuiButtonTextured) {
												ItemStack currentStack = binderHandler
														.getStackInSlot(((GuiButtonTextured) press).getId());
												player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 0.40f, 1F);
												if (currentStack.getItem() instanceof ItemMechanModuleBase) {
													ItemMechanModuleBase pat = (ItemMechanModuleBase) currentStack
															.getItem();
													PacketHandler.HANDLER.sendToServer(new PacketUpdateMechanModule(
															((GuiButtonTextured) press).getId(), currentStack));
													glove.setModuleStack(currentStack);

												}
											}
										}
									}));
						} else if (i < 12) {
							this.addButton(new GuiButtonTextured(texture, i, sideLoc - (guiWidth - 130),
									(verticalLoc - 410) + (i * 45), 20, 20, 174, 98, null, new IPressable() {
										@SuppressWarnings("unused")
										@Override
										public void onPress(Button press) {
											if (press instanceof GuiButtonTextured) {
												ItemStack currentStack = binderHandler
														.getStackInSlot(((GuiButtonTextured) press).getId());
												player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 0.40f, 1F);
												if (currentStack.getItem() instanceof ItemMechanModuleBase) {
													ItemMechanModuleBase pat = (ItemMechanModuleBase) currentStack
															.getItem();
													PacketHandler.HANDLER.sendToServer(new PacketUpdateMechanModule(
															((GuiButtonTextured) press).getId(), currentStack));
													glove.setModuleStack(currentStack);

												}
											}
										}
									}));
						}
					}
				}
			}
		}
		super.init();
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
