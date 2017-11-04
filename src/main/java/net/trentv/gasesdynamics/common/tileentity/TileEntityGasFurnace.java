package net.trentv.gasesdynamics.common.tileentity;

import javax.annotation.Nullable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.trentv.gasesdynamics.GasesDynamics;

public class TileEntityGasFurnace extends TileEntity
{
	private ItemStackHandler inventory = new ItemStackHandler(2);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
		super.readFromNBT(nbt);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
	}

	public static class ContainerGasFurnace extends Container
	{
		public ContainerGasFurnace(InventoryPlayer playerInv, TileEntity tileEntity)
		{
			TileEntityGasFurnace tile = (TileEntityGasFurnace) tileEntity;
			IItemHandler inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

			// Two item slots for the container itself; ingredient and result

			this.addSlotToContainer(new SlotItemHandler(inv, 0, 56, 17)
			{
				@Override
				public void onSlotChanged()
				{
					tile.markDirty();
				}
			});
			this.addSlotToContainer(new SlotItemHandler(inv, 1, 116, 35)
			{
				@Override
				public void onSlotChanged()
				{
					tile.markDirty();
				}
			});

			// 3*9 + 9 player inventory slots

			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
				}
			}

			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
			}
		}

		@Override
		public boolean canInteractWith(EntityPlayer playerIn)
		{
			return true;
		}
	}

	public static class GuiGasFurnace extends GuiContainer
	{
		private static final ResourceLocation BACKGROUND = new ResourceLocation(GasesDynamics.MODID, "textures/gui/container/gasfurnace.png");

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
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
		{

		}
	}
}
