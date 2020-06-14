package shihchieh.ma.rxdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import shihchieh.ma.rxdemo.rx.Observable;
import shihchieh.ma.rxdemo.rx.Schedulers;
import shihchieh.ma.rxdemo.rx.Subscriber;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivityTag";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG, Thread.currentThread().getName());
    // 基本功能
    /*Observable.create(
        new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            Log.d(TAG, Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
              subscriber.onNext(i);
            }
          }
        })
    .subscribe(
        new Subscriber<Integer>() {

          @Override
          public void onStart() {
            Log.d(TAG, "onStart");
          }

          @Override
          public void onError(Throwable throwable) {
            Log.d(TAG, throwable.getMessage());
          }

          @Override
          public void onNext(Integer integer) {
            Log.d(TAG, Thread.currentThread().getName());
            Log.d(TAG, "onNext:" + integer.intValue());
          }
        });*/

    // 线程切换
    Observable.create(
            new Observable.OnSubscribe<Integer>() {
              @Override
              public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(11);
                Log.d(TAG, Thread.currentThread().getName());
              }
            })
        .observeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
        .subscribe(
            new Subscriber<Integer>() {

              @Override
              public void onStart() {
                Log.d(TAG, "onStart");
              }

              @Override
              public void onError(Throwable throwable) {
                Log.d(TAG, throwable.getMessage());
              }

              @Override
              public void onNext(Integer integer) {
                Log.d(TAG, Thread.currentThread().getName());
                Log.d(TAG, "onNext:" + integer.intValue());
              }
            });
  }
}
