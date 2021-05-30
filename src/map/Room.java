package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import objects.Object;

public class Room {
	
	public Map map;
	
//	public HashMap<Integer[], Room> roomMap = new HashMap<Integer[], Room>();
	
	public List<Object>  currentObjectList           = new ArrayList<Object>();
	public Queue<Object> currentObjectsRenderedQueue = new ConcurrentLinkedQueue<Object>();
	
	public Room(Map map) {
		
		this.map = map;
		
		this.currentObjectList.addAll(map.getTileObjects());
		
	}
	
	//
	
}