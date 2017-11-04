package net.trentv.gasesdynamics.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesdynamics.common.tileentity.TileEntityGasFurnace.ContainerGasFurnace;
import net.trentv.gasesdynamics.common.tileentity.TileEntityGasFurnace.GuiGasFurnace;

public class GuiHandlerGasesDynamics implements IGuiHandler
{
	public static void openGui(EntityPlayer player, int id, BlockPos pos)
	{
		player.openGui(GasesDynamics.instance, id, player.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ());
	}

	public static final int GAS_FURNACE = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case GAS_FURNACE:
				return new ContainerGasFurnace(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case GAS_FURNACE:
				return new GuiGasFurnace((Container) getServerGuiElement(ID, player, world, x, y, z));
			default:
				return null;
		}
	}
}
