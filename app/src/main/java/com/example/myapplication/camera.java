package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.VideoResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class camera extends AppCompatActivity {
    CameraView camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);
        initCamera();

        findViewById(R.id.startRecord).setOnClickListener(view -> {
                    File mediaStorageDir = getExternalFilesDir("videos");

                    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                        Log.e("JOGO", "failed to create directory");
                        return;
                    }
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                    File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                            timeStamp + "433_test.mp4");
                    camera.takeVideoSnapshot(mediaFile.getAbsoluteFile(), 5000);
                }
        );
        initLottie();

        CameraLogger.setLogLevel(CameraLogger.LEVEL_VERBOSE);


    }


    public void initCamera() {
        camera.addCameraListener(new CameraListener() {

            @Override
            public void onPictureShutter() {
                // Picture capture started!
            }

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                // A Picture was taken!
            }

            @Override
            public void onVideoTaken(@NonNull VideoResult result) {
                // A Video was taken!
            }

            @Override
            public void onVideoRecordingStart() {
                // Notifies that the actual video recording has started.
                // Can be used to show some UI indicator for video recording or counting time.
            }

            @Override
            public void onVideoRecordingEnd() {
                // Notifies that the actual video recording has ended.
                // Can be used to remove UI indicators added in onVideoRecordingStart.
            }
        });

    }

    public void initLottie(){
        ((LottieAnimationView) findViewById(R.id.lottie1)).setRenderMode(RenderMode.HARDWARE);
        ((LottieAnimationView) findViewById(R.id.lottie2)).setRenderMode(RenderMode.HARDWARE);
        ((LottieAnimationView) findViewById(R.id.lottie3)).setRenderMode(RenderMode.HARDWARE);
        ((LottieAnimationView) findViewById(R.id.lottie4)).setRenderMode(RenderMode.HARDWARE);


    }
}
