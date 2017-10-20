package net.trentv.gasesdynamics.common;

import net.trentv.gasesdynamics.GasesDynamicsRegistry;
import net.trentv.gasesdynamics.common.item.ItemGasGrenade;
import net.trentv.gasesdynamics.common.item.ItemGasthrower;

public class GasesDynamicsObjects
{
	public static final ItemGasthrower GAS_THROWER = new ItemGasthrower();
	public static final ItemGasGrenade GAS_GRENADE = new ItemGasGrenade();

	public static void init()
	{
		GasesDynamicsRegistry.registerItem(GAS_THROWER, GAS_GRENADE);
	}
}
