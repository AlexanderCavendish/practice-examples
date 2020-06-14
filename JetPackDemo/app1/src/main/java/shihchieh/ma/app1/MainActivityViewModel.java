package shihchieh.ma.app1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @ProjectName: JetPackDemo
 * @Package: shihchieh.ma.app1
 * @ClassName: MainActivityViewModel
 * @Author: shihchieh.ma
 * @CreateDate: 2020/6/6 9:53 PM
 * @Email: shihchieh.ma@gail.com
 * @Description: Java类作用描述
 */
public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Boolean> isActive;

    public MutableLiveData<Boolean> getIsActive() {
        if (isActive == null) {
            isActive = new MutableLiveData<>();
            isActive.setValue(false);
        }
        return isActive;
    }
}
