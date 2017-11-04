package net.trentv.gasesdynamics.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesdynamics.common.GuiHandlerGasesDynamics;
import net.trentv.gasesdynamics.common.tileentity.TileEntityGasFurnace;

public class BlockGasFurnace extends Block
{
	public BlockGasFurnace(Material materialIn)
	{
		super(materialIn);
		setRegistryName(GasesDynamics.MODID, "gasfurnace");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			GuiHandlerGasesDynamics.openGui(player, GuiHandlerGasesDynamics.GAS_FURNACE, pos);
		}
		return true;
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityGasFurnace();
	}
}
