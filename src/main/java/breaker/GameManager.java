package breaker;

import java.util.ArrayList;

public class GameManager {
	int level = 0;

	ArrayList<ArrayList<Brick>> brick_levels;
	ArrayList<Brick> bricks;
	ArrayList<Ball> defaultBalls;
	ArrayList<Ball> balls;
	ArrayList<PowerUp> powerUps;
	Paddle defaultPaddle;
	Paddle paddle;

	int timer = 0;
	String stage = "ON";

	public GameManager(ArrayList<Ball> balls, ArrayList<ArrayList<Brick>> brick_levels,
			Paddle paddle, ArrayList<PowerUp> powerUps){
			this.defaultBalls = balls;
			this.balls = balls;
			this.brick_levels = brick_levels;
			this.defaultPaddle = paddle;
			this.paddle = paddle;
			this.powerUps = powerUps;
			}

	public void tick() {

		if (timer == 0) {stage = "ON";}
		if (stage.equals("ON")) {
			bricks = brick_levels.get(level);
			Ball.tick(balls, bricks, paddle, powerUps);
			Paddle.tick(this.paddle);
			PowerUp.tick(powerUps, paddle, balls);
			Brick.tick(brick_levels.get(level));
			boolean counter = Brick.tick(brick_levels.get(level));
			if ( !counter && level < brick_levels.size()) {
				level += 1;
				timer += 120;
				stage = "NEXTLEVEL";
				paddle = defaultPaddle;
				powerUps = new ArrayList<PowerUp>();
				balls = defaultBalls;
				Audio.levelComplete();
			}
			if (level == brick_levels.size()) {
				timer += 120;
				stage = "WIN";
				paddle = defaultPaddle;
				powerUps = new ArrayList<PowerUp>();
				balls = defaultBalls;
				level = 0;
				Audio.win();
			}
			if (balls.size() == 0) {
				timer += 120;
				stage = "LOST";
				paddle = defaultPaddle;
				powerUps = new ArrayList<PowerUp>();
				balls = defaultBalls;
				level = 0;
				Audio.lost();
			}
		} else if (stage.equals("NEXTLEVEL")) {
			timer --;
		} else if (stage.equals("WIN") || stage.equals("LOST")) {
			timer --;
		}
	}
}

