package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Blue extends Entity{

	GamePanel gp;
	
	public OBJ_Potion_Blue(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_consumable;
		name = "Blue Potion";
		value = 3;
		down1 = setup("/object/potion_blue", gp.tileSize, gp.tileSize);
		description = "[Blue Potion]\nHeals your life by " + value + ".";
	}
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name + "!\n"
				+ "Your mana has been recovered by "+ value + ".";
		entity.mana += value;
		gp.playSE(2);
	}
	
}
