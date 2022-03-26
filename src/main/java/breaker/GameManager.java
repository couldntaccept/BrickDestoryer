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
	int stage = 0;
	// stage: 0 -- ON, 1 -- WIN, 2 -- LOST, 3 -- NEXT LEVEL



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

		if (timer == 0) {stage = 0;}
		switch (stage) {
			case 0 -> {
				bricks = brick_levels.get(level);
				Ball.tick(balls, bricks, paddle, powerUps);
				Paddle.tick(this.paddle);
				PowerUp.tick(powerUps, paddle, balls);
				Brick.tick(brick_levels.get(level));
				boolean counter = Brick.tick(brick_levels.get(level));
				if (!counter && level < brick_levels.size()) {
					this.defaultProgress();
					level += 1;
					stage = 3;
					Audio.levelComplete();
				}
				if (level == brick_levels.size()) {
					this.defaultProgress();
					level = 0;
					stage = 1;
					Audio.win();
				}
				if (balls.size() == 0) {
					this.defaultProgress();
					level = 0;
					stage = 2;
					Audio.lost();
				}
			}
			case 1, 2, 3 -> timer--;
		}
	}

	public void defaultProgress(){
		timer += 120;
		paddle = defaultPaddle;
		powerUps = new ArrayList<PowerUp>();
		balls = defaultBalls;
	}
}

