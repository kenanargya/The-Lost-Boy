package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Laser;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity{
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int dialogueP;
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		// SOLID AREA
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		// ATTACK AREA
//		attackArea.width = 36;
//		attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize*19/2;
		worldY = gp.tileSize*7;
		speed = 4;
		direction = "down";
		
		// Player Status
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;
		strenght = 1; // The more strenght he has, the more damage he gives.
		dexterity =1;  // The more dexterity he has, the less damage he receives.
//		exp = 0;
//		nextLevelExp = 5;
		coin = 0;
//		currentWeapon = new OBJ_Sword_Normal(gp);
		currentWeapon = null;
		laserWeapon = new OBJ_Laser(gp);
		weaponTemp = currentWeapon;
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
//		projectile = new OBJ_Rock(gp);
		attack = getAttack(); // The total attack value is decided by strenght and weapon
		defense = getDefense(); // The total defense value is decided by dexterity and shield
	}
	public void setDefaultPositions() {
		
		worldX = gp.tileSize*19/2;
		worldY = gp.tileSize*7;
		direction = "down";
	}
	public void restoreLifeAndMana() {
		life = maxLife;
		mana = maxMana;
		invincible = false;
	}
	public void setItems() {
		
		inventory.clear();
		//inventory.add(currentWeapon);
		//inventory.add(currentShield);
		//inventory.add(new OBJ_Key(gp));
	}
	public int getAttack() {
		if (currentWeapon == null) return 0;
		attackArea = currentWeapon.attackArea;
		return attack = strenght * currentWeapon.attackValue;
	}
	public int getDefense() {
		if (currentWeapon == null) return 0;
		return defense = dexterity * currentShield.defenseValue;
	}
	public void getPlayerImage() {
		
		up1 = setup("/player/playertesup", gp.tileSize, gp.tileSize);
		up2 = setup("/player/playertesup2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/playertes", gp.tileSize, gp.tileSize);
		down2 = setup("/player/playertes2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/playerteskiri", gp.tileSize, gp.tileSize);
		left2 = setup("/player/playerteskiri2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/playerteskanan", gp.tileSize, gp.tileSize);
		right2 = setup("/player/playerteskanan2", gp.tileSize, gp.tileSize);
	}
	public void getPlayerAttackImage() {
		if(currentWeapon == null) {
			
		}
		else if(currentWeapon.type == type_sword) {
			attackUp1 = setup("/player/boy_attack_up_1_t", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/boy_attack_up_2_t", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/boy_attack_down_1_t", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/boy_attack_down_2_t", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/boy_attack_left_1_t", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_attack_left_2_t", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_attack_right_1_t", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_attack_right_2_t", gp.tileSize*2, gp.tileSize);
		}
		else if(currentWeapon.type == type_axe) {
			attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/boy_axe_Down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/boy_axe_Down_2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/boy_axe_Left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_axe_Left_2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_axe_Right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_axe_Right_2", gp.tileSize*2, gp.tileSize);
		}
		else if(currentWeapon.type == type_laser) {
			attackUp1 = setup("/player/laseratas", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/laseratas2", gp.tileSize, gp.tileSize*2);
			attackUp3 = setup("/player/laseratas3", gp.tileSize, gp.tileSize*2);
		//	attackDown1 = setup("/player/boy_attack_Down_1", gp.tileSize, gp.tileSize*2);
		//	attackDown2 = setup("/player/boy_attack_Down_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/laserdown1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/laserdown2", gp.tileSize, gp.tileSize*2);
			attackDown3 = setup("/player/laserdown3", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/laserkiri", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/laserkiri2", gp.tileSize*2, gp.tileSize);
			attackLeft3 = setup("/player/laserkiri3", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/laserkanan", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/laserkanan2", gp.tileSize*2, gp.tileSize);
			attackRight3 = setup("/player/laserkanan3", gp.tileSize*2, gp.tileSize);
		}
	}
	public void update() {
		
		if(attacking == true) {
			attacking();
//			currentWeapon = weaponTemp;
		}
		else if (attackLaser == true) {
			attackLaser();
		}
//		else if(charStat == true) {
//			statShow();
//		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true || keyH.qPressed == true) {
			
		if(keyH.upPressed== true) {
			direction = "up";			
		}
		else if(keyH.downPressed== true) {
			direction = "down";			
		}
		else if(keyH.leftPressed== true) {
			direction = "left";			
		}
		else if(keyH.rightPressed== true) {
			direction = "right";		
		}
//		if(keyH.charStatus== true) {
//			charStat = true;	
//		}
		
		// CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		// CHECK OBJECT COLLISION
		int  objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		// CHECK NPC COLLISION
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		// CHECK MONSTER COLLISION
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		
		// CHECK INTERACTIVE TILE COLLISION
		int iTileIndex = gp.cChecker.checkEntity(this,  gp.iTile);
		
		// CHECK EVENT
		gp.eHandler.checkEvent();
		
		// IF COLLISION IS FALSE, PLAYER CAN MOVE]
		if(collisionOn == false && gp.keyH.enterPressed == false && gp.keyH.qPressed == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			//case "charShow": gp.charStatusShow(); break;
			}
		}
		//weaponTemp = currentWeapon;
		if(keyH.enterPressed == true && attackCanceled == false && sword == true) {
			//weaponTemp = currentWeapon;
			currentWeapon = weaponTemp;
			attack = getAttack();
			getPlayerAttackImage();
				//gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
		}
		if(keyH.qPressed == true && attackCanceled == false && gp.lavaMonAmount == true) {
		//	weaponTemp = currentWeapon;
			currentWeapon = laserWeapon;
			attack = getAttack();
			getPlayerAttackImage();
				gp.playlasMusic(15);
				//gp.se.loop();
				attackLaser = true;
				spriteCounter = 0;
		}
//		currentWeapon = weaponTemp;
		attackCanceled = false;
		gp.keyH.enterPressed = false;
		gp.keyH.qPressed = false;
		
		spriteCounter++;
		if(spriteCounter > 7) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		else {
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false 
				&& shotAvailableCounter == 30 && projectile.haveResource(this) == true && gp.lavaMonAmount == true) {
			
			// SET DEFAULT COORDINATES, DIRECTION AND USER
			projectile.set(worldX, worldY, direction, true, this);
			
			// SUBSTRACT THE COST(MANA, AMMO ETC.)
			projectile.subtractResource(this);
			
			// ADD IT TO THE LIST
			gp.projectileList.add(projectile);
			
			shotAvailableCounter = 0;
			
			gp.playSE(10);
		}
		
		// This needs to be outside of key if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
		if(life > maxLife) {
			life = maxLife;
		}
		if(mana > maxMana) {
			mana = maxMana;
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.playSE(12);
		}
	}
	public void attacking() {
		spriteCounter++;
		// Save the current worldX, worldY, solidArea
		//boolean kena = false;
		int currentWorldX = worldX;
		int currentWorldY = worldY;
		int solidAreaWidth = solidArea.width;
		int solidAreaHeight = solidArea.height;
	//	System.out.println(solidAreaWidth);
		//int monsterIndex = gp.cChecker.checkEntity(this,  gp.monster);
		if(spriteCounter <= 10) {
			spriteNum = 1;
		}
		if(spriteCounter > 10 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case"up": worldY -= attackArea.height; break;
			case"down": worldY += attackArea.height; break;
			case"left": worldX -= attackArea.width; break;
			case"right": worldX += attackArea.width; break;
			}
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//System.out.println(solidArea.width + " " + solidArea.height);
			// Check monster collision with the updated worldX, worldY and solidArea
	//		System.out.println(this + "1");
			int monsterIndex = gp.cChecker.checkEntity(this,  gp.monster);
			System.out.println(attack);
			if(monsterIndex != 999 && spriteCounter == 11) gp.playSE(7);
			else if(monsterIndex == 999 && spriteCounter == 11) gp.playSE(14);
			damageMonster(monsterIndex, attack);
			
			int iTileIndex = gp.cChecker.checkEntity(this,  gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			// After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		//System.out.println(type + " " + type_sword);
//		if(keyH.qPressed == true) {
//			weaponTemp = currentWeapon;
//			currentWeapon.type = type_laser;
//		}
//		System.out.println(currentWeapon.type);
//		if(currentWeapon.type == type_laser) {
//			
//			switch(direction) {
//			case"up": worldY -= attackArea.height; break;
//			case"down": worldY += attackArea.height; break;
//			case"left": worldX -= attackArea.height; break;
//			case"right": worldX += attackArea.height; break;
//			}
//			solidArea.width = attackArea.width;
//			solidArea.height = attackArea.height;
//			//System.out.println(solidArea.width + " " + solidArea.height);
//			if(spriteCounter % 15 == 0) {
//				//System.out.println("true");
//			//	System.out.println(this);
//				if(spriteNum == 2) spriteNum = 3;
//				else if(spriteNum == 3) spriteNum = 2;
//				//System.out.println(solidArea.width + " " + solidArea.height);
//				int monsterIndex = gp.cChecker.checkEntity(this,  gp.monster);
//			//	System.out.println(monsterIndex);
//				damageMonster(monsterIndex, attack);
//			}
//			worldX = currentWorldX;
//			worldY = currentWorldY;
//			solidArea.width = solidAreaWidth;
//			solidArea.height = solidAreaHeight;
//			if(keyH.qPressed == false) {
////				worldX = currentWorldX;
////				worldY = currentWorldY;
//				if(spriteCounter > 35) {
//					//System.out.println("true");
//					spriteNum = 1;
//					spriteCounter = 0;
//					attacking = false;
//				}
//			}
//		}
		else if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
		
	}
	public void attackLaser() {
		spriteCounter++;
		// Save the current worldX, worldY, solidArea
		
		int currentWorldX = worldX;
		int currentWorldY = worldY;
		int solidAreaWidth = solidArea.width;
		int solidAreaHeight = solidArea.height;
	//	System.out.println(solidAreaWidth);
		//int monsterIndex = gp.cChecker.checkEntity(this,  gp.monster);
		if(spriteCounter <= 7) {
			spriteNum = 1;
			//if(spriteCounter == 2)gp.playSE(12);
		}
		if(spriteCounter > 7 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case"up": worldY -= attackArea.height; break;
			case"down": worldY += attackArea.height; break;
			case"left": worldX -= attackArea.width; break;
			case"right": worldX += attackArea.width; break;
			}
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//System.out.println(solidArea.width + " " + solidArea.height);
			// Check monster collision with the updated worldX, worldY and solidArea
	//		System.out.println(this + "1");
			int monsterIndex = gp.cChecker.checkEntity(this,  gp.monster);
		//	System.out.println(attack);
			damageMonster(monsterIndex, attack);
			
			int iTileIndex = gp.cChecker.checkEntity(this,  gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			// After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		//System.out.println(type + " " + type_sword);
//		if(keyH.qPressed == true) {
//			weaponTemp = currentWeapon;
//			currentWeapon.type = type_laser;
//		}
		System.out.println(currentWeapon.type);
		if(currentWeapon.type == type_laser) {
			//if(spriteCounter % 15 == 0)gp.playSE(12);
			switch(direction) {
			case"up": worldY -= attackArea.height; break;
			case"down": worldY += attackArea.height; break;
			case"left": worldX -= attackArea.height; break;
			case"right": worldX += attackArea.height; break;
			}
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//System.out.println(solidArea.width + " " + solidArea.height);
			if(spriteCounter % 15 == 0) {
				if(spriteNum == 2) spriteNum = 3;
				else if(spriteNum == 3) spriteNum = 2;
			}
				//System.out.println("true");
			//	System.out.println(this);
				
				//System.out.println(solidArea.width + " " + solidArea.height);
				int monsterIndex = gp.cChecker.checkEntity(this,  gp.monster);
			//	System.out.println(monsterIndex);
				damageMonster(monsterIndex, attack);
	//		}
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			if(keyH.qPressed == false) {
//				worldX = currentWorldX;
//				worldY = currentWorldY;
				if(spriteCounter > 35) {
					//System.out.println("true");
//					if(spriteCounter % 25 == 0)gp.playSE(12);
					spriteNum = 1;
					spriteCounter = 0;
					attackLaser = false;
					gp.stoplasMusic();
					//gp.stopMusic();
					//currentWeapon = weaponTemp;
				}
			}
		}
		else if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attackLaser = false;
			gp.stoplasMusic();
			//gp.stopMusic();
		}
		
	}
//	public void statShow() {
//		showCounter++;
//		gp.charStatusShow();
//		if(showCounter > 15) {
//			showCounter = 0;
//			charStat = false;
//		}
//	}
	public void pickUpObject(int i) {
		
		if(i != 999) {
			// PICKUP ONLY ITEMS
			//System.out.println(gp.obj[i].name + " " + gp.obj[i].type + " " + type_pickupOnly);
			if(gp.obj[i].type == type_pickupOnly){
				
				gp.obj[i].use(this);
				gp.obj[i] = null;
			}
			else if(gp.obj[i].type == type_extras){
			}
			else if(gp.obj[i].type == type_open) {
				String text;
				if(hasKey != 999) {
					if(keyH.enterPressed == true) {
						if(gp.obj[i].name == "Door") {
							attackCanceled = true;
							gp.gameState = gp.endingState;
							gp.stopMusic();
							gp.playMusic(16);
							hasKey = 999;
						}
						else {
							attackCanceled = true;
							sword = true;
							currentWeapon = new OBJ_Sword_Normal(gp);
							weaponTemp = currentWeapon;
							inventory.add(currentWeapon);
							inventory.add(currentShield);
							getPlayerAttackImage();
							attack = getAttack();
							defense = getDefense();
							inventory.remove(hasKey);
							hasKey = 999;
							gp.gameState = gp.dialogueState;
							gp.ui.currentDialogue = "You've got a sword and a shield!";
//							gp.gameState = gp.dialogueState;
//							gp.ui.currentDialogue = "Press 'Enter' to attack";
							gp.playSE(13);
							gp.obj[i].down1 = setup("/object/chest_opened", gp.tileSize, gp.tileSize);
//							gp.npc[i].dialogueType = 2;
							dialogueType = 2;
							dialogueIndex = 0;
						}
					}
					text = null;
				}
				else text = null;
				gp.ui.addMessage(text);
			}
			// INVENTOTY ITEMS
			else{
				String text;
				
				if(inventory.size() != maxInventorySize) {
					if (gp.obj[i].name == "Key") {
						hasKey = inventory.size();
					}
					inventory.add(gp.obj[i]);
					gp.playSE(1);
					text = "Got a " + gp.obj[i].name + "!";
				}
				else {
					text = "You cannot carry any more!";
				}
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			}
		}
	}
	public void interactNPC(int i) {
		
		if(gp.keyH.enterPressed == true) {
			if(i != 999) {
				attackCanceled = true;
			//	if(dialogueP == 2) gp.npc[i].dialogueType = dialogueP;
			//	System.out.println(gp.npc[i].dialogueType);
			//	gp.npc[i]
				gp.playSE(17);
	        	gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}	
	}
	public void contactMonster(int i) {
		if(i != 999) {
			System.out.println("true");
			if(invincible == false && gp.monster[i].dying == false) {
				gp.playSE(6);
				int damage = gp.monster[i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincible = true;
			}		
		}
	}
	public void damageMonster(int i, int attack) {
		
		if(i != 999) {
			
			if(gp.monster[i].invincible == false) {
				
				gp.playSE(5);
				//System.out.println(spriteCounter);
				int damage = attack - gp.monster[i].defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[i].name + "!");
//					gp.ui.addMessage("Exp + " + gp.monster[i].exp);
//					exp += gp.monster[i].exp;
//					checkLevelUp();
				}
			}
		}
	}
	public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[i].destructible == true 
				&& gp.iTile[i].isCorrectItem(this) == true && gp.iTile[i].invincible == false) {
			
			gp.iTile[i].playSE();
			gp.iTile[i].life--;
			gp.iTile[i].invincible = true;
			
			// Generate Particle
			generateParticle(gp.iTile[i], gp.iTile[i]);
			
			if(gp.iTile[i].life == 0) {
				gp.iTile[i] = gp.iTile[i].getDestroyedFrom();
			}	
		}
	}
