
package breaker;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class AppTest {
    App classUnderTest = new App();

    @Test
    public void testCollisionForBricksAndBalls() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, null, 1));
        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -1.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        for (int i = 0; i < 195 ; i ++ ) {
            classUnderTest.gm.tick();
        }
        assert classUnderTest.gm.balls.get(0).y_vel == 1 : "x velocity of ball get wrong :(";
        assert classUnderTest.gm.balls.get(0).x_vel == 0 : "y velocity of ball get wrong :(";

    }

    @Test
    public void testHitBricks() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, null, 1));
        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -1.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        for (int i = 0; i < 195 ; i ++ ) {
            classUnderTest.gm.tick();
        }
        assert classUnderTest.gm.bricks.get(0).broken == true : "the only brick should be broken after hit :(";
    }

    @Test
    public void testCollisionForPaddleAndBalls() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, null, 1));
        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -1.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        for (int i = 0; i < 195; i ++ ) {
            classUnderTest.gm.tick();
        }
        assert classUnderTest.gm.balls.get(0).y_vel == 1 : "y velocity of ball get wrong :(";
    }

    @Test
    public void testPaddleMovement() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, null, 1));
        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -3.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        for (int i = 0; i < 5 ; i ++ ) {
            classUnderTest.paddle.moveR();
        }
        assert classUnderTest.gm.paddle.x == 260 : "paddle moved wrongly :(";

    }
    @Test
    public void testPaddleMovementRestriction() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, null, 1));
        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -3.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        double width = classUnderTest.gm.paddle.width;
        for (int i = 0; i < 1000 ; i ++ ) {
            classUnderTest.paddle.moveL();
        }
        classUnderTest.gm.tick();
        assert classUnderTest.gm.paddle.x == 0 : "paddle move off the screen :(";
        for (int i = 0; i < 1000; i ++ ) {
            classUnderTest.paddle.moveR();
        }
        classUnderTest.gm.tick();
        assert classUnderTest.gm.paddle.x == 520.0 - width : "paddle move off the screen :(";

    }
    @Test
    public void testPowerUpsMultiball() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, "paddleup", 1));
        bricks.add(new Brick("Sample", 150, 0, "paddleup", 1));

        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -1.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        for (int i = 0; i < 190; i ++ ) {
            classUnderTest.gm.tick();
        }
        assert classUnderTest.gm.powerUps.size() == 1 : "number of powerups get wrong :(";
    }
    @Test
    public void testPowerUpsCollision() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick("Sample", 250, 0, "paddleup", 1));
        bricks.add(new Brick("Sample", 150, 0, "paddleup", 1));
        classUnderTest.brick_levels.add( bricks );
        classUnderTest.balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -1.0, 5.0, 5.0));
        classUnderTest.gm = new GameManager(classUnderTest.balls, classUnderTest.brick_levels, classUnderTest.paddle, classUnderTest.powerUps);
        for (int i = 0; i < 590; i ++ ) {
            classUnderTest.gm.tick();
        }
        assert classUnderTest.gm.paddle.width == 50 : "number of powerups get wrong :(";
    }

}

