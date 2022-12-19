package main;

import entity.NPC_Tes;
import monster.MON_GreenSlime;
import monster.MON_LavaMonster;
import monster.MON_Wolf;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_House;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import tile_interactive.IT_DryTree;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int i = 0;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = gp.tileSize*75;
		gp.obj[i].worldY = gp.tileSize*1;
		i++;
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = gp.tileSize*98;
		gp.obj[i].worldY = gp.tileSize*90;
		i++;
//		gp.obj[i] = new OBJ_Key(gp);
//		gp.obj[i].worldX = gp.tileSize*25;
//		gp.obj[i].worldY = gp.tileSize*20;
//		i++;
//		gp.obj[i] = new OBJ_Shield_Blue(gp);
//		gp.obj[i].worldX = gp.tileSize*35;
//		gp.obj[i].worldY = gp.tileSize*20;
//		i++;
		gp.obj[i] = new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = gp.tileSize*1;
		gp.obj[i].worldY = gp.tileSize*95;
		i++;
		gp.obj[i] = new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = gp.tileSize*74;
		gp.obj[i].worldY = gp.tileSize*58;
		i++;
		gp.obj[i] = new OBJ_Heart(gp);
		gp.obj[i].worldX = gp.tileSize*97;
		gp.obj[i].worldY = gp.tileSize*1;
		i++;
		gp.obj[i] = new OBJ_ManaCrystal(gp);
		gp.obj[i].worldX = gp.tileSize*88;
		gp.obj[i].worldY = gp.tileSize*9;
		i++;
		gp.obj[i] = new OBJ_Heart(gp);
		gp.obj[i].worldX = gp.tileSize*94;
		gp.obj[i].worldY = gp.tileSize*22;
		i++;
		gp.obj[i] = new OBJ_ManaCrystal(gp);
		gp.obj[i].worldX = gp.tileSize*76;
		gp.obj[i].worldY = gp.tileSize*21;
		i++;
		gp.obj[i] = new OBJ_House(gp);
		gp.obj[i].worldX = gp.tileSize*8;
		gp.obj[i].worldY = gp.tileSize*3;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = gp.tileSize*6;
		gp.obj[i].worldY = gp.tileSize*24;
		i++;
		gp.obj[i] = new OBJ_Potion_Blue(gp);
		gp.obj[i].worldX = gp.tileSize*24;
		gp.obj[i].worldY = gp.tileSize*1;
		i++;
//		gp.obj[i] = new OBJ_Chest(gp);
//		gp.obj[i].worldX = gp.tileSize*5;
//		gp.obj[i].worldY = gp.tileSize*8;
//		i++;
	}
	public void setNPC() {
//		gp.npc[0] = new NPC_OldMan(gp);
//		gp.npc[0].worldX = gp.tileSize*21;
//		gp.npc[0].worldY = gp.tileSize*21;
//		
		gp.npc[0] = new NPC_Tes(gp);
		gp.npc[0].worldX = gp.tileSize*7;
		gp.npc[0].worldY = gp.tileSize*7;
//		
//		gp.npc[2] = new NPC_OldMan(gp);
//		gp.npc[2].worldX = gp.tileSize*21;
//		gp.npc[2].worldY = gp.tileSize*25;
		
	}
	public int setMonster() {
		
		int i = 0;
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 15;
		gp.monster[i].worldY = gp.tileSize * 61;
		i++;
		
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 17;
		gp.monster[i].worldY = gp.tileSize * 59;
		i++;

		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 20;
		gp.monster[i].worldY = gp.tileSize * 64;
		i++;
		
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 13;
		gp.monster[i].worldY = gp.tileSize * 56;
		i++;
		
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 13;
		gp.monster[i].worldY = gp.tileSize * 51;
		i++;
		
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 14;
		gp.monster[i].worldY = gp.tileSize * 56;
		i++;
		
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 3;
		gp.monster[i].worldY = gp.tileSize * 54;
		i++;
		
		gp.monster[i] = new MON_LavaMonster(gp);
		gp.monster[i].worldX = gp.tileSize * 5;
		gp.monster[i].worldY = gp.tileSize * 52;
		i++;
		int k = i;
		gp.monster[i] = new MON_Wolf(gp);
		gp.monster[i].worldX = gp.tileSize * 85;
		gp.monster[i].worldY = gp.tileSize * 18;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 57;
		gp.monster[i].worldY = gp.tileSize * 37;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 58;
		gp.monster[i].worldY = gp.tileSize * 51;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 60;
		gp.monster[i].worldY = gp.tileSize * 63;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 45;
		gp.monster[i].worldY = gp.tileSize * 61;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 41;
		gp.monster[i].worldY = gp.tileSize * 74;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 49;
		gp.monster[i].worldY = gp.tileSize * 74;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 57;
		gp.monster[i].worldY = gp.tileSize * 71;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 70;
		gp.monster[i].worldY = gp.tileSize * 67;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 83;
		gp.monster[i].worldY = gp.tileSize * 58;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 94;
		gp.monster[i].worldY = gp.tileSize * 60;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 50;
		gp.monster[i].worldY = gp.tileSize * 80;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 36;
		gp.monster[i].worldY = gp.tileSize * 88;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 39;
		gp.monster[i].worldY = gp.tileSize * 90;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 41;
		gp.monster[i].worldY = gp.tileSize * 82;
		i++;
	//	lavaMonAmount = 
		return k;
	}
	public void setInteractiveTile() {
		
		int i = 0;
		gp.iTile[i] = new IT_DryTree(gp,36,14); i++;
		gp.iTile[i] = new IT_DryTree(gp,35,14); i++;
		gp.iTile[i] = new IT_DryTree(gp,34,14); i++;
		gp.iTile[i] = new IT_DryTree(gp,33,14); i++;
		gp.iTile[i] = new IT_DryTree(gp,32,14); i++;
		
		gp.iTile[i] = new IT_DryTree(gp,28,19); i++;
		gp.iTile[i] = new IT_DryTree(gp,29,19); i++;
		gp.iTile[i] = new IT_DryTree(gp,30,19); i++;
	}
}
