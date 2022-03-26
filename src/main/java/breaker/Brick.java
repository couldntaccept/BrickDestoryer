package breaker;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PGraphics;

import java.util.ArrayList;


public class Brick{

	public String id;
	public double x;
	public double y;
	public double width = 20;
	public double height = 10;
	public int hp;
	public boolean broken = false;
	public String mysteriousBrick;

	public Brick(String id, double x, double y, String mysteriousBrick, int hp){
		this.mysteriousBrick = mysteriousBrick;
		this.id = id;
		this.x = x;
		this.y = y;
		this.hp = hp;
	}

	public static boolean tick(ArrayList<Brick> bricks){
		for (Brick b : bricks) {
			if (!b.broken) { return true; }
		}
		return false;
	}

	public double getX() {return this.x;}
	public double getY() {return this.y;}

	public double getCenterX() {return this.width;}
	public double getCenterY() {return this.height;}

	public void destory() {
		this.hp --;
		if (this.hp == 0) { this.broken = true; }
	}
}

