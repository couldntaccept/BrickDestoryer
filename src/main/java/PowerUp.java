package breaker;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUp {

	public String id;
	public Brick brick;
	public double width = 16;
	public double height = 16;
	public double x;
	public double y = 0.0;
	public double y_vel = 1.0;

	public static void tick(ArrayList<PowerUp> powerUps, Paddle paddle, ArrayList<Ball> balls){
		for (PowerUp p : powerUps) {
			p.move();
			p.collide(paddle, balls);
			if (paddle.timer == 0 && paddle.bigPaddle) {
				paddle.width -= 10.0;
			}
			if (paddle.timer == 0) {
				paddle.bigPaddle = false;
			} else if (paddle.bigPaddle && paddle.timer != 0) {
				paddle.timer --;
			}
		}
		powerUps.removeIf( (p) -> (p.y > (paddle.y - paddle.height)));
	}

	public PowerUp(double x, String id) {
		this.x = x;
		this.id = id;
	}


	public void move() {
		this.y += this.y_vel;
	}

	public void collide(Paddle paddle, ArrayList<Ball> balls) {
		if (this.y == paddle.y - this.height && this.x >= paddle.x && this.x <= paddle.x + paddle.width) {
			if (this.id.equals("paddleup") && paddle.bigPaddle == false) {
				paddle.timer += 3600;
				paddle.width += 10.0;
				paddle.bigPaddle = true;
				Audio.powerup();
			} else if (this.id.equals("multiball")) {
				balls.add(new Ball("ball.png", 240.0, 195.0, 1.0, 2.0, 5.0, 5.0));
				balls.add(new Ball("ball.png", 240.0, 195.0, 1.0, -3.0, 5.0, 5.0));
				Audio.powerup();
			}
		}
	}
}

