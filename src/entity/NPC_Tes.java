package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Tes extends Entity{
 
	//public int dialogueType = 0;
	public NPC_Tes(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 0;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
		setDialogue();
	}
    public void getImage() {
		
//		up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
//		up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
//		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
//		down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
//		left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
//		left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
//		right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
//		right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    	
    	up1 = setup("/npc/NPC Belakang", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/NPC Belakang", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/NPC Depan", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/NPC Depan", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/NPC Kiri", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/NPC Kiri", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/NPC Kanan", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/NPC Kanan", gp.tileSize, gp.tileSize);
	}
    public void setDialogue() {
    	
    	if(dialogueType == 0) {
    		dialogue[0] = "Hello, young man!\nDo you feel much better now?";
        	dialogue[1] = "I found you in the forest last night\nI thought you were dead";
        	dialogue[2] = "I know you're confused right now.\nThis happened before, it's because of the portal";
        	dialogue[3] = "You must be drank and fell into the portal";
        	dialogue[4] = "The only way out is the big door in the winterfell.\nBut it's not easy to get there";
    	}
    	else if(dialogueType == 1) {
    		dialogue[0] = "The door is guarded by hungry monster.\nYou must find a weapon to kill it";
        	dialogue[1] = "Some people said there's a maze in the south east,\nand they said treasures were there too, It might be \na weapon.";
        	dialogue[2] = "Many has failed to get the treasure,\nbut one guy have found the key to the treasure";
        	dialogue[3] = "He gave it to me,\nyou can find it near that small lake";
        	dialogue[4] = null;
    	}
    	else if(dialogueType == 2) {
    		dialogue[0] = "Did you get something from the maze?";
        	dialogue[1] = "Ah, it's a sword?!,\nGood for you boy";
        	dialogue[2] = "Because you have got the sword,\nyou should check the lava area in the south direction.";
        	dialogue[3] = "The rumor said if u kill all the monsters there,\nyou will get an astonishing power.";
        	dialogue[4] = null;
    	}
    	else if(dialogueType == 3) {
    		dialogue[0] = "Did you get something from the lava area?";
        	dialogue[1] = "Wow, it's the legend fruit!";
        	dialogue[2] = "Rumors also said that u will gain the power by eating\nthem.";
        	dialogue[3] = "And if u already eat the fruit,\nyou better head to winterfell and beat that monster.";
        	dialogue[4] = "The only way possible to get there is by the invisible portal in\nthe north east of my house";
        	dialogue[5] = "Good luck, Boy!";
    	}
    }
	public void setAction() {
		
//		actionLockCounter++;
//		if(actionLockCounter == 120) {
//			Random random = new Random();
//			int i = random.nextInt(100)+1; // pick up a number from 1 to 100
//			
//			if(i <= 25) {
//				direction = "up";
//			}
//			if(i > 25 && i <= 50) {
//				direction = "down";
//			}
//			if(i > 50 && i <= 75) {
//				direction = "left";
//			}
//			if(i > 75 && i <= 100) {
//				direction = "right";
//			}
//			
//			actionLockCounter = 0;
//		}
	}
	public void speak() {
		
		// DO this character specific stuff
		System.out.println(dialogueType+" "+ dialogueIndex);
		if(dialogueType == 0 && dialogueIndex > 4) {
			dialogueType = 1;
			dialogueIndex = 0;
			//System.out.println(dialogueType);
		}
		setDialogue();
		super.speak();
	}
}
