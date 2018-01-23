package org.lovedev.recordaudio;

/**
 * Created by Branch on 16/7/31.
 */
public class FileItem {


  private String mFileId;

  private String mFilePath;

  private String mFileName;
  private long mFileDuration;

  public FileItem(String mFileId, String mFilePath, String mFileName, long fileDuration) {
    this.mFileId = mFileId;
    this.mFilePath = mFilePath;
    this.mFileName = mFileName;
    mFileDuration = fileDuration;
  }

  public FileItem(String imageId, String filePath, String fileName) {
    this.mFileId = imageId;
    this.mFilePath = filePath;
    this.mFileName = fileName;
  }

  public String getmFileId() {
    return mFileId;
  }

  public void setmFileId(String mFileId) {
    this.mFileId = mFileId;
  }

  public String getmFilePath() {
    return mFilePath;
  }

  public void setmFilePath(String mFilePath) {
    this.mFilePath = mFilePath;
  }

  public String getmFileName() {
    return mFileName;
  }

  public void setmFileName(String mFileName) {
    this.mFileName = mFileName;
  }

  public long getFileDuration() {
    return mFileDuration;
  }

  public void setFileDuration(long fileDuration) {
    mFileDuration = fileDuration;
  }
}
