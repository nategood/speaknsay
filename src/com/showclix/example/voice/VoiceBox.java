package com.showclix.example.voice;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class VoiceBox {
		
	private  SoundPool mSoundPool; 
	private  HashMap<Character, Integer> mSoundPoolMap; 
	private  AudioManager  mAudioManager;
	private  Context mContext;
	
	
	public VoiceBox() {
	}
		
	public void initSounds(Context theContext) { 
		 mContext = theContext;
	     mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0); 
	     mSoundPoolMap = new HashMap<Character, Integer>(); 
	     mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);

	     addSound('a', R.raw.a);
	     addSound('b', R.raw.b);
	     addSound('c', R.raw.c);
	     addSound('d', R.raw.d);
	     addSound('e', R.raw.e);
	     addSound('f', R.raw.f);
	     addSound('g', R.raw.g);
	     addSound('h', R.raw.h);
	     addSound('i', R.raw.i);
	     addSound('j', R.raw.j);
	     addSound('k', R.raw.k);
	     addSound('l', R.raw.l);
	     addSound('m', R.raw.m);
	     addSound('n', R.raw.n);
	     addSound('o', R.raw.o);
	     addSound('p', R.raw.p);
	     addSound('q', R.raw.q);
	     addSound('r', R.raw.r);
	     addSound('s', R.raw.s);
	     addSound('t', R.raw.t);
	     addSound('u', R.raw.u);
	     addSound('v', R.raw.v);
	     addSound('w', R.raw.w);
	     addSound('x', R.raw.x);
	     addSound('y', R.raw.y);
	     addSound('z', R.raw.z);
	     
	     addSound('-', R.raw.play);
	}
	
	public void addSound(Character letter, int SoundID) {
		mSoundPoolMap.put(letter, mSoundPool.load(mContext, SoundID, 1));
	}
	
	public void playSound(Character letter) { 
		if (!mSoundPoolMap.containsKey(letter))
			return;
	     int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	     mSoundPool.play(mSoundPoolMap.get(letter), streamVolume, streamVolume, 1, 0, 1f); 
	}
	
	public void playPlay() {
		playSound('-');
	}
	
//	public void playLoopedSound(int index) { 
//	     int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
//	     mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f); 
//	}
	
}