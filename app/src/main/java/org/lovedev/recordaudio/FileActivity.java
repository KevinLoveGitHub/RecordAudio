package org.lovedev.recordaudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class FileActivity extends AppCompatActivity implements ContentDataLoadTask.OnContentDataLoadListener {

    private static final String TAG = "FileActivity";
    private ContentDataLoadTask mContentDataLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        startLoadData();
    }


    private void startLoadData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mContentDataLoadTask = new ContentDataLoadTask(this);
            mContentDataLoadTask.setmOnContentDataLoadListener(this);
            mContentDataLoadTask.execute();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onFinishLoad() {
        List<FileItem> fileItemListByType = ContentDataControl.getFileItemListByType(FileSystemType.music);
        Log.d(TAG, "onFinishLoad: " + fileItemListByType.size());
    }

}
