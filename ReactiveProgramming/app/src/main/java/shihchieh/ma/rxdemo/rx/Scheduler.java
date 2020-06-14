package shihchieh.ma.rxdemo.rx;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * @author: shihchieh.ma
 * @create: 2020/6/14 8:30 PM
 * @email: shihchieh.ma@gail.com
 * @description: 应该整个线程池工具类 懒得弄了 就这么个意思
 */
public class Scheduler {
  private WeakReference<Handler> handler;

  public Scheduler(boolean isMainThread) {
    if (isMainThread) {
      handler = new WeakReference<>(new Handler(Looper.getMainLooper()));
      Log.d("Scheduler", handler.get().getLooper().getThread().getName());
    } else {
      HandlerThread handlerThread = new HandlerThread(UUID.randomUUID().toString());
      handlerThread.start();
      Log.d("Scheduler", handlerThread.getName());
      handler = new WeakReference<>(new Handler(handlerThread.getLooper()));
    }
  }

  public Worker createWorker() {
    return new Worker(handler);
  }

  public static class Worker {
    final WeakReference<Handler> executor;

    public Worker(WeakReference<Handler> executor) {
      this.executor = executor;
    }

    public void schedule(Runnable runnable) {
      if (executor.get() != null) {
        executor.get().post(runnable);
      }
    }
  }
}
