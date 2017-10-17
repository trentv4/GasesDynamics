package net.trentv.gasesdynamics.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
		player.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			if (player.isSneaking())
			{
				if (GFManipulationAPI.getGasLevel(pos, worldIn) > 0)
				{
					ItemStack stack = player.getHeldItem(hand);
					NBTTagCompound nbt = getNBT(stack);
					if (!nbt.hasKey("gastype"))
						nbt.setString("gastype", "gasesframework:air");
					nbt.setString("gastype", GFManipulationAPI.getGasType(pos, worldIn).getRegistryName().toString());
				}
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase player, int timeLeft)
	{
		if (!worldIn.isRemote)
		{
			NBTTagCompound nbt = getNBT(player.getActiveItemStack());
			nbt.setInteger("distance", 1);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if (player.isHandActive())
			{
				if (!world.isRemote)
				{
					NBTTagCompound nbt = getNBT(stack);
					if (!nbt.hasKey("fuel"))
						nbt.setInteger("fuel", 0);
					if (!nbt.hasKey("distance"))
						nbt.setInteger("distance", 1);
					if (!nbt.hasKey("gastype"))
						nbt.setString("gastype", "gasesframework:air");

					int fuel = nbt.getInteger("fuel");
					int distance = nbt.getInteger("distance");
					GasType heldGas = GFRegistrationAPI.getGasType(new ResourceLocation(nbt.getString("gastype")));
					if (heldGas == null)
						return;

					if (!player.isSneaking())
					{
						for (int i = 0; i < distance; i++)
						{
							Vec3d pos = player.getPositionEyes(0f).add(player.getLookVec().scale(i + 3));
							BlockPos bpos = new BlockPos(pos);
							if (!GFManipulationAPI.canPlaceGas(bpos, world, heldGas))
								break;

							if (fuel >= 16)
							{
								GFManipulationAPI.addGasLevel(bpos, world, heldGas, 16);
								fuel -= 16;
							}
							else if (fuel > 0)
							{
								GFManipulationAPI.addGasLevel(bpos, world, heldGas, fuel);
								fuel = 0;
							}

						}
						if (distance < 12)
							distance++;
					}

					nbt.setInteger("fuel", fuel);
					nbt.setInteger("distance", distance);
					nbt.setString("gastype", heldGas.getRegistryName().toString());
				}
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.NONE;
	}

	private NBTTagCompound getNBT(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			return stack.getTagCompound();
		}
		else
		{
			NBTTagCompound nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
			return nbt;
		}
	}
}
