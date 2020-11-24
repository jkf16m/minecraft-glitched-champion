package net.whitecat.champions.EntityType;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class ChampionEntity extends HostileEntity {

    public ChampionEntity(EntityType<? extends HostileEntity> entityType, World world){
        super(entityType,world);
    }
}
