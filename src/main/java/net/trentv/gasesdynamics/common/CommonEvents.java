package net.trentv.gasesdynamics.common;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesdynamics.common.entity.projectile.EntityGasGrenade;

public class CommonEvents
{
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		event.getRegistry().register(new EntityEntry(EntityGasGrenade.class, "gas_grenade").setRegistryName(GasesDynamics.MODID, "gas_grenade"));
	}
}
