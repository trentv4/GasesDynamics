package net.trentv.gases;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.gases.common.CommonProxy;
import net.trentv.gases.common.GasesDynamicsObjects;

@Mod(modid = GasesDynamics.MODID, version = GasesDynamics.VERSION, acceptedMinecraftVersions = "1.10.2", dependencies = "required-after:gasesframework")
public class GasesDynamics
{
	public static final String MODID = "gasesdynamics";
	public static final String VERSION = "1.0.0";

	public static final GasesDynamicsCreativeTab CREATIVE_TAB = new GasesDynamicsCreativeTab("gasesdynamics");

	public static Logger logger;

	@SidedProxy(clientSide = "net.trentv.gasesdynamics.client.ClientProxy", serverSide = "net.trentv.gasesdynamics.server.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		GasesDynamicsObjects.init();

		proxy.registerEventHandlers();
		proxy.registerRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("'Gases Dynamics' initialized");
	}

	private static class GasesDynamicsCreativeTab extends CreativeTabs
	{
		public GasesDynamicsCreativeTab(String label)
		{
			super(label);
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Items.FILLED_MAP);
		}
	}
}
