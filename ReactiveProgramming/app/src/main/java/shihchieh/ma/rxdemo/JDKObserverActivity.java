package shihchieh.ma.rxdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: shihchieh.ma
 * @create: 2020/6/14 6:07 PM
 * @email: shihchieh.ma@gail.com
 * @description:
 */
public class JDKObserverActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Provider provider = new Provider(new Accept());
  }

  public static class Provider extends Observable {

    public Provider(Observer observer) {
      addObserver(observer);
      for (int i = 0; i < 10; i++) {
        setChanged();
        notifyObservers(i);
      }
      deleteObserver(observer);
    }
  }

  public static class Accept implements Observer {
    public Accept() {}

    @Override
    public void update(Observable o, Object arg) {
      Log.d("Accept", "o:" + o);
      Log.d("Accept", "arg:" + arg);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
