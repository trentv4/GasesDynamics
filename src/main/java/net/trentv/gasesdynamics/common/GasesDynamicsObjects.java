package net.trentv.gasesdynamics.common;

import net.trentv.gasesdynamics.GasesDynamicsRegistry;
import net.trentv.gasesdynamics.common.item.ItemGasthrower;

public class GasesDynamicsObjects
{
	public static final ItemGasthrower GAS_THROWER = new ItemGasthrower();

	public static void init()
	{
		GasesDynamicsRegistry.registerItem(GAS_THROWER);
	}
}
