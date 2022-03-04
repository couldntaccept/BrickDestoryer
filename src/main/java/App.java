
package breaker;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PGraphics;
import processing.core.PFont;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.lang.Math;
import java.util.ArrayList;


public class App extends PApplet {
    PImage brick_red, brick_pink, brick_green, brick_blue, ballImg, paddleImg,
            ball_powerup, paddle_powerup, bgImg;
    ArrayList<ArrayList<Brick>> brick_levels = new ArrayList<ArrayList<Brick>>();
    Paddle paddle = new Paddle();
    ArrayList<Ball> balls = new ArrayList<Ball>();
    ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    PFont f;
    GameManager gm;

    public App() {
    }

    public void settings() {
        size(520, 400);
    }

    public void setup() {
        frameRate(60);
        balls.add(new Ball("ball.png", 260.0, 195.0, 0.0, -3.0, 5.0, 5.0));
        f = createFont("PressStart2P-Regular.ttf", 24);
        textFont(f);
        textAlign(CENTER, CENTER);

        bgImg = loadImage("bg.png");
        ballImg = loadImage("ball.png");
        brick_red = loadImage("brick_red.png");
        brick_blue = loadImage("brick_blue.png");
        brick_pink = loadImage("brick_pink.png");
        brick_green = loadImage("brick_green.png");
        paddleImg = loadImage("paddle.png");
        ball_powerup = loadImage("ball_powerup.png");
        paddle_powerup = loadImage("paddle_powerup.png");

        JSONParser parser = new JSONParser();
        String fileName = "Level1.json";
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        while (true) {
            if (fileName.equals("NOMORE.json")) { break;}
            bricks = new ArrayList<Brick>();
            try (Reader reader = new FileReader(fileName)) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray bri = (JSONArray) jsonObject.get("bricks");
                fileName = (String) (jsonObject.get("next_level")+".json");
                for (Object elem : bri) {
                    double x = (double)(long)((JSONObject) elem).get("x");
                    double y = (double)(long)((JSONObject) elem).get("y");
                    String powerup = (String)((JSONObject) elem).get("powerup");
                    String hp = (String)((JSONObject) elem).get("hp");
                    if (hp != null) {
                        int Hp = Integer.parseInt(hp);
                        Brick b = new Brick((String)((JSONObject) elem).get("id"), x, y, powerup, Hp);
                        bricks.add(b);
                    } else {
                        Brick b = new Brick((String)((JSONObject) elem).get("id"), x, y, powerup, 1);
                        bricks.add(b);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            brick_levels.add(bricks);
        }
        gm = new GameManager(balls, brick_levels, paddle, powerUps);
    }

    public void draw() {
        background(0);
        image(bgImg, 0, 0);
        gm.tick();
        if (gm.stage.equals("WIN")) {
            text("YOU WIN !", 270, 190);

        }else if (gm.stage.equals("LOST")) {
            text("GAME OVER", 260, 190);

        }else if (gm.stage.equals("NEXTLEVEL")) {
            text("NEXT LEVEL", 255, 190);

        }
        for (Brick b : gm.bricks) {
            if (!b.broken) {
                if (b.id.equals("brick_red") ){ image(brick_red, (int)b.x, (int)b.y); }
                else if (b.id.equals("brick_blue") ){ image(brick_blue, (int)b.x, (int)b.y); }
                else if (b.id.equals("brick_green") ){ image(brick_green, (int)b.x, (int)b.y); }
                else if (b.id.equals("brick_pink") ){ image(brick_pink, (int)b.x, (int)b.y); }
            }
        }

        image(paddleImg,(int)gm.paddle.x,(int)gm.paddle.y, (int)gm.paddle.width, (int)gm.paddle.height);

        for (Ball b : gm.balls) {
            int result = b.rebound(gm.bricks, gm.paddle, gm.powerUps);
            image(ballImg,(int)b.x,(int)b.y);
        }

        for (PowerUp p : gm.powerUps) {
            if (p.id.equals("paddleup")) {
                image(paddle_powerup, (int)p.x,(int) p.y);
            }else {
                image(ball_powerup,(int) p.x, (int)p.y);
            }
        }
    }

    public void keyPressed(){
        if (keyCode == RIGHT ) { gm.paddle.rightMove();}
        else if (keyCode == LEFT ) { gm.paddle.leftMove();}
        else { gm.timer = 0;}
    }

    public void keyReleased(){ gm.paddle.keepStill();}

    public static void main(String[] args) {
        PApplet.main("breaker.App");
    }
}

