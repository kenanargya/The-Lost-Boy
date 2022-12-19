package tile;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;


public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		// Read Tile Data File
		InputStream is = getClass().getResourceAsStream("/map/tiledata.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// Getting tiles names and collision from file
		String line;
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Initialize the tile array based on the fileNames size
		tile = new Tile[fileNames.size()];
		getTileImage();
		
		// Get the maxWorldCol and row
		is = getClass().getResourceAsStream("/map/dunia.txt");
		br = new BufferedReader(new InputStreamReader(is));
		
		try {
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
			br.close();
			
		} catch(IOException e) {
			System.out.println("Exception!");
			
		}
		
		loadMap("/map/dunia.txt");
		
	}
	
	public void getTileImage() {
		
	for(int i = 0; i < fileNames.size(); i++) {
		String fileName;
		boolean collision;
		
		//Get a file name
		fileName = fileNames.get(i);
		
		//Get a collision status
		if(collisionStatus.get(i).equals("true")) {
			collision = true;
		} else {
			collision = false;
		}
		
		setup(i, fileName, collision);
	}
		
		
		
//		    // PLACEHOLDER
//			setup(0, "grass00", false);
//			setup(1, "grass00", false);
//			setup(2, "grass00", false);
//			setup(3, "grass00", false);
//			setup(4, "grass00", false);
//			setup(5, "grass00", false);
//			setup(6, "grass00", false);
//			setup(7, "grass00", false);
//			setup(8, "grass00", false);
//			setup(9, "grass00", false);
//			setup(10, "grass00", false);
//			// PLACEHOLDER
//			
//			
//			setup(11, "wall", true);
//			setup(12, "earth", false);
//			setup(13, "water00", true);
//			setup(14, "tree", true);
//			setup(15, "sand", false);
//			setup(16, "grass01", false);
//			setup(17, "water01", true);
//			setup(18, "water02", true);
//			setup(19, "water03", true);
//			setup(20, "water04", true);
//			setup(21, "water05", true);
//			setup(22, "water06", true);
//			setup(23, "water07", true);
//			setup(24, "water08", true);
//			setup(25, "water09", true);
//			setup(26, "water10", true);
//			setup(27, "water11", true);
//			setup(28, "water12", true);
//			setup(29, "water13", true);
//			setup(30, "road00", false);
//			setup(31, "road02", false);
//			setup(32, "road07", false);
//			setup(33, "Testree", true);
//			setup(34, "lava", false);
//			setup(35, "lava01", false);
}
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles_test/"+imageName));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics g2) {
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		int worldcol = 0;
		int worldrow = 0;
		
		while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldcol][worldrow];
			
			int worldX = worldcol * gp.tileSize;
			int worldY = worldrow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		       worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) 
			{
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			
			worldcol++;
		
			
			if(worldcol == gp.maxWorldCol) {
				worldcol = 0;
				worldrow++;
			}
		}
	}
}
