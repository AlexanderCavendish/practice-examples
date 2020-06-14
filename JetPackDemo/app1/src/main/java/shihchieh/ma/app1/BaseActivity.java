package shihchieh.ma.app1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

/**
 * @ProjectName: JetPackDemo
 * @Package: shihchieh.ma.app1
 * @ClassName: BaseActivity
 * @Author: shihchieh.ma
 * @CreateDate: 2020/6/6 9:19 PM
 * @Email: shihchieh.ma@gail.com
 * @Description: Java类作用描述
 */
public  abstract class BaseActivity extends AppCompatActivity {

    protected ProjectViewModel projectViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectViewModel = ((JetPackApplication)getApplication()).getAppViewModelProvider().get(ProjectViewModel.class);
    }

    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }
}
