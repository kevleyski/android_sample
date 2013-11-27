package com.example.audiorec;

import java.io.File;

// import android.R; <- なんでこれが追加されたんだろう？
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnStop, btnStart;
	private MediaRecorder _mediarecorder;
	
	private File _path;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStart = (Button)findViewById(R.id.buttonStartRec);
		btnStop = (Button)findViewById(R.id.buttonStopRec);
		
		this._mediarecorder = new MediaRecorder();
		try {
			this._path = new File(Environment.getExternalStorageDirectory(), "myRecording.3gp");
			// this._path = new File(getFilesDir(), "myRecording.3gp");
			Log.d("MyApp",this._path.getAbsolutePath());
			
			// Nexus7 
			// getFilesDir()
			// "/data/data/com.example.audiorec/files/myRecording.3gp"
			
			// Nexus7
			// Environment.getExternalStorageDirectory()
			// "/storage/emulated/0/myRecording.3gp"
		} catch (Exception e) {
			// Nexus7だとSDないのでトップに保存されたかも
			Log.e("MyApp", "SDないじゃん");
		}
		
		resetRecorder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this._mediarecorder.release();
	}
	
	public void resetRecorder() {
		this._mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		this._mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		this._mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		this._mediarecorder.setOutputFile(this._path.getAbsolutePath());
	}
	
	public void btnStrat_onCLick(View view) {
		try {
			try {
				this._mediarecorder.prepare();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this._mediarecorder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnStop_onCLick(View view) {
		try {
			this._mediarecorder.stop();
			resetRecorder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
