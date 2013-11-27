package com.example.micvolume;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String TAG = "MyApp"; 
	
	private Button btnStop, btnStart;
	private TextView textViewVolume;
	private TextView texViewWork1;
	
	private int _hit = 0;
	
	Timer _timer = null;
	Handler _timerHandle = new Handler();
	
	SoundMeter _soundMeter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStart = (Button)findViewById(R.id.buttonStartRec);
		btnStop = (Button)findViewById(R.id.buttonStopRec);
		textViewVolume = (TextView)findViewById(R.id.textViewVolume);
		texViewWork1 = (TextView)findViewById(R.id.textVieValue);
		
		_soundMeter = new SoundMeter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void btnStrat_onCLick(View view) {
		// タイマ開始
		_soundMeter.start();
		
		if (_timer != null) {
			_timer.cancel();
			_timer = null;
		}
		_timer = new Timer();
		_timer.schedule(new timerFunc(), 100, 100); // ミリ秒でセット

	}

	public void btnStop_onCLick(View view) {
		// タイマ停止
		if (_timer != null) {
			_timer.cancel();
			_timer = null;
		}
		_soundMeter.stop();
	}
	
	// ------------------------------------------------------------------------
	// インナークラスにしたけど違和感あるな～
	// クラスの中にクラスってありなんだな～といっても匿名メソッドもな~
	// ------------------------------------------------------------------------
	class timerFunc extends TimerTask {
		private int num = 0;
		@Override
		public void run() {
			_timerHandle.post(new Runnable() {
				@Override
				public void run() {
					num++;
					// textViewVolume.setText(String.valueOf(num));
					double ratio = _soundMeter.getAmplitude();
					textViewVolume.setText(String.valueOf(ratio));
					
					// 大きな声の判断を32000.0以上にしているが、この値は環境によって調整だね
					if (ratio > 32000.0) {
						_hit++;
					}
		            //Log.d(TAG,String.valueOf(ratio));
		            texViewWork1.setText(String.valueOf(_hit));
				}
			});
		}
	}
}
