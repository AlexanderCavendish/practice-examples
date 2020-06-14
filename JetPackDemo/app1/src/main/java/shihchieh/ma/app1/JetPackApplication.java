package shihchieh.ma.app1;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * @ProjectName: JetPackDemo
 * @Package: shihchieh.ma.app1
 * @ClassName: JetPackApplication
 * @Author: shihchieh.ma
 * @CreateDate: 2020/6/6 9:15 PM
 * @Email: shihchieh.ma@gail.com
 * @Description: Java类作用描述
 */
public class JetPackApplication extends Application implements ViewModelStoreOwner {

    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        // 实例化 ViewModelStore   一次
        mAppViewModelStore = new ViewModelStore();
        mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public ViewModelProvider getAppViewModelProvider() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, mFactory);
        Log.d("JetPackApplication", viewModelProvider.toString());
        return viewModelProvider;
    }


}
