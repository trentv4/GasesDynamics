package net.trentv.gasesdynamics.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesdynamics.common.entity.projectile.EntityGasGrenade;

public class CommonEvents
{
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		event.getRegistry().register(EntityEntryBuilder.create().entity(EntityGasGrenade.class).tracker(160, 1, false).name("gas_grenade").id(new ResourceLocation(GasesDynamics.MODID, "gas_grenade"), 0).build());
	}
}
