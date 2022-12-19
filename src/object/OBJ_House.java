package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House extends Entity{

	public OBJ_House(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_extras;
		name = "House";
		down1 = setup("/object/house", gp.tileSize*4-1, gp.tileSize*4);
		collision = true;
		solidArea.x = 0;
		solidArea.y = gp.tileSize;
		solidArea.width = gp.tileSize*4-1;
		solidArea.height = gp.tileSize*3-16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
