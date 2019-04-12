package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	public void clearEnemies(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player && Menu.players == false){
				object.clear();
				if(Game.gameState != Game.STATE.End){
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player,this));
				}
			}
			if(tempObject.getId() == ID.Player && Menu.players == true){
				object.clear();
				if(Game.gameState != Game.STATE.End){
					addObject(new Player(200,200, ID.Player,this));
					addObject(new Player2(500, 200, ID.Player2,this));
					
				}
			}
		}
	}
}
