package main;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import camera.Camera;

import gui.HealthBarOverlay;
import gui.Overlay;

import input.KeyboardInput;
import input.MouseInput;

import map.Map;
import map.Room;

import objects.NPCObject;
import objects.Object;
import objects.PlayerObject;
import objects.TileObject;
import objects.WeatherObject;

import physics.ObjectCollision;

import sprites.Sprite;
import sprites.objects.player.PlayerSprites;

public class Game {
	
	public static boolean isRunning = false;
	
	public int mainWindowWidth  = 750;
	public int mainWindowHeight = 650;
	
	public JFrame mainWindowFrame = new JFrame();
	
	private Insets mainWindowInsets;
	
	private BufferedImage backBuffer;
	
	private Graphics2D backBufferGraphics;
	
	private Room currentRoom;
	
	private Camera camera;
	
	private ObjectCollision objectCollision;
	
	public List<Overlay> currentOverlays = new ArrayList<Overlay>();
	
	public List<WeatherObject> currentWeatherObjectsRendered = new ArrayList<WeatherObject>();
	
	public PlayerObject playerObject;
	
	// This Method was Made by TerranceN
	
	void run() {
		
		initialize();
		
		while (isRunning) {
			
			long timeCache = System.currentTimeMillis();
			
			update();
			
			draw();
			
			// 60 = FPS Lock
			
//			long frameTime = (1000 / 60) - (System.currentTimeMillis() - time);
			long frameTime = (long) (16.6666666667 - (System.currentTimeMillis() - timeCache));
			
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
	
	private void initialize() {
		
		setMainWindow();
		
		this.backBuffer = new BufferedImage(this.mainWindowWidth, this.mainWindowHeight, BufferedImage.TYPE_INT_RGB);
		
		this.backBufferGraphics = this.backBuffer.createGraphics();
		
		KeyboardInput keyboardInput = new KeyboardInput(this.mainWindowFrame);
		MouseInput    mouseInput    = new MouseInput(this.mainWindowFrame);
		
		this.currentRoom = new Room(new Map(this.getClass().getResourceAsStream("/map/presets/world" + Globals.MAP)));
		
		setCurrentMap(this.currentRoom.map);
		
		playerObject = new PlayerObject("Prismo", 100, PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 450, 450, keyboardInput, mouseInput);
		
		this.currentRoom.currentObjectList.add(playerObject);
		
		objectCollision = new ObjectCollision(this.currentRoom);
		
		HealthBarOverlay healthBarOverlay = new HealthBarOverlay(playerObject);
		
		this.currentOverlays.add(healthBarOverlay);
		
		this.camera = new Camera(playerObject, this.mainWindowFrame, this.currentRoom.map);
		
		NPCObject npc1 = new NPCObject("Test1", PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 100, 300);
		NPCObject npc2 = new NPCObject("Test2", PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 150, 300);
		NPCObject npc3 = new NPCObject("Test3", PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 250, 320);
		NPCObject npc4 = new NPCObject("Test4", PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 400, 500);
		NPCObject npc5 = new NPCObject("Test5", PlayerSprites.getAnimationCycle(), Object.SIZE_16_24, 250, 900);
		
		this.currentRoom.currentObjectList.add(npc1);
		this.currentRoom.currentObjectList.add(npc2);
		this.currentRoom.currentObjectList.add(npc3);
		this.currentRoom.currentObjectList.add(npc4);
		this.currentRoom.currentObjectList.add(npc5);
		
		this.mainWindowFrame.setVisible(true);
		
		isRunning = true;
		
		playerObject.subtractHealth(50);
		
		npc1.walk(npc1.x + 150, npc1.y + 100);
		npc2.walk(npc2.x + 120, npc2.y + 520);
		npc3.walk(npc3.x + 450, npc3.y + 150);
		npc4.walk(npc4.x + 550, npc4.y + 400);
		npc5.walk(npc5.x + 650, npc5.y + 220);
		
	}
	
	private void setMainWindow() {
		
		this.mainWindowFrame.setTitle("Prismo's Quest");
		this.mainWindowFrame.setResizable(false);
		this.mainWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.mainWindowFrame.setUndecorated(true);
		this.mainWindowFrame.setAlwaysOnTop(true);
		
		this.mainWindowFrame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Sprite.createSprite(Globals.GUI_SPRITES, "cursor", Globals.PNG), new Point(0, 0), null));
		
		this.mainWindowFrame.setSize(this.mainWindowWidth, this.mainWindowHeight);
		
		this.mainWindowInsets = mainWindowFrame.getInsets();
		
		this.mainWindowFrame.setSize(this.mainWindowInsets.left + this.mainWindowFrame.getWidth()  + this.mainWindowInsets.right,
									 this.mainWindowInsets.top  + this.mainWindowFrame.getHeight() + this.mainWindowInsets.bottom);
		
		this.mainWindowFrame.setLocationRelativeTo(null); // Set Location After Size
		
//		this.mainWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.mainWindowFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				exit();
				
			}
			
		});
		
	}
	
	private void update() {
		
		playerObject.checkForKeyboardInput();
//		playerObject.checkForMouseInput();
		
		this.objectCollision.checkForWorldCollision();
		this.objectCollision.checkForObjectCollision();
		
	}
	
	private void draw() {
		
		int mainWindowFrameWidth  = this.mainWindowFrame.getWidth();
		int mainWindowFrameHeight = this.mainWindowFrame.getHeight();
		
		for (Object object : this.currentRoom.currentObjectList) {
			
			if ((object.x > (this.camera.offsetX - mainWindowFrameWidth))  && (object.x < (this.camera.offsetX + mainWindowFrameWidth)) &&
				(object.y > (this.camera.offsetY - mainWindowFrameHeight)) && (object.y < (this.camera.offsetY + mainWindowFrameHeight))) {
				
				object.draw(this.backBufferGraphics, this.mainWindowFrame, this.mainWindowInsets, this.camera.offsetX, this.camera.offsetY);
				
			}
			
		}
		
		for (Overlay overlay : this.currentOverlays) {
			
			overlay.draw(this.backBufferGraphics, this.mainWindowFrame);
			
		}
		
		this.mainWindowFrame.getGraphics().drawImage(this.backBuffer, this.mainWindowInsets.left, this.mainWindowInsets.top, this.mainWindowFrame);
		
	}
	
	private void exit() {
		
		// This Method is for Configuration, Auto Saving, etc.
		
		System.exit(0);
		
	}
	
	private void setCurrentMap(Map map) {
		
		for (Object object : new ArrayList<Object>(this.currentRoom.currentObjectList)) {
			
			if (object instanceof TileObject) {
				
				this.currentRoom.currentObjectList.remove(object);
				
			}
			
		}
		
		List<TileObject> tileObjects = map.getTileObjects();
		
		MediaTracker mediaTracker = new MediaTracker(mainWindowFrame);
		
		for (int i = 0; i < tileObjects.size(); i++) {
			
			mediaTracker.addImage(tileObjects.get(i).sprite, i);
			
		}
		
		try {
			
			mediaTracker.waitForAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		this.currentRoom.currentObjectList.addAll(tileObjects);
		
	}
	
}