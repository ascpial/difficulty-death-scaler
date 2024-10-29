package world.anhgelus.architectsland.difficultydeathscaler.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import world.anhgelus.architectsland.difficultydeathscaler.difficulty.global.GlobalDifficultyManager;

@Mixin(CreeperIgniteGoal.class)
public abstract class AnnoyingCreeper {
    private @Shadow @Final CreeperEntity creeper;
    private @Shadow LivingEntity target;

    @Inject(at = @At("RETURN"), method = "canStart", cancellable = true)
    public void canStart(CallbackInfoReturnable<Boolean> cir) {
        if (!GlobalDifficultyManager.betterCreepers()) return;
        final var livingEntity = creeper.getTarget();
        cir.setReturnValue(creeper.getFuseSpeed() > 0 || livingEntity != null && creeper.squaredDistanceTo(livingEntity) < 18.0);
    }

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci) {
        if (!GlobalDifficultyManager.betterCreepers()) return;
        if (this.target == null) {
            this.creeper.setFuseSpeed(-1);
            return;
        }
        if (this.creeper.squaredDistanceTo(this.target) > 20.0) {
            this.creeper.setFuseSpeed(-1);
            return;
        }
        if (!this.creeper.getVisibilityCache().canSee(this.target)) {
            this.creeper.setFuseSpeed(-1);
            return;
        }
        creeper.setFuseSpeed(2);
        ci.cancel();
    }
}
