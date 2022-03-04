# Brick Destroyer


Brick Destroyer is a pixel game run on JVM, player can achieve next level games by destroy all the bricks in current level. Player will lose when all the balls are off the screen. Have fun!


### Features!
  - Audio effect on
    - When ball hit bricks/wall
    - When ball hit paddle
    - When player eat a powerup
    - When player complete current level
    - When player completer all the levels

### Things for developer to keep in mind
  - Please set the "next_level" of the JSON file of the last level of game to be "NOMORE"

### Algorithm declaration
  - Interpolation for collision between balls and paddle
  
        p1 = 7
        p2 = 3
        diff = abs(p1 - p2)
        d = 0.25 //number between 0.0 and 1.0, distance from
        new_point = p1 + (diff * d) //new point using p1 as a base

  - Collision Detection (AABB with modifications) for collsion between balls and bricks
  
        rebound(movingBox, rect):
            if (movingBox.x + movingBox.width + movingBox.speed_x > rect.x &&
                movingBox.x + movingBox.speed_x < rec.x + rect.width &&
                movingBox.y + movingBox.height > rect.y &&
                movingBox.y < rect.y + rect.height)
                movingBox.speed_x *= -1;
            if (movingBox.x + movingBox.width > rect.x &&
                movingBox.x < rec.x + rect.width &&
                movingBox.y + movingBox.height + movingBox.speed_y > rect.y &&
                movingBox.y + movingBox.speed_y < rect.y + rect.height)
                movingBox.speed_y *= -1;

