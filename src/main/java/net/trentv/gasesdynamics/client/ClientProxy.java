package net.trentv.gasesdynamics.client;

import net.minecraftforge.common.MinecraftForge;
import net.trentv.gasesdynamics.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{

	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}
}
