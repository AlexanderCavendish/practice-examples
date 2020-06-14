package shihchieh.ma.rxdemo.rx;

import androidx.lifecycle.Observer;

/**
 * @author: shihchieh.ma
 * @create: 2020/6/14 4:47 PM
 * @email: shihchieh.ma@gail.com
 * @description: 观察者
 */
public interface Subscriber<T> {
  void onStart();

  void onNext(T t);

  void onError(Throwable throwable);
}
