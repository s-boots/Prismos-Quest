package main;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import camera.Camera;

import input.KeyboardInputHandler;
import input.MouseInputHandler;

import map.Map;

import objects.NPCObject;
import objects.Object;
import objects.PlayerObject;
import objects.TileObject;

import physics.ObjectCollision;

import sprites.gui.CursorSprites;
import sprites.objects.player.PlayerSprites;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public static boolean isRunning = false;
	
	public static int mainWindowWidth  = 750;
	public static int mainWindowHeight = 650;
	
	public Insets mainWindowInsets;
	
	public BufferedImage backBuffer = new BufferedImage(mainWindowWidth, mainWindowHeight, BufferedImage.TYPE_INT_RGB);
	
	public Map gameMap;
	
	public static Camera camera;
	
	public static List<Object> currentObjectsOnScreen = new ArrayList<Object>();
	
	public PlayerObject playerObject;
	
	// run() and draw() Were Made by TerranceN for the Most Part
	
	public void run() {
		
		initialize();
		
		while (isRunning) {
			
			long time = System.currentTimeMillis();
			
			update();
			
			draw();
			
			// 60 = FPS Lock
			
//			long frameTime = (1000 / 60) - (System.currentTimeMillis() - time);
			long frameTime = (long) (16.666667 - (System.currentTimeMillis() - time));
			
			if (frameTime > 0) {
				
				try {
					
					Thread.sleep(frameTime);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		}
		
		exit();
		
	}
	
	void initialize() {
		
		createMainWindow();
		
		backBuffer = new BufferedImage(mainWindowWidth, mainWindowHeight, BufferedImage.TYPE_INT_RGB);
		
		KeyboardInputHandler keyboardInput = new KeyboardInputHandler(this);
		MouseInputHandler    mouseInput    = new MouseInputHandler(this);
		
		gameMap = new Map(new File(this.getClass().getResource("/map/maps/0.map").getFile()));
		
		setCurrentMap(gameMap);
		
		playerObject = new PlayerObject("Prismo", PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 450, 250, keyboardInput, mouseInput);
		
		camera = new Camera(playerObject, this, gameMap);
		
		NPCObject test1 = new NPCObject("Test2", PlayerSprites.getAnimationCycle(), Object.SIZE_16_16, 100, 300);
		
		this.setVisible(true);
		
//		playerObject.move(365, 450);
		test1.move(400, 50);
		
		isRunning = true;
		
	}
	
	void createMainWindow() {
		
		this.setTitle("Prismo's Quest");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(CursorSprites.cursorSprite, new Point(0, 0), null));
		
		this.setSize(mainWindowWidth, mainWindowHeight);
		
		this.mainWindowInsets = getInsets();
		
		this.setSize(this.mainWindowInsets.left + this.getWidth()  + this.mainWindowInsets.right,
					 this.mainWindowInsets.top  + this.getHeight() + this.mainWindowInsets.bottom);
		
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setLocationRelativeTo(null); // Set Location After Size
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				
				exit();
				
			}
			
		});
		
	}
	
	void update() {
		
		playerObject.checkForKeyboardInput();
		playerObject.checkForMouseInput();
		
		ObjectCollision.checkForWorldCollision(gameMap);
		ObjectCollision.checkForObjectCollision();
		
	}
	
	void draw() {
		
		Graphics backBufferGraphics = backBuffer.getGraphics();
		
		for (Object object : currentObjectsOnScreen) {
			
			object.draw(backBufferGraphics, this, this.mainWindowInsets, camera.offsetX, camera.offsetY);
			
		}
		
		this.getGraphics().drawImage(this.backBuffer, this.mainWindowInsets.left, this.mainWindowInsets.top, this);
		
	}
	
	void exit() {
		
		// This Method is for Configuration, Auto Saving, etc.
		
		System.exit(0);
		
	}
	
	void setCurrentMap(Map map) {
		
		for (Object object : currentObjectsOnScreen) {
			
			if (object instanceof TileObject) {
				
				currentObjectsOnScreen.remove(object);
				
			}
			
		}
		
		currentObjectsOnScreen.addAll(map.getTileObjects());
		
	}
	
}