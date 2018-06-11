package org.lovedev.recordaudio;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PATH_NAME
            = Environment.getExternalStorageDirectory().getPath() + "/recorded_audio" + System.currentTimeMillis() + ".m4a";
    private static final String PLAY_FILE_PATH
            = Environment.getExternalStorageDirectory().getPath() + "/123.flac";
    private MediaRecorder mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View view) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(PATH_NAME);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                Log.d(TAG, "onInfo: " + extra);
            }
        });

        mRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
            @Override
            public void onError(MediaRecorder mr, int what, int extra) {
                Log.d(TAG, "onError: " + extra);
            }
        });
        mRecorder.start();
    }

    public void stop(View view) {
        mRecorder.stop();
        scanFileAsync(this, PATH_NAME);
        releaseMediaRecorder();
    }

    public void play(View view) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            File file = new File(PLAY_FILE_PATH);
            FileInputStream fis = new FileInputStream(file);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拍摄好的视频没办法及时更新到视频库, 手动更新一下
     *
     * @param filePath 文件路径
     */
    public void scanFileAsync(Context context, String filePath) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(scanIntent);
    }

    private void releaseMediaRecorder() {
        if (mRecorder != null) {
            // clear recorder configuration
            mRecorder.reset();
            // release the recorder object
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void navToFile(View view) {
        Intent intent = new Intent(this, FileActivity.class);
        startActivity(intent);
    }
}
