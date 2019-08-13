package alexander.cavendish.textureviewplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private TextureView textureView;
    private Surface surface;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textureView = findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                surface = new Surface(surfaceTexture);
                checkPermission();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                surface = null;
                if (mMediaPlayer != null) {
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                }
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });
    }

    public void play(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                Toast.makeText(MainActivity.this, "文件路径错误", Toast.LENGTH_SHORT).show();
            }
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(file.getAbsolutePath());
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { //尺寸变化回调
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    changeVideoSize();
                }
            });
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //  用户彻底拒绝授予权限
            } else {
                //  用户未彻底拒绝授予权限
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/testvideo.mp4";
            if (new File(path).exists()) {
                play(path);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED) {
                    final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/testvideo.mp4";
                    if (new File(path).exists()) {
                        play(path);
                    }
                } else {
                    // 申请失败
                    Toast.makeText(this, "没法访问文件", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        Log.d("MainActivity", "onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        changeVideoSize();
        super.onConfigurationChanged(newConfig);
    }

    public void changeVideoSize() {
        float videoWidth = mMediaPlayer.getVideoWidth();
        float videoHeight = mMediaPlayer.getVideoHeight();
        float deviceWidth = getResources().getDisplayMetrics().widthPixels;
        float deviceHeight = getResources().getDisplayMetrics().heightPixels;
        float devicePercent = deviceHeight / deviceWidth, videoPercent = videoHeight / videoWidth;
        int height = 0, width = 0;
        if (devicePercent >= videoPercent) {
            if (videoWidth >= deviceWidth) {
                width = (int) deviceWidth;
                float v = videoWidth / deviceWidth;
                height = (int) (videoHeight / v);
            } else {
                width = (int) deviceWidth;
                float v = deviceWidth / videoWidth;
                height = (int) (videoHeight * v);
            }
        } else {
            if (videoHeight >= deviceHeight) {
                float v = videoHeight / deviceHeight;
                height = (int) deviceHeight;
                width = (int) (videoWidth / v);
            } else {
                height = (int) deviceHeight;
                float v = deviceHeight / videoHeight;
                width = (int) (videoWidth * v);
            }
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textureView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        textureView.setLayoutParams(layoutParams);
    }
}
