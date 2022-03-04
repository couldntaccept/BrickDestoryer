package breaker;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PGraphics;
import java.util.ArrayList;
import java.io.File;

public class Ball {

	public String fileName;
	public double x_vel;
	public double y_vel;
	public double x;
	public double y;
	public double width;
	public double height;

	public Ball(String fileName, double x, double y, double x_vel, double y_vel,
		double width, double height) {
		this.fileName = fileName;
		this.x = x;
		this.y = y;
		this.x_vel = x_vel;
		this.y_vel = y_vel;
		this.width = width;
		this.height = height;
	}

	public static void tick(ArrayList<Ball> balls, ArrayList<Brick> bricks,
			Paddle paddle, ArrayList<PowerUp> powerUps){
		for (Ball b : balls) {
			b.rebound(bricks, paddle, powerUps);
			b.move();
		}
		balls.removeIf( (b) -> (b.rebound(bricks, paddle, powerUps) == -1));
	}

	public int rebound(ArrayList<Brick> bricks, Paddle paddle, ArrayList<PowerUp> powerUps){
		boolean counter = false;
		for ( Brick b : bricks) {
			if (!b.broken) {
				if (this.x + this.width + this.x_vel > b.x&&
					this.x + this.x_vel < b.x + b.width &&
					this.y + this.height > b.y &&
					this.y < b.y + b.height){
					this.x_vel *= -1;
					b.destory();
					Audio.b2b();
					if (b.mysteriousBrick != null && b.hp == 0) {
						powerUps.add(new PowerUp(b.x, b.mysteriousBrick));
					}
				}
				if (this.x + this.width > b.x &&
					this.x < b.x + b.width &&
					this.y + this.height + this.y_vel > b.y &&
					this.y + this.y_vel < b.y + b.height){
					this.y_vel *= -1;
					b.destory();
					Audio.b2b();
					if (b.mysteriousBrick != null && b.hp == 0) {
						powerUps.add(new PowerUp(b.x, b.mysteriousBrick));
					}
				}
			}
		}

		if (this.x >= 520 || this.x <= 0) {
			Audio.b2b();
			this.x_vel *= -1;
		}else if (this.y <= 0) {
			Audio.b2b();
			this.y_vel *= -1;
		}else if (this.x + this.width > paddle.x &&
					this.x < paddle.x + paddle.width &&
					this.y + this.height + this.y_vel > paddle.y &&
					this.y + this.y_vel < paddle.y + paddle.height){
				Audio.b2p();
				double speed_sum = Math.abs(this.x_vel) + this.y_vel;
				if (this.x <= paddle.x + (paddle.width/2) + 2) {
					double d = (this.x - paddle.x)/(paddle.width/2);
					this.y_vel = d*speed_sum;
					this.x_vel = -(1.0-d)*speed_sum;
					this.y_vel *= -1.0;
				} else {
					double d = (this.x - paddle.x - (paddle.width/2))/(paddle.width/2);
					this.x_vel = d*speed_sum;
					this.y_vel = (1.0-d)*speed_sum;
					this.y_vel *= -1.0;
				}
		}else if (this.y > 400) {
			return -1;
		}
		if (this.y_vel == 0) {
			this.y_vel -= 0.3;
			this.x_vel += 0.3;
		}
		return 0;
	}

	public void move(){
		this.x += this.x_vel;
		this.y += this.y_vel;
	}
}

