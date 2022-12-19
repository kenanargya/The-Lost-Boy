package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Laser extends Entity{

	public OBJ_Laser(GamePanel gp) {
		super(gp);
		
		type = type_laser;
		name = "Laser";
		down1 = setup("/object/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 4;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[" + name + "]\nAn old sword.";
	}

}
