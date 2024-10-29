package world.anhgelus.architectsland.difficultydeathscaler.utils;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;

public class GoalsUtils {
    public static void commonBetterGoals(HostileEntity e, GoalSelector targetSelector) {
        for (Goal g : targetSelector.getGoals()) {
            if (g instanceof RevengeGoal) {
                targetSelector.remove(g);
            }
        }
        // kill player become more important than defending itself
        targetSelector.add(2, new RevengeGoal(e));
        targetSelector.add(1, new ActiveTargetGoal<>(e, PlayerEntity.class, true));
    }
}
