package breaker;

import processing.core.PApplet;

public class Paddle{
	public boolean leftMove = false;
	public boolean rightMove = false;
	public double x = 240.0;
	public double y = 390.0;
	public double width = 40.0;
	public double height = 10.0;
	public int timer = 0;
	public boolean bigPaddle = false;

	public Paddle(){}

	public static void tick(Paddle paddle) {
		if (paddle.leftMove) {
			paddle.moveL();
		}else if (paddle.rightMove) {
			paddle.moveR();

		}
	}

	public void rightMove(){
		rightMove = true;
		leftMove = false;

	}
	public void leftMove(){
		leftMove = true;
		rightMove = false;

	}
	public void keepStill(){
		rightMove = false;
		leftMove = false;
	}

	public void moveR(){
		this.x += 4;
		if (this.x > (520 - this.width)) {
			this.x = 520 - this.width;
		}
	}

	public void moveL() {
		this.x -= 4;
		if (this.x < 0) {
			this.x = 0;
		}
	}
}

