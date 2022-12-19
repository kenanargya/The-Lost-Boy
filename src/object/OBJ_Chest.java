package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{

	public OBJ_Chest(GamePanel gp) {

	    super(gp);
		
	    type = type_open;
		name = "Chest";
		down1 = setup("/object/chest", gp.tileSize, gp.tileSize);
		collision = true;
	}
}
