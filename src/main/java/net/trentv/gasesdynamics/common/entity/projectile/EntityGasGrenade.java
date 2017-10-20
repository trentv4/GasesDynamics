package net.trentv.gasesdynamics.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;

public class EntityGasGrenade extends EntityThrowable
{
	private GasType gastype;

	public EntityGasGrenade(World worldIn)
	{
		super(worldIn);
	}

	public EntityGasGrenade(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}

	public EntityGasGrenade(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	public EntityGasGrenade(World world, EntityLivingBase thrower, GasType g)
	{
		super(world, thrower);
		gastype = g;
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (!this.world.isRemote)
		{
			GFManipulationAPI.addGasLevelOverflow(getPosition(), world, gastype, 16 * 150);

			this.setDead();
		}
	}
}
