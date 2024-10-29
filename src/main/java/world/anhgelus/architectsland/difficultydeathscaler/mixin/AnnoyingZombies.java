package world.anhgelus.architectsland.difficultydeathscaler.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LongDoorInteractGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import world.anhgelus.architectsland.difficultydeathscaler.difficulty.global.GlobalDifficultyManager;
import world.anhgelus.architectsland.difficultydeathscaler.utils.GoalsUtils;

@Mixin(ZombieEntity.class)
public abstract class AnnoyingZombies extends HostileEntity {
    protected AnnoyingZombies(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "initGoals")
    protected void newGoals(CallbackInfo ci) {
        if (!GlobalDifficultyManager.betterZombies()) return;
        goalSelector.add(3, new LongDoorInteractGoal(this, false));
    }

    @Inject(at = @At("RETURN"), method = "initGoals")
    protected void betterGoals(CallbackInfo ci) {
        if (GlobalDifficultyManager.betterZombies()) GoalsUtils.commonBetterGoals(this, targetSelector);
    }
}
