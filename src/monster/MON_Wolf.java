package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Key;
import object.OBJ_Rock;
import object.OBJ_Winter_Ball;

public class MON_Wolf extends Entity{

	GamePanel gp;
	
	public MON_Wolf(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Boss Wolf";
		speed = 3;
		maxLife = 20;
		life = maxLife;
		attack = 3;
		defense = 0;
//		exp = 2;
		projectile = new OBJ_Winter_Ball(gp);
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	    
		getImage();
	}
    public void getImage() {
    	up1 = setup("/monster/rabbitmonster_up1", gp.tileSize, gp.tileSize);
    	up2 = setup("/monster/rabbitmonster_up2", gp.tileSize, gp.tileSize);
    	down1 = setup("/monster/rabbitmonster_down1", gp.tileSize, gp.tileSize);
    	down2 = setup("/monster/rabbitmonster_down2", gp.tileSize, gp.tileSize);
    	left1 = setup("/monster/rabbitmonster_left1", gp.tileSize, gp.tileSize);
    	left2 = setup("/monster/rabbitmonster_left2", gp.tileSize, gp.tileSize);
    	right1 = setup("/monster/rabbitmonster_right1", gp.tileSize, gp.tileSize);
    	right2 = setup("/monster/rabbitmonster_right2", gp.tileSize, gp.tileSize);
    	
    }
    public void setAction() {
    	
    	actionLockCounter++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick up a number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
		else if(Math.abs(gp.player.worldX - worldX) <= 120 && Math.abs(gp.player.worldY - worldY) <= 120 && actionLockCounter>7) {
			if(Math.abs(gp.player.worldX - worldX) <= 16) {
				if(gp.player.worldY > worldY) direction = "down";
				else if(gp.player.worldY < worldY) direction = "up";
			}
			else if(Math.abs(gp.player.worldY - worldY) <= 16) {
				if(gp.player.worldX > worldX) direction = "right";
				else if(gp.player.worldX < worldX) direction = "left";
			}
			actionLockCounter = 0;
		}
		
		int i = new Random().nextInt(100)+1;
		if(i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
			projectile.set(worldX,  worldY,  direction, true,  this);
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
		}
    }
    public void damageReaction() {
    	
    	actionLockCounter = 0;
    	direction = gp.player.direction;
    }
    public void checkDrop() {
    	
    	// CAST A DIE
    	dropItem(new OBJ_Key(gp));
    }
}
