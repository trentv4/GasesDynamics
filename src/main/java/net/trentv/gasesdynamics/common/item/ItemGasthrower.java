package net.trentv.gasesdynamics.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;

public class ItemGasthrower extends Item
{
	public ItemGasthrower()
	{
		setRegistryName(GasesDynamics.MODID, "gasthrower");
		setCreativeTab(GasesDynamics.CREATIVE_TAB);
		setUnlocalizedName("gasthrower");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ActionResult<ItemStack> result = new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));

		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt;

		if (stack.hasTagCompound())
		{
			nbt = stack.getTagCompound();
		}
		else
		{
			nbt = new NBTTagCompound();
			nbt.setInteger("fuel", Integer.MAX_VALUE);
			nbt.setString("gastype", "gasesframework:air");
			stack.setTagCompound(nbt);
		}

		if (player.isSneaking())
		{
			RayTraceResult target = world.rayTraceBlocks(player.getPositionEyes(0), player.getPositionEyes(0).add(player.getLookVec().scale(200)));

			if (target != null && target.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				BlockPos pos = target.getBlockPos();
				if (GFManipulationAPI.getGasLevel(pos, world) > 0)
				{
					nbt.setString("gastype", GFManipulationAPI.getGasType(pos, world).getRegistryName().toString());
				}
			}
		}
		else
		{
			int nozzleDistance = 10;

			int fuel = nbt.getInteger("fuel");
			GasType gastype = GFRegistrationAPI.getGasType(new ResourceLocation(nbt.getString("gastype")));

			for (int i = 0; i < nozzleDistance; i++)
			{
				BlockPos pos = new BlockPos(player.getPositionEyes(0).add(player.getLookVec().scale(i + 2)));
				if (!GFManipulationAPI.canPlaceGas(pos, world, gastype))
					break;

				if (fuel >= 16)
				{
					GFManipulationAPI.addGasLevel(pos, world, gastype, 16);
					fuel -= 16;
				}
				else if (fuel > 0)
				{
					GFManipulationAPI.addGasLevel(pos, world, gastype, fuel);
					fuel = 0;
				}
			}
			System.out.println(nbt.getString("gastype"));
		}

		return result;
	}
}
