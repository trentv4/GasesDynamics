package net.trentv.gasesdynamics.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesdynamics.GasesDynamicsRegistry;
import net.trentv.gasesdynamics.common.block.BlockGasFurnace;
import net.trentv.gasesdynamics.common.item.ItemGasGrenade;
import net.trentv.gasesdynamics.common.item.ItemGasthrower;
import net.trentv.gasesdynamics.common.tileentity.TileEntityGasFurnace;

public class GasesDynamicsObjects
{
	public static final ItemGasthrower GAS_THROWER = new ItemGasthrower();
	public static final ItemGasGrenade GAS_GRENADE = new ItemGasGrenade();

	public static final Block GAS_FURNACE = new BlockGasFurnace(Material.IRON).setCreativeTab(GasesDynamics.CREATIVE_TAB);

	public static void init()
	{
		GasesDynamicsRegistry.registerItem(GAS_THROWER, GAS_GRENADE);
		GasesDynamicsRegistry.registerBlockAndItem(GAS_FURNACE);

		GameRegistry.registerTileEntity(TileEntityGasFurnace.class, "gasesdynamics:gas_furnace");
	}
}
