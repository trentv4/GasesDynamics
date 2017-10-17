package net.trentv.gases.common;

import net.minecraftforge.common.MinecraftForge;
import net.trentv.gases.GasesDynamicsRegistry;

public abstract class CommonProxy
{
	public abstract void registerRenderers();

	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		MinecraftForge.EVENT_BUS.register(new GasesDynamicsRegistry());		
	}
}
