package net.whitecat.champions.mixin;

import jdk.nashorn.internal.codegen.CompilerConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.GameMode;
import net.whitecat.champions.Utils;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {


    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract void setGameMode(GameMode gameMode);

    @Shadow public abstract void disableShield(boolean sprinting);


    @Inject(at=@At("TAIL"), method="tick")
    public void onTick(CallbackInfo info){

        System.out.println();

    }

    @Inject(at=@At("TAIL"), method="jump")
    public void onJumping(CallbackInfo info){
        System.out.println("reduce 20 air");
        this.setAir(this.getAir()-50);
    }

    @Inject(at=@At(value="TAIL"), method="isSwimming")
    public void onSwimming(CallbackInfoReturnable info){
        this.setAir(this.getAir()-3);
    }



    @Inject(at=@At("TAIL"), method="takeShieldHit")
    public void onShieldDamage(LivingEntity attacker, CallbackInfo info){
        System.out.println("reduce 40 air");
        if(attacker instanceof CreeperEntity){
            disableShield(true);
            System.out.println("disable shield");
        }
    }

    @Inject(at=@At("TAIL"), method="attack")
    public void onAttacking(CallbackInfo info){
        System.out.println("reduce 20 air");
    }
}

@Mixin(Entity.class)
abstract class EntityMixin{
    @Shadow public abstract void setAir(int air);

    @Shadow public abstract int getAir();

    @Inject(at=@At("TAIL"), method="isSprinting")
    public void onSprinting(CallbackInfoReturnable info){
        setAir(getAir()-3);
    }
}
