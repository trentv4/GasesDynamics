package net.trentv.gasesdynamics.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.trentv.gasesdynamics.GasesDynamics;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;

public class ItemGasthrower extends Item
{
	private GasType heldGas;
	private int fuel = 0;
	private int distance = 1;
	
	public ItemGasthrower()
	{
		setRegistryName(GasesDynamics.MODID, "gasthrower");
		setCreativeTab(GasesDynamics.CREATIVE_TAB);
		setUnlocalizedName("gasthrower");
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(!world.isRemote)
		{
			if(!player.isSneaking())
			{
				for(int i = 0; i < distance; i++)
				{
					Vec3d pos = player.getPositionEyes(0f).add(player.getLookVec().scale(i + 2));
					GFManipulationAPI.addGasLevel(new BlockPos(pos), world, heldGas, 8);

					if(distance < 5) distance++;
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
	
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(!worldIn.isRemote)
    	{
        	if(player.isSneaking())
        	{
            	if(GFManipulationAPI.getGasLevel(pos, worldIn) > 0)
            	{
            		heldGas = GFManipulationAPI.getGasType(pos, worldIn);
            		fuel += 10;
            		System.out.println("Gassing up: " + heldGas.name + " at " + fuel);
            	}
        	}
    	}
        return EnumActionResult.PASS;
    }
}
