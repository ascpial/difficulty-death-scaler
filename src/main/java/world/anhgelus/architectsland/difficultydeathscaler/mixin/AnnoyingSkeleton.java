package world.anhgelus.architectsland.difficultydeathscaler.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import world.anhgelus.architectsland.difficultydeathscaler.difficulty.global.GlobalDifficultyManager;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AnnoyingSkeleton extends HostileEntity {
    protected AnnoyingSkeleton(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "initGoals")
    protected void newGoals(CallbackInfo ci) {
        if (!GlobalDifficultyManager.betterSkeleton()) return;
        // flee Player
        goalSelector.add(3, new FleeEntityGoal<>(
                (AbstractSkeletonEntity) (Object)this,
                PlayerEntity.class,
                1.5f,
                1.2,
                1.3)
        );
    }

    @Inject(at = @At("RETURN"), method = "initGoals")
    protected void betterGoals(CallbackInfo ci) {
        if (!GlobalDifficultyManager.betterSkeleton()) return;
        for (Goal g : targetSelector.getGoals()) {
            if (g instanceof RevengeGoal) {
                targetSelector.remove(g);
            }
        }
        // kill player become more important than defending itself
        targetSelector.add(2, new RevengeGoal(this));
        targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }
}
