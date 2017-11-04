package net.trentv.gasesdynamics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.trentv.gasesdynamics.GasesDynamics;

public class GuiGasFurnace extends GuiContainer
{
	private static final ResourceLocation BACKGROUND = new ResourceLocation(GasesDynamics.MODID, "textures/gui/container/gas_furnace.png");

	public GuiGasFurnace(Container inventorySlotsIn)
	{
		super(inventorySlotsIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BACKGROUND);
		drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);

		int xh = (width - xSize) / 2;
		int yh = (height - ySize) / 2;

		float fuelPercent = 1f;
		int fuelBarHeight = (int) (52 * fuelPercent);
		drawTexturedModalRect(xh + 38, yh + 17 + 52 - fuelBarHeight, 221, 1, 8, fuelBarHeight);

		float cookingPercentage = 1f;
		drawTexturedModalRect(xh + 79, yh + 35, 176, 14, (int) (24 * cookingPercentage), 17);

		int stage = 0; // 0 - 3
		boolean isCooking = (cookingPercentage >= 0);
		if (isCooking)
		{
			// Flame
			drawTexturedModalRect(xh + 59, yh + 35, 180, 50 + (18 * stage), 10, 18);
			// Pipe
			drawTexturedModalRect(xh + 47, yh + 52, 176, 31, 20, 12);
		}
		else
		{
			drawTexturedModalRect(xh + 59, yh + 35, 198, 50 + (18 * stage), 10, 18);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String name = I18n.format("tile.gas_furnace.name");
		fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
	}
}