package eli.com.applicastertask.manager;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import eli.com.applicastertask.interfaces.TickerStrategy;
import eli.com.applicastertask.model.events.TimeToRefreshEvent;

/**
 * Created by eli on 23/12/16.
 */

public class ExecutorServiceTickerStrategy implements TickerStrategy {

    private ScheduledExecutorService scheduler;
    private boolean isStarted;


    @Override
    public void start(long startDelay, long interval, TimeUnit units) {
        if (!isStarted) {
            isStarted = true;
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    EventBus.getDefault().post(new TimeToRefreshEvent());
                }
            }, startDelay, interval, units);
        }
    }


    @Override
    public void stop() {
        if (isStarted) {
            isStarted = false;
            scheduler.shutdownNow();
        }
    }


}
