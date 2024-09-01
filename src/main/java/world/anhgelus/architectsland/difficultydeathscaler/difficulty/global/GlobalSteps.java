package world.anhgelus.architectsland.difficultydeathscaler.difficulty.global;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import world.anhgelus.architectsland.difficultydeathscaler.difficulty.DifficultyManager;

public class GlobalSteps {
    /**
     * Default step, is always reached
     */
    public static class Default extends DifficultyManager.Step {
        protected Default() {
            super(0);
        }

        @Override
        public void reached(MinecraftServer server, GameRules gamerules, DifficultyManager.Updater updater) {
            gamerules.get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set(30, server);
            gamerules.get(GameRules.NATURAL_REGENERATION).set(true, server);
            updater.updateDifficulty(2);
        }
    }

    public static class First extends DifficultyManager.Step {
        protected First() {
            super(5);
        }

        @Override
        public void reached(MinecraftServer server, GameRules gamerules, DifficultyManager.Updater updater) {
            gamerules.get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set(70, server);
        }
    }

    public static class Second extends DifficultyManager.Step {
        protected Second() {
            super(10);
        }

        @Override
        public void reached(MinecraftServer server, GameRules gamerules, DifficultyManager.Updater updater) {
            updater.updateDifficulty(3);
        }
    }

    public static class Third extends DifficultyManager.Step {
        protected Third() {
            super(15);
        }

        @Override
        public void reached(MinecraftServer server, GameRules gamerules, DifficultyManager.Updater updater) {
            gamerules.get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set(100, server);
        }
    }

    public static class Fourth extends DifficultyManager.Step {
        protected Fourth() {
            super(20);
        }

        @Override
        public void reached(MinecraftServer server, GameRules gamerules, DifficultyManager.Updater updater) {
            gamerules.get(GameRules.NATURAL_REGENERATION).set(false, server);
        }
    }
}
