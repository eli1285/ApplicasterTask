package eli.com.applicastertask.interfaces;

import java.util.concurrent.TimeUnit;

/**
 * Created by eli on 23/12/16.
 */

public interface TickerStrategy {
    void start(long delay, long interval, TimeUnit timeUnit);
    void stop();
}
