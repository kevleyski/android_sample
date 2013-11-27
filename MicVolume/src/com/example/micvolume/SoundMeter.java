package com.example.micvolume;

import android.media.MediaRecorder;
import android.util.Log;

public class SoundMeter {
	private MediaRecorder mRecorder = null;

    public void start() {
            if (mRecorder == null) {
                    mRecorder = new MediaRecorder();
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); // AMR_NB
                    mRecorder.setOutputFile("/dev/null"); 

                    try {
        				this.mRecorder.prepare();
        			} catch (Exception e) {
        				e.printStackTrace();
        				Log.e("MyApp","ERROR : SoundMeter class");
        			}
 
                    mRecorder.start();
                    
                    /*
            		this._mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            		this._mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            		this._mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            		this._mediarecorder.setOutputFile(this._path.getAbsolutePath());
            		*/
            }
    }

    public void stop() {
            if (mRecorder != null) {
                    mRecorder.stop();       
                    mRecorder.release();
                    mRecorder = null;
            }
    }

    public double getAmplitude() {
            if (mRecorder != null)
                    return  mRecorder.getMaxAmplitude();
            else
                    return 0;

    }
}
