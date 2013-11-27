package com.example.playwave;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnStop, btnStart;
	private MediaPlayer _mediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStart = (Button)findViewById(R.id.buttonStart);
		btnStop = (Button)findViewById(R.id.buttonStop);
		
		try {
			_mediaPlayer = MediaPlayer.create(this, R.raw.komo_click_01);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (_mediaPlayer != null) {
			_mediaPlayer.release();
		}
	}
	
	public void btnStrat_onCLick(View view) {
		try {
			_mediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnStop_onCLick(View view) {
		// 今回はクリック音を想定しているのでSTOPは必要無し
		/*
		try {
		　　　 // どういう組み合わせがいいのか不明
			//　_mediaPlayer.prepare();
			//　_mediaPlayer.stop();
			//　_mediaPlayer.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