//	public void checkLevelUp() {
//		
//		if(exp >= nextLevelExp) {
//			
//			level++;
//			nextLevelExp = nextLevelExp*2;
//			maxLife += 2;
//			strenght++;
//			dexterity++;
//			attack = getAttack();
//			defense = getDefense();
//			
//			gp.playSE(8);
//			gp.gameState = gp.dialogueState;
//			gp.ui.currentDialogue = "You are level " + level + " now\n" + "You feel stronger!";
//		}
//	}
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				
				currentWeapon = selectedItem;
				weaponTemp = currentWeapon;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable) {
				
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
//		if(charStat == true) {
//			gp.charStatusShow(g2);
//			charStat = false;
//		}
		switch(direction) {
		case "up":
			if(attacking == false && attackLaser == false) {
				if(spriteNum == 1) image = up1;
				if(spriteNum == 2) image = up2;	
			}
			if(attacking == true || attackLaser == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) image = attackUp1;
				if(spriteNum == 2) image = attackUp2;	
				if(spriteNum == 3) image = attackUp3;	
			}
			break;
		case "down":
			if(attacking == false && attackLaser == false) {
				if(spriteNum == 1) image = down1;
				if(spriteNum == 2) image = down2;	
			}
			if(attacking == true || attackLaser == true) {
				if(spriteNum == 1) image = attackDown1;
				if(spriteNum == 2) image = attackDown2;	
				if(spriteNum == 3) image = attackDown3;	
			}
			break;
		case "left":
			if(attacking == false && attackLaser == false) {
				if(spriteNum == 1) image = left1;
				if(spriteNum == 2) image = left2;	
			}
			if(attacking == true || attackLaser == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) image = attackLeft1;
				if(spriteNum == 2) image = attackLeft2;	
				if(spriteNum == 3) image = attackLeft3;	
			}
			break;
		case "right":
			if(attacking == false && attackLaser == false) {
				if(spriteNum == 1) image = right1;
				if(spriteNum == 2) image = right2;	
			}
			if(attacking == true || attackLaser == true) {
				if(spriteNum == 1) image = attackRight1;
				if(spriteNum == 2) image = attackRight2;
				if(spriteNum == 3) image = attackRight3;
			}
			break;
		}
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		}
		g2.drawImage(image,  tempScreenX,  tempScreenY, null);
		
		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		//currentWeapon = weaponTemp;
		// DEBUG
//		
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:"+invincibleCounter+" "+invincible, 10, 400);
	}
}
