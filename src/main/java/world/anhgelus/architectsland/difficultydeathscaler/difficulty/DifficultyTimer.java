package world.anhgelus.architectsland.difficultydeathscaler.difficulty;

import org.jetbrains.annotations.Nullable;
import world.anhgelus.architectsland.difficultydeathscaler.DifficultyDeathScaler;

import java.util.Timer;
import java.util.TimerTask;

public class DifficultyTimer {
    protected long initialDelay = 0;
    protected long timerStart = System.currentTimeMillis() / 1000;

    protected Timer timer;

    protected void delayFirstTask(long delay) {
        initialDelay = delay;
    }

    protected void executeTask(TimerTask task, @Nullable TimerTask pastTask, long repeatEach) {
        executeTask(task, pastTask, repeatEach, repeatEach);
    }

    protected void executeTask(TimerTask task, @Nullable TimerTask pastTask, long delay, long repeatEach) {
        if (pastTask == null && initialDelay != 0) {
            try {
                timer.schedule(task, (delay - initialDelay) * 1000L, repeatEach * 1000L);
                timerStart -= initialDelay;
            } catch (IllegalArgumentException e) {
                DifficultyDeathScaler.LOGGER.error("An exception occurred while launching the first task", e);
                DifficultyDeathScaler.LOGGER.warn("Resetting delay to 0");
                initialDelay = 0;
                timer.schedule(task, delay * 1000L, repeatEach * 1000L);
            }
            return;
        }
        timer.schedule(task, delay * 1000L, repeatEach * 1000L);
    }

    protected static String formatSeconds(long time) {
        long hours = 0;
        if (time > 3600) {
            hours = Math.floorDiv(time, 3600);
        }
        long minutes = 0;
        if (hours != 0 || time > 60) {
            minutes = Math.floorDiv(time - hours * 3600, 60);
        }
        long seconds = (long) Math.floor(time - hours * 3600 - minutes * 60);

        StringBuilder sb = new StringBuilder();
        if (hours != 0) {
            sb.append(hours).append(" hours ");
        }
        if (minutes != 0 || hours != 0) {
            sb.append(minutes).append(" minutes ");
        }
        sb.append(seconds).append(" seconds");

        return sb.toString();
    }

    public long delay() {
        return delay(timerStart);
    }

    public long delay(long timerStart) {
        return System.currentTimeMillis() / 1000 - timerStart;
    }
}
