package shihchieh.ma.app1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * @ProjectName: JetPackDemo
 * @Package: shihchieh.ma.app1
 * @ClassName: BaseFragment
 * @Author: shihchieh.ma
 * @CreateDate: 2020/6/6 9:50 PM
 * @Email: shihchieh.ma@gail.com
 * @Description: Java类作用描述
 */
public abstract class BaseFragment extends Fragment {
    protected AppCompatActivity mActivity;
    public ProjectViewModel projectViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectViewModel = ((JetPackApplication) mActivity.getApplication()).getAppViewModelProvider().get(ProjectViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

}
