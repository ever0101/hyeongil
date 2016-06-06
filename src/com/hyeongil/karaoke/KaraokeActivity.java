package com.hyeongil.karaoke;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class KaraokeActivity extends Activity implements SensorEventListener {
    /** Called when the activity is first created. */
	
	SoundPool sp;
	int song;
	private MediaPlayer mp;
	int count=0;
	
	private SensorManager mSensorManager;  
	private Sensor mSensor;  
	//ImageView iv;  
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*
        sp=new SoundPool(4,AudioManager.STREAM_MUSIC,100);
        song=sp.load(this,R.raw.konan,1);
        
        Button b1=(Button)findViewById(R.id.button1);
        b1.setText("Play");
        
        b1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sp.play(song, 1, 1, 0, 0, 1);
			}
		});
		*/
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor= mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //iv=(ImageView)findViewById(R.id.)
        
        mp=MediaPlayer.create(getBaseContext(), R.raw.konan);
        
        Button b1=(Button)findViewById(R.id.button1);
        Button b2=(Button)findViewById(R.id.button2);
        b1.setText("Play");
        b2.setText("Stop");
        b1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mp.start();
			}
		});
        b2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mp.pause();
			}
		});
    }

    
    protected void onResume() {  
    	super.onResume();  
    	mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {  
    	super.onPause(); 
    	mSensorManager.unregisterListener(this);
    }  
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    	
    }
    public void onSensorChanged(SensorEvent event){
    	if(event.values[0]==0){
    		if(count==0){
    			mp.pause();
    		}
    		else{
    			mp.start();
    		}
    		count++;
    		count=count%2;
    	}
    	
    		
    }
    
}