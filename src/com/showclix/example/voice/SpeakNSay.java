package com.showclix.example.voice;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class SpeakNSay extends Activity {
	public static final String TAG = "VoiceExample";
	private SpeakListener speakListener;
	private VoiceBox voiceBox;
	private HashMap<Character, Integer> letterMap;
	private LinearLayout ledLayout;
	
    // Listener
    class SpeakListener implements OnClickListener {
    	public SpeakNSay activity;
        public void onClick(View v) {
        	voiceBox.playPlay();
        	activity.listen();
        }
        public SpeakListener(SpeakNSay act) {
        	activity = act;
        }
    }
    
    private void initLetters() {
    	letterMap = new HashMap<Character, Integer>();
    	
    	letterMap.put('a', R.drawable.a);
    	letterMap.put('b', R.drawable.b);
    	letterMap.put('c', R.drawable.c);
    	letterMap.put('d', R.drawable.d);
    	letterMap.put('e', R.drawable.e);
    	letterMap.put('f', R.drawable.f);
    	letterMap.put('g', R.drawable.g);
    	letterMap.put('h', R.drawable.h);
    	letterMap.put('i', R.drawable.i);
    	letterMap.put('j', R.drawable.j);
    	letterMap.put('k', R.drawable.k);
    	letterMap.put('l', R.drawable.l);
    	letterMap.put('m', R.drawable.m);
    	letterMap.put('n', R.drawable.n);
    	letterMap.put('o', R.drawable.o);
    	letterMap.put('p', R.drawable.p);
    	letterMap.put('q', R.drawable.q);
    	letterMap.put('r', R.drawable.r);
    	letterMap.put('s', R.drawable.s);
    	letterMap.put('t', R.drawable.t);
    	letterMap.put('u', R.drawable.u);
    	letterMap.put('v', R.drawable.v);
    	letterMap.put('w', R.drawable.w);
    	letterMap.put('x', R.drawable.x);
    	letterMap.put('y', R.drawable.y);
    	letterMap.put('z', R.drawable.z);
    }
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        //Log.i(TAG,android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        
        ledLayout = (LinearLayout)findViewById(R.id.leds);
        
        voiceBox = new VoiceBox(); 
        voiceBox.initSounds(getBaseContext());
        
        initLetters();
        
        speakListener = new SpeakListener(this);
        Button button = (Button)findViewById(R.id.spell_button);
        button.setOnClickListener(speakListener);
        
//        Button playButton = (Button)findViewById(R.id.play_button);
//        playButton.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				voiceBox.playPlay();
//			}
//        });
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (data == null)
    		return;
    	//for (String o : data.getExtras().keySet())
    	//android.speech.extra.RESULTS
    	for (String phrase : data.getStringArrayListExtra("android.speech.extra.RESULTS")) {
    		Log.i(TAG, phrase);
    		processInput(phrase);
    		break;
    	}
    	
    	Log.i(TAG,"Code " + requestCode);
    }
    
    public void listen () {
    	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent, 0);   
    }
    
    public void processInput(String phrase) {
    	ledLayout.removeAllViews();
		for(int i = 0 ; i < phrase.length(); i++) {
			showLetter(phrase.charAt(i));
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
			}
			voiceBox.playSound(phrase.charAt(i));
		}
    }
    
    public void showLetter(Character c) {
    	if (!letterMap.containsKey(c))
    		return;
    	//((ImageView)findViewById(R.id.letter)).setImageResource(letterMap.get(c));
    	//((View)findViewById(R.id.canvas)).invalidate();
    	ImageView iv = new ImageView(this);
    	iv.setImageResource(letterMap.get(c));
    	ledLayout.addView(iv);
    	Log.i(TAG, c + " ");
    }
    
}