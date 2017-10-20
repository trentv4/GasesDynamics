package net.trentv.gasesdynamics.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.trentv.gasesdynamics.common.CommonProxy;
import net.trentv.gasesdynamics.common.entity.projectile.EntityGasGrenade;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGasGrenade.class, new GasGrenadeRenderFactory());
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}

	private class GasGrenadeRenderFactory implements IRenderFactory<EntityGasGrenade>
	{
		@Override
		public Render<? super EntityGasGrenade> createRenderFor(RenderManager manager)
		{
			Minecraft m = Minecraft.getMinecraft();
			return new RenderSnowball<EntityGasGrenade>(m.getRenderManager(), Items.EGG, m.getRenderItem());
		}

	}
}
