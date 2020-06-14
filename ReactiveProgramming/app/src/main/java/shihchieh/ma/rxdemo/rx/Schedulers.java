package shihchieh.ma.rxdemo.rx;

import android.os.HandlerThread;
import android.os.Looper;

import java.util.UUID;
import java.util.concurrent.Executors;

/**
 * @author: shihchieh.ma
 * @create: 2020/6/14 8:31 PM
 * @email: shihchieh.ma@gail.com
 * @description:
 */
public class Schedulers {

  private Schedulers() {}

  public static Scheduler io() {
    return new Scheduler(false);
  }

  public static Scheduler main() {
    return new Scheduler(true);
  }
}
