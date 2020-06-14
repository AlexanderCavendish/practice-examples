package shihchieh.ma.rxdemo.rx;

import android.text.TextUtils;

import org.w3c.dom.Text;

/**
 * @author: shihchieh.ma
 * @create: 2020/6/14 5:06 PM
 * @email: shihchieh.ma@gail.com
 * @description: 订阅源
 */
public class Observable<T> {

  public interface OnSubscribe<T> {
    void call(Subscriber<? super T> subscriber);
  }

  final OnSubscribe<T> onSubscribe;

  private Observable(OnSubscribe<T> onSubscribe) {
    this.onSubscribe = onSubscribe;
  }

  public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
    return new Observable<T>(onSubscribe);
  }

  public Observable<T> observeOn(final Scheduler scheduler) {
    return Observable.create(
        new OnSubscribe<T>() {
          @Override
          public void call(final Subscriber<? super T> subscriber) {
            subscriber.onStart();
            final Scheduler.Worker worker = scheduler.createWorker();
            Observable.this.onSubscribe.call(
                new Subscriber<T>() {
                  @Override
                  public void onStart() {
                    worker.schedule(
                        new Runnable() {
                          @Override
                          public void run() {
                            subscriber.onStart();
                          }
                        });
                  }

                  @Override
                  public void onNext(T t) {
                    worker.schedule(
                        new Runnable() {
                          @Override
                          public void run() {
                            subscriber.onStart();
                          }
                        });
                  }

                  @Override
                  public void onError(Throwable throwable) {
                    worker.schedule(
                        new Runnable() {
                          @Override
                          public void run() {
                            subscriber.onStart();
                          }
                        });
                  }
                });
          }
        });
  }

  public Observable<T> subscribeOn(final Scheduler scheduler) {
    return Observable.create(
        new OnSubscribe<T>() {
          @Override
          public void call(final Subscriber<? super T> subscriber) {
            subscriber.onStart();
            // 将事件生产切换到新的线程
            scheduler
                .createWorker()
                .schedule(
                    new Runnable() {
                      @Override
                      public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                      }
                    });
          }
        });
  }

  public void subscribe(Subscriber<? super T> subscriber) {
    try {
      subscriber.onStart();
      onSubscribe.call(subscriber);
    } catch (Throwable e) {
      if (!TextUtils.isEmpty(e.getMessage())) {
        subscriber.onError(e);
      } else {
        subscriber.onError(new Throwable("subscribe unknown error"));
      }
    }
  }

  public void map() {}
}
