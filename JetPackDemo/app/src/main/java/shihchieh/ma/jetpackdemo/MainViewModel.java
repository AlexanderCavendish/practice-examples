package shihchieh.ma.jetpackdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @ProjectName: JetPackDemo
 * @Package: shihchieh.ma.jetpackdemo
 * @ClassName: MainViewModel
 * @Author: shihchieh.Ma
 * @CreateDate: 2020/6/4 11:30 PM
 * @Email: shihchieh.ma@gail.com
 * @Description: Java类作用描述
 */
public class MainViewModel extends /* 没有环境 ViewModel*/  AndroidViewModel {

    private MutableLiveData<String> phoneInfo;

    private Context context;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public MutableLiveData<String> getPhoneInfo() {
        if (phoneInfo == null) {
            phoneInfo = new MutableLiveData<>();

            // 默认值
            phoneInfo.setValue("");
        }
        return phoneInfo;
    }

    public void appendNumber(String number) {
        phoneInfo.setValue(phoneInfo.getValue() + number);
    }

    public void backspaceNumber() {
        int length = phoneInfo.getValue().length();
        if (length > 0) {
            phoneInfo.setValue(phoneInfo.getValue().substring(0, phoneInfo.getValue().length() - 1));
        }
    }

    public void clear() {
        phoneInfo.setValue("");
    }

    public void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneInfo.getValue()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
