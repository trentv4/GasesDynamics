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
import net.trentv.gasesdynamics.common.entity.projectile.EntityGasGrenade;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;

public class ItemGasGrenade extends Item
{
	public ItemGasGrenade()
	{
		setRegistryName(GasesDynamics.MODID, "gas_grenade");
		setCreativeTab(GasesDynamics.CREATIVE_TAB);
		setUnlocalizedName("gas_grenade");
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
			if (!world.isRemote)
			{
				GasType gastype = GFRegistrationAPI.getGasType(new ResourceLocation(nbt.getString("gastype")));
				EntityGasGrenade grenade = new EntityGasGrenade(world, player, gastype);
				grenade.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
				world.spawnEntity(grenade);
			}
			System.out.println(nbt.getString("gastype"));
		}

		return result;
	}
}
