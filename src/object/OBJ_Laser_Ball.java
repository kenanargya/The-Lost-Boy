package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Laser_Ball extends Entity{

	GamePanel gp;
	public OBJ_Laser_Ball(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_consumable;
		name = "Laser Ball";
		down1 = setup("/object/LaserBall", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nEat it and you'll be super.";
	}
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You've ate the laser ball!\nNow you have laser and fireball ability";
		gp.lavaMonAmount = true;
		gp.playSE(2);
	}
}
