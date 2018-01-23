package org.lovedev.recordaudio;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PATH_NAME = Environment.getExternalStorageDirectory().getPath() + "/recorded_audio.aac";
    private MediaRecorder mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View view) {
//        File file = new File(PATH_NAME);
//        if (file.exists()) {
//            file.delete();
//        }
//
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
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
        releaseMediaRecorder();
    }

    private void releaseMediaRecorder(){
        if (mRecorder != null) {
            // clear recorder configuration
            mRecorder.reset();
            // release the recorder object
            mRecorder.release();
            mRecorder = null;
        }
    }

}
