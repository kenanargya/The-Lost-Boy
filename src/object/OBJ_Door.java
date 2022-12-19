package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{

	public OBJ_Door(GamePanel gp) {

	    super(gp);
		
	    type = type_open;
		name = "Door";
		down1 = setup("/object/door", gp.tileSize*2, gp.tileSize*2);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 96;
		solidArea.height = 96;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
