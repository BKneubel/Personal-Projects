package com.game.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String,Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load(){
		try {
			
			soundMap.put("menu_sound", new Sound("res/Click.ogg"));//click
			musicMap.put("music", new Music("res/Background.ogg"));//background music
			soundMap.put("coin", new Sound("res/Coin.ogg"));
			soundMap.put("laser", new Sound("res/Laser.wav"));
			soundMap.put("shields", new Sound("res/Shields.ogg"));
			soundMap.put("heal", new Sound("res/Collect.wav"));
			soundMap.put("hit", new Sound("res/hit2.wav"));

			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
	
}
