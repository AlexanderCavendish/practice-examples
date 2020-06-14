package shihchieh.ma.app1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @ProjectName: JetPackDemo
 * @Package: shihchieh.ma.app1
 * @ClassName: ProjectViewModel
 * @Author: shihchieh.ma
 * @CreateDate: 2020/6/6 9:20 PM
 * @Email: shihchieh.ma@gail.com
 * @Description: Java类作用描述
 */
public class ProjectViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isActive; // App 是否存活
    private final MutableLiveData<Boolean> isLogin; // App 是否登录

    public MutableLiveData<Boolean> getIsActive() {
        return isActive;
    }

    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    {
        isActive = new MutableLiveData<>();
        isActive.setValue(false);

        isLogin = new MutableLiveData<>();
        isLogin.setValue(false);
    }

}

